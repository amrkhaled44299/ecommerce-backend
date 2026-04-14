package ecommerce.com.product_services.payment;

import ecommerce.com.product_services.order.Order;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private double amount;
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PENDING,
        SUCCESS,
        FAILED
    }

    public Payment() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}