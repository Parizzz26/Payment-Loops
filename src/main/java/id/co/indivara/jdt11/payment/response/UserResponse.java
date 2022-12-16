package id.co.indivara.jdt11.payment.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserResponse {
    private String nama;
    private String email;
    private String password;
    private String jenisKelamin;
    private Date createDateUser;
}
