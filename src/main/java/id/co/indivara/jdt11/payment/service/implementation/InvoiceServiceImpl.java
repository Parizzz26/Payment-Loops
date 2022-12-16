package id.co.indivara.jdt11.payment.service.implementation;

import id.co.indivara.jdt11.payment.entity.*;
import id.co.indivara.jdt11.payment.repository.*;
import id.co.indivara.jdt11.payment.request.InvoiceRequest;
import id.co.indivara.jdt11.payment.response.InvoiceResponse;
import id.co.indivara.jdt11.payment.service.InvoiceService;
import id.co.indivara.jdt11.payment.util.PaymentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Validated
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SaldoRepository saldoRepository;

    @Autowired
    JenisRepository jenisRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    PromoRepository promoRepository;

    @Override
    public InvoiceResponse saveInvoice(InvoiceRequest request) {
        Invoice invoice = new Invoice();
        invoice.setBasePrice(request.getBasePrice());
        invoice.setUser(getUser(Math.toIntExact(request.getUserId())));
        invoice.setJenis(getJenis(Math.toIntExact(request.getJenisId())));
        invoice.setCompany(getCompany(Math.toIntExact(request.getIdCompany())));
        invoice.setPromo(getPromo(Math.toIntExact(request.getIdPromo())));
        invoice.setNamaJenis(request.getJenisId()==1?"Topup":"Payment");
        invoice.setTotalPrice(invoice.getBasePrice());
        invoice.setCreatedDate(new Date());

        Invoice i = invoiceRepository.save(invoice);

        InvoiceResponse response = new InvoiceResponse();
        response.setBasePrice(i.getBasePrice());
        response.setUserId(i.getUser().getId());
        response.setJenisId(i.getJenis().getJenisId());
        response.setIdCompany(i.getCompany().getIdCompany());
        response.setIdPromo(i.getPromo().getIdPromo());
        response.setNamaJenis(i.getNamaJenis());
        response.setNamaCompany(i.getCompany().getCompany());
        response.setDiscount(i.getPromo().getDiscount());
        response.setCashback(i.getPromo().getCashBack());
        response.setTotalPrice(response.getDiscount()!=null?i.getBasePrice()
                .subtract(i.getBasePrice().multiply(response.getDiscount()).divide(BigDecimal.valueOf(100))):
                i.getBasePrice().subtract(response.getCashback()));
        response.setCreatedDate(i.getCreatedDate());
        saveSaldo(Math.toIntExact(response.getUserId()),response);
        return response;
    }

    @Override
    public User getUser(Integer userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(()->new PaymentException("User Not Found"));
        return user;
    }

    @Override
    public Jenis getJenis(Integer jenisId) {
        Jenis jenis = jenisRepository.findById(Long.valueOf(jenisId))
                .orElseThrow(()->new PaymentException("Jenis Not Found"));
        jenis.getJenis();
        return jenis;
    }

    @Override
    public Company getCompany(Integer companyId) {
        Company company = companyRepository.findById(Long.valueOf(companyId))
                .orElseThrow(()->new PaymentException("Company Not Found"));
        company.getCompany();
        return company;
    }

    @Override
    public Promo getPromo(Integer promoId) {
        Promo promo = promoRepository.findById(Long.valueOf(promoId))
                .orElseThrow(()->new PaymentException("Promo Not Found"));
        promo.getDiscount();
        promo.getCashBack();
        return promo;
    }

    @Override
    public Page<Invoice> invoiceUser() {
        return (Page<Invoice>)invoiceRepository.findAll(PageRequest.of(1,5, Sort.by(Sort.Direction.DESC, "userId")));
    }


    private Saldo saveSaldo(Integer userId, InvoiceResponse response) {
        Saldo saldo = saldoRepository.findById(Long.valueOf(userId))
                .orElseThrow(()->new PaymentException("User Not Found"));
        BigDecimal hasilSaaldo = response.getTotalPrice().add(saldo.getBalance());
        Saldo saldo1 = new Saldo();
        saldo1.setIdBalance(saldo.getIdBalance());
        saldo1.setUser(getUser(userId));
        saldo1.setBalance(hasilSaaldo);
        saldo1.setUpdateDate(new Date());
        saldoRepository.save(saldo1);
        return saldo;
    }

}
