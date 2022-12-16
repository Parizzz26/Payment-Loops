package id.co.indivara.jdt11.payment.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class InvoiceResponse {
    private Long userId;
    private Long idCompany;
    private Long jenisId;
    private Long idPromo;
    private BigDecimal basePrice;
    private BigDecimal totalPrice;
    private String namaJenis;
    private String namaCompany;
    private BigDecimal discount;
    private BigDecimal cashback;
    private Date createdDate;
}
