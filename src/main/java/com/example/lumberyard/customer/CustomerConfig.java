package com.example.lumberyard.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository) {
        return args -> {
            Customer john = new Customer("John Doe", "john.doe@example.com", 12);
            Customer jane = new Customer("Jane Smith", "jane.smith@example.com", 24);

            repository.saveAll(List.of(john, jane));
        };
    }
}
