package com.klu.dto;

public class OTPRequest {

    private String email;
    private String otp;

    // No-args constructor
    public OTPRequest() {
    }

    // All-args constructor
    public OTPRequest(String email, String otp) {
        this.email = email;
        this.otp = otp;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for otp
    public String getOtp() {
        return otp;
    }

    // Setter for otp
    public void setOtp(String otp) {
        this.otp = otp;
    }
}