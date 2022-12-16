package id.co.indivara.jdt11.payment.controller;

import id.co.indivara.jdt11.payment.entity.User;
import id.co.indivara.jdt11.payment.request.UserRequest;
import id.co.indivara.jdt11.payment.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loops/user")
public class UserControl {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/save")
    public User saveUser(UserRequest request){
        return userService.saveUser(request);
    }

    @GetMapping("/semua")
    public List<User> semuaDataUser(){
        return userService.semuaUser();
    }
}
