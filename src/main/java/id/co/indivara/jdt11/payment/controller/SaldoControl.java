package id.co.indivara.jdt11.payment.controller;

import id.co.indivara.jdt11.payment.entity.Saldo;
import id.co.indivara.jdt11.payment.service.implementation.SaldoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loops/user")
public class SaldoControl {

    @Autowired
    SaldoServiceImpl saldoService;

    @GetMapping("/saldo/{id}")
    public ResponseEntity<Saldo> cariSaldoByUserId(@PathVariable("id") Long id){
        return  saldoService.cariSaldoByUserId(id);
    }
}
