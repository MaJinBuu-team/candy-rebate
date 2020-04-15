package cn.com.mjb.candyrebatecore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {
    @Bean
    public JavaMailSenderImpl JavaMailSender() {
        return new JavaMailSenderImpl();
    }
}
