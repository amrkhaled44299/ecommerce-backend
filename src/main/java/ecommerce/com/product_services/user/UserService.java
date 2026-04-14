package ecommerce.com.product_services.user;

import ecommerce.com.product_services.security.JwtUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    private final UserRepo repo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo repo, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.USER);
        return repo.save(user);
    }

    public String login(String username, String password) {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        return jwtUtil.generateToken(username);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }
}