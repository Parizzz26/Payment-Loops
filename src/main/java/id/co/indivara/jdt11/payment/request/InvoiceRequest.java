package id.co.indivara.jdt11.payment.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceRequest {
    private Long userId;
    private Long idCompany;
    private Long jenisId;
    private Long idPromo;
    private String namaJenis;
    private BigDecimal basePrice;
}
