package id.co.indivara.jdt11.payment.service;

import id.co.indivara.jdt11.payment.entity.User;
import id.co.indivara.jdt11.payment.request.UserRequest;
import id.co.indivara.jdt11.payment.response.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    User saveUser(UserRequest request);

    List<User> semuaUser();

}
