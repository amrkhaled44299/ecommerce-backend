package ecommerce.com.product_services.payment;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Process Payment
    @PostMapping("/{orderId}")
    public Payment processPayment(@PathVariable Long orderId) {
        return paymentService.processPayment(orderId);
    }

    // Get Payment by Order
    @GetMapping("/order/{orderId}")
    public Payment getPaymentByOrder(@PathVariable Long orderId) {
        return paymentService.getPaymentByOrder(orderId);
    }

    // Get All Payments (Admin)
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
}