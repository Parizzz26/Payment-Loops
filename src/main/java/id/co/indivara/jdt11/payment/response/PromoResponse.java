package id.co.indivara.jdt11.payment.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PromoResponse {
    private Integer discount;
    private BigDecimal cashBack;
}
