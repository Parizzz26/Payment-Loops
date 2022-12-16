package id.co.indivara.jdt11.payment.service.implementation;

import id.co.indivara.jdt11.payment.entity.Saldo;
import id.co.indivara.jdt11.payment.entity.User;
import id.co.indivara.jdt11.payment.repository.SaldoRepository;
import id.co.indivara.jdt11.payment.repository.UserRepository;
import id.co.indivara.jdt11.payment.request.UserRequest;
import id.co.indivara.jdt11.payment.response.UserResponse;
import id.co.indivara.jdt11.payment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Validated
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SaldoRepository saldoRepository;

    @Override
    public User saveUser(UserRequest request) {
        User user = new User();
        user.setNama(request.getNama());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setJenisKelamin(request.getJenisKelamin());
        user.setCreateDateUser(new Date());
        User u = userRepository.save(user);

        Saldo saldo = new Saldo();
        saldo.setBalance(BigDecimal.ZERO);
        saldo.setUpdateDate(new Date());
        saldo.setUser(u);
        saldoRepository.save(saldo);

        return user;
    }

    @Override
    public List<User> semuaUser() {
        return (List<User>)userRepository.findAll();
    }
}
