package fr.alexandresarouille.library.web;

import fr.alexandresarouille.library.web.services.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"fr.alexandresarouille.library.web.controllers", "fr.alexandresarouille.library.web.config"})
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
    @Bean
    public ApplicationProperties applicationProperties() {
        return new ApplicationProperties();
    }
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
    @Bean
    public BookService bookService() {
        return new BookServiceImpl();
    }
    @Bean
    public LoanService loanService() {
        return new LoanServiceImpl();
    }
}
