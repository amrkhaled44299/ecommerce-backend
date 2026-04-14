package ecommerce.com.product_services.order;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Place Order
    @PostMapping
    public Order placeOrder(@RequestParam Long userId,
                            @RequestParam Long productId,
                            @RequestParam int quantity) {
        return orderService.placeOrder(userId, productId, quantity);
    }

    // Get User Orders
    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }

    // Get All Orders (Admin)
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Update Status (Admin)
    @PutMapping("/{orderId}")
    public Order updateStatus(@PathVariable Long orderId,
                              @RequestParam Order.Status status) {
        return orderService.updateStatus(orderId, status);
    }
}