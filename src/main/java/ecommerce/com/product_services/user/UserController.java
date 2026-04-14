package ecommerce.com.product_services.user;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
public User register(@Valid @RequestBody User user) {
    return userService.register(user);
}

    @PostMapping("/login")
public String login(@RequestBody User user) {
    return userService.login(user.getUsername(), user.getPassword());
}

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}