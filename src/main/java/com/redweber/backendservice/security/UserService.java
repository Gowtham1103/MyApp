package com.redweber.backendservice.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public static boolean isValidPassword(String password) {
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        if (password.length() < 8) {
            return false;
        }
        if (!password.matches(".*[!@#$%^&*()\\-_+=<>?/{}\\[\\]\\|].*")) {
            return false;
        }
        return true;
    }
    public ResponseEntity<?> signUp(User credentials) {
        if (userRepository.existsByUsername(credentials.getUsername())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Username not available!");
        }
        if (!isValidPassword(credentials.getPassword())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Your password must be at least 8 characters long and contain at least one uppercase letter and one special character.");
        }
        userRepository.save(credentials);
        return ResponseEntity.ok("User account created successfully!");
    }
    public ResponseEntity<?> signIn(@RequestBody User credentials) {
        User existingUser = userRepository.findByUsername(credentials.getUsername());
        if (existingUser != null && credentials.getPassword().equals(existingUser.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
