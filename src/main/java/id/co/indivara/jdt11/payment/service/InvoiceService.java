package id.co.indivara.jdt11.payment.service;

import id.co.indivara.jdt11.payment.entity.*;
import id.co.indivara.jdt11.payment.request.InvoiceRequest;
import id.co.indivara.jdt11.payment.response.InvoiceResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InvoiceService {
    InvoiceResponse saveInvoice(InvoiceRequest request);

    User getUser(Integer userId);

    Jenis getJenis(Integer jenisId);

    Company getCompany(Integer companyId);

    Promo getPromo(Integer promoId);

    Page<Invoice> invoiceUser();
}
