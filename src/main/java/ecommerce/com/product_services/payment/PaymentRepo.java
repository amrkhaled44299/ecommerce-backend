package ecommerce.com.product_services.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PaymentRepo extends JpaRepository<Payment, Long> {

    Optional<Payment> findByOrderId(Long orderId);
}