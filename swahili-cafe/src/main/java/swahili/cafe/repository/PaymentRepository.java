package swahili.cafe.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swahili.cafe.model.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findPaymentByPaymentId(long paymentId);

    @Query("SELECT SUM(paymentAmount) FROM Payment")
    Long getTotalPayments();

    @Query("SELECT p FROM Payment p JOIN p.expert e WHERE e.username = ?1")
    List<Payment> findPaymentsByExpertUsername(String username);
}
