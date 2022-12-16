package id.co.indivara.jdt11.payment.controller;

import id.co.indivara.jdt11.payment.entity.Invoice;
import id.co.indivara.jdt11.payment.request.InvoiceRequest;
import id.co.indivara.jdt11.payment.response.InvoiceResponse;
import id.co.indivara.jdt11.payment.service.implementation.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loops/invoice")
public class InvoiceControl {
    @Autowired
    InvoiceServiceImpl service;

    @PostMapping("/save")
    public InvoiceResponse saveInvoice(@RequestBody InvoiceRequest request) {
        return service.saveInvoice(request);
    }

    @GetMapping("/history")
    public Page<Invoice> invoiceUser() {
        return  service.invoiceUser();
    }
}
