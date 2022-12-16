package id.co.indivara.jdt11.payment.service;

import id.co.indivara.jdt11.payment.entity.Saldo;
import org.springframework.http.ResponseEntity;

public interface SaldoService {
    ResponseEntity<Saldo> cariSaldoByUserId(Long id);
}
