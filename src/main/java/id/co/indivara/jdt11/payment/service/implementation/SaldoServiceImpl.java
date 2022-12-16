package id.co.indivara.jdt11.payment.service.implementation;

import id.co.indivara.jdt11.payment.entity.Saldo;
import id.co.indivara.jdt11.payment.repository.SaldoRepository;
import id.co.indivara.jdt11.payment.service.SaldoService;
import id.co.indivara.jdt11.payment.util.PaymentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class SaldoServiceImpl implements SaldoService {

    @Autowired
    SaldoRepository saldoRepository;

    @Override
    public ResponseEntity<Saldo> cariSaldoByUserId(Long id) {
        Saldo saldo = saldoRepository.findById(id)
                .orElseThrow(()->new PaymentException("User Not Found"));
        return ResponseEntity.ok().body(saldo);
    }
}
