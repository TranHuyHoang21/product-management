package com.example.productmanagement.config;

import com.example.productmanagement.entity.Product;
import com.example.productmanagement.entity.User;
import com.example.productmanagement.repository.ProductRepository;
import com.example.productmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Tạo user mẫu nếu chưa có
        if (userRepository.count() == 0) {
            // Admin user
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");
            admin.setEnabled(true);
            userRepository.save(admin);
            
            // Regular user
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole("ROLE_USER");
            user.setEnabled(true);
            userRepository.save(user);
            
            System.out.println("Đã tạo users mẫu:");
            System.out.println("Admin - username: admin, password: admin123");
            System.out.println("User - username: user, password: user123");
        }
        
        // Tạo products mẫu nếu chưa có
        if (productRepository.count() == 0) {
            for (int i = 1; i <= 25; i++) {
                Product product = new Product();
                product.setName("Sản phẩm " + i);
                product.setDescription("Mô tả chi tiết cho sản phẩm " + i);
                product.setPrice(new BigDecimal(100000 + (i * 50000)));
                product.setQuantity(10 + i);
                productRepository.save(product);
            }
            System.out.println("Đã tạo 25 sản phẩm mẫu");
        }
    }
}