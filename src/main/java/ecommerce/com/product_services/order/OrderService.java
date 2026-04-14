package ecommerce.com.product_services.order;

import ecommerce.com.product_services.product.Product;
import ecommerce.com.product_services.product.ProductRepo;
import ecommerce.com.product_services.user.User;
import ecommerce.com.product_services.user.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    public OrderService(OrderRepo orderRepo, ProductRepo productRepo, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    public Order placeOrder(Long userId, Long productId, int quantity) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setTotalPrice(product.getPrice() * quantity);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Order.Status.PENDING);

        return orderRepo.save(order);
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepo.findByUserId(userId);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Order updateStatus(Long orderId, Order.Status status) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return orderRepo.save(order);
    }
}