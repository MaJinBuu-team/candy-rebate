package cn.com.mjb.candyrebateweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn.com.mjb")
public class CandyRebateWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CandyRebateWebApplication.class, args);
    }
}
