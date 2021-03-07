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
}
