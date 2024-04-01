package com.redweber.backendservice.mail;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {
    @Bean
    public MailProperties mailProperties() {
        return new MailProperties();
    }
}
