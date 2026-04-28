package com.klu.service;

import com.klu.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.klu.dto.LoginRequest;
import com.klu.dto.OTPRequest;
import com.klu.dto.RegisterRequest;
import com.klu.repository.UserRepository;
import java.util.Map;
import java.util.HashMap;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;
    private Map<String, RegisterRequest> tempUsers = new HashMap<>();
    private Map<String, String> otpStorage = new HashMap<>();
    // 🔹 REGISTER
    public String register(RegisterRequest request) throws Exception {

        // check existing user in DB
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            return "User already exists";
        }

        // generate OTP
        String otp = String.valueOf((int)(Math.random() * 9000) + 1000);

        // store TEMP data
        tempUsers.put(request.getEmail(), request);
        otpStorage.put(request.getEmail(), otp);

        // send email
        emailService.sendOtp(request.getEmail(), otp);

        return "OTP sent to email";
    }

    // 🔹 VERIFY OTP
    public String verifyOtp(OTPRequest request) {

        String storedOtp = otpStorage.get(request.getEmail());

        if (storedOtp == null) {
            return "OTP not found";
        }

        if (!storedOtp.equals(request.getOtp())) {
            return "Invalid OTP";
        }

        // ✅ get temp user data
        RegisterRequest tempUser = tempUsers.get(request.getEmail());

        if (tempUser == null) {
            return "User data not found";
        }

        // ✅ NOW SAVE TO DB
        User user = new User();
        user.setName(tempUser.getName());
        user.setEmail(tempUser.getEmail());
        user.setPassword(passwordEncoder.encode(tempUser.getPassword()));
        user.setRole(tempUser.getRole());
        user.setPhone(tempUser.getPhone());
        user.setVerified(true);

        userRepo.save(user);

        // cleanup
        tempUsers.remove(request.getEmail());
        otpStorage.remove(request.getEmail());

        return "Account verified and registered!";
    }
    // 🔹 LOGIN
    public Map<String, Object> login(LoginRequest request) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow();

        if (!user.isVerified()) {
            throw new RuntimeException("Verify your account first!");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // ✅ return user data instead of string
        Map<String, Object> response = new HashMap<>();
        response.put("email", user.getEmail());
        response.put("role", user.getRole());

        return response;
    }
}