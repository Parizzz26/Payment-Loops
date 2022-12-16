package id.co.indivara.jdt11.payment.request;

import lombok.Data;

@Data
public class UserRequest {
    private String nama;
    private String email;
    private String password;
    private String jenisKelamin;
}
