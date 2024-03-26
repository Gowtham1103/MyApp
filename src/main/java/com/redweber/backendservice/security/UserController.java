package com.redweber.backendservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User credentials){
        return userService.signUp(credentials);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody User credentials){
        return userService.signIn(credentials);
    }
}
