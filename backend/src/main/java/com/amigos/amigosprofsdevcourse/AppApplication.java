package com.amigos.amigosprofsdevcourse;

import com.amigos.amigosprofsdevcourse.customer.Customer;
import com.amigos.amigosprofsdevcourse.customer.CustomerRepository;
import com.github.javafaker.Faker;
import lombok.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import java.util.TimeZone;

@SpringBootApplication
public class AppApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Kyiv"));
        ConfigurableApplicationContext run = SpringApplication.run(AppApplication.class, args);



//        System.out.println(run.getEnvironment());
//        System.out.println(Arrays.toString(run.getBeanDefinitionNames()));
    }

//    @Bean
//    CommandLineRunner runner(CustomerRepository customerRepository) {
//        return args -> {
//            Faker faker = new Faker();
//            Customer alex = Customer
//                    .builder()
//                    .name(faker.name().fullName())
//                    .email(faker.internet().emailAddress())
//                    .age(faker.random().nextInt(18, 50))
//                    .country(faker.country().countryCode3().toUpperCase())
//                    .build();
//
//            customerRepository.save(alex);
//        };
//    }
}
