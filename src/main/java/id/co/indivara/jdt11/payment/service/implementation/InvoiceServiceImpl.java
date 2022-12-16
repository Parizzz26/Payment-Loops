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


    @Override
    public InvoiceResponse saveInvoice(InvoiceRequest request) {
        Invoice invoice = new Invoice();
        invoice.setBasePrice(request.getBasePrice());
        invoice.setTotalPrice(request.getTotalPrice());
        invoice.setUser(getUser(Math.toIntExact(request.getUserId())));
        invoice.setJenis(getJenis(Math.toIntExact(request.getJenisId())));
        invoice.setCompany(getCompany(Math.toIntExact(request.getIdCompany())));
        invoice.setCreatedDate(new Date());

        Invoice i = invoiceRepository.save(invoice);
        saveSaldo(Math.toIntExact(request.getUserId()),request);

        InvoiceResponse response = new InvoiceResponse();
        response.setBasePrice(i.getBasePrice());
        response.setUserId(i.getUser().getId());
        response.setJenisId(i.getJenis().getJenisId());
        response.setIdCompany(i.getCompany().getIdCompany());
        response.setTotalPrice(i.getTotalPrice());
        response.setCreatedDate(i.getCreatedDate());
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
    public Page<Invoice> invoiceUser() {
        return (Page<Invoice>)invoiceRepository.findAll(PageRequest.of(0,5, Sort.by(Sort.Direction.DESC, "userId")));
    }


    private Saldo saveSaldo(Integer userId, InvoiceRequest request) {
        Saldo saldo = saldoRepository.findById(Long.valueOf(userId))
                .orElseThrow(()->new PaymentException("User Not Found"));
        BigDecimal hasilSaldo = request.getBasePrice().add(saldo.getBalance());
        BigDecimal kurangSaldo = saldo.getBalance().subtract(request.getBasePrice());
        Saldo saldo1 = new Saldo();
        saldo1.setIdBalance(saldo.getIdBalance());
        saldo1.setUser(getUser(userId));
        saldo1.setBalance(request.getJenisId()!=1?kurangSaldo:hasilSaldo);
        saldo1.setUpdateDate(new Date());
        saldoRepository.save(saldo1);
        return saldo;
    }

}
