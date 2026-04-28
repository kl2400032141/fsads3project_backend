package com.klu.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtp(String to, String otp) throws Exception {
    	 MimeMessage message = mailSender.createMimeMessage();
         MimeMessageHelper helper = new MimeMessageHelper(message);

         helper.setTo(to);
         helper.setSubject("OTP Verification");
         helper.setText("Your OTP is: " + otp);

         mailSender.send(message);
     }
}