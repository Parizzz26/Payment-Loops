package id.co.indivara.jdt11.payment.repository;

import id.co.indivara.jdt11.payment.entity.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoRepository extends JpaRepository<Promo,Long> {
}
