package ecommerce.com.product_services.payment;

import ecommerce.com.product_services.order.Order;
import ecommerce.com.product_services.order.OrderRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepo paymentRepo;
    private final OrderRepo orderRepo;

    public PaymentService(PaymentRepo paymentRepo, OrderRepo orderRepo) {
        this.paymentRepo = paymentRepo;
        this.orderRepo = orderRepo;
    }

    public Payment processPayment(Long orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalPrice());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus(Payment.Status.SUCCESS);

        // Update order status
        order.setStatus(Order.Status.CONFIRMED);
        orderRepo.save(order);

        return paymentRepo.save(payment);
    }

    public Payment getPaymentByOrder(Long orderId) {
        return paymentRepo.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }
}