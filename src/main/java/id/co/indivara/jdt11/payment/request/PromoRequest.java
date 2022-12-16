package id.co.indivara.jdt11.payment.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PromoRequest {
    private Integer discount;
    private BigDecimal cashBack;
}
