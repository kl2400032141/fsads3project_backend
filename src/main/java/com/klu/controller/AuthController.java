package com.klu.controller;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.klu.dto.LoginRequest;
import com.klu.dto.OTPRequest;
import com.klu.dto.RegisterRequest;
import com.klu.service.AuthService;
import java.util.Map;
import java.util.HashMap;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3030")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) throws Exception {
        return authService.register(request);
    }
    @PostMapping("/verify")
    public String verify(@RequestBody OTPRequest request) {
        return authService.verifyOtp(request);
    }
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}