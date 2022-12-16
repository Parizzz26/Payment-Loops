package id.co.indivara.jdt11.payment.repository;

import id.co.indivara.jdt11.payment.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}
