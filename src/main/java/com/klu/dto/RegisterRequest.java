package com.klu.dto;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {

    private String name;
    @NotBlank
    @Email(message = "Invalid email format")
    private String email;
    private String password;
    private String role;
    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    private String phone;

    public RegisterRequest() {
    }

    // All-args constructor
    public RegisterRequest(String name, String email, String password,String role, String  phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role=role;
        this.phone=phone;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
    	return role;
    }
    public void setRole(String role) {
    	this.role=role;
    }
    public String getPhone() {
        return phone;
    }

    // Setter for password
    public void setPhone(String phone) {
        this.phone = phone;
    }
}