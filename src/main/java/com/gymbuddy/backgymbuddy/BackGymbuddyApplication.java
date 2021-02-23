package com.gymbuddy.backgymbuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BackGymbuddyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackGymbuddyApplication.class, args);
    }

    /*@Bean
    public AuditorAware<String> auditorProvider() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();
        return () -> Optional.of(loginId);
    }*/
}
