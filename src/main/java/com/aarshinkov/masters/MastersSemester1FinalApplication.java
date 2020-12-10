package com.aarshinkov.masters;

import com.aarshinkov.masters.security.Expressions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MastersSemester1FinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MastersSemester1FinalApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public Expressions expressions() {
        return new Expressions();
    }
}
