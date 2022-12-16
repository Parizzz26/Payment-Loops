package id.co.indivara.jdt11.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "promo")
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPromo;

    @Column(nullable = true)
    private BigDecimal discount;

    @Column(nullable = true)
    private BigDecimal cashBack;
}
