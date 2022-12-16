package id.co.indivara.jdt11.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
@Table(name = "saldo")
public class Saldo {
    @Id
    @Column(name = "id_balance")
    private Long idBalance;

    @NotNull
    @DecimalMin("0")
    private BigDecimal balance;

    private Date updateDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
