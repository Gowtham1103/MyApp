package com.redweber.backendservice.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public ResponseEntity<String> sendSubscriptionEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Thank You for Subscribing");
        message.setText("Dear Customer,\n" +
                "\n" +
                "Thank you for subscribing to our updates and exclusive deals! We're thrilled to have you join our community.\n" +
                "\n" +
                "Stay tuned for exciting news, special offers, and insider information delivered straight to your inbox.\n" +
                "\n" +
                "If you have any questions or feedback, feel free to reach out to us anytime.\n" +
                "\n" +
                "Best regards,\n" +
                "thankyou :)");

        try {
            emailSender.send(message);
            return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully.");
        } catch (MailException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email.");
        }
    }
}