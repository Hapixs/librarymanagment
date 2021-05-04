package fr.alexandresarouille;

import fr.alexandresarouille.entities.Loan;
import fr.alexandresarouille.model.Mail;
import fr.alexandresarouille.services.LoanService;
import fr.alexandresarouille.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.util.Collection;


public class Application extends SpringBootServletInitializer  {

    @Autowired
    private static Environment environment;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(Application.class);
    }

    @Autowired
    private LoanService loanService;
    @Autowired
    private MailService mailService;

    @Scheduled(fixedDelay = 1440000, initialDelay = 5000)
    public void scheduleEmailing() {
        String mailFrom = environment.getProperty("spring.mail.username");
        String subject = environment.getProperty("spring.mail.subject");
        Collection<Loan> loans = loanService.findAllExceededLoan();
        for(Loan loan : loans) {
            Mail mail = new Mail();
            mail.setMailTo(loan.getUser().getEmail());
            mail.setFrom(mailFrom);
            mail.setSubject(subject);
            try {
                mailService.sendEmail(mail);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
