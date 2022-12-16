package id.co.indivara.jdt11.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "jenis")
public class Jenis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jenisId;

    @NotEmpty
    private String jenis;
}
