package fr.alexandresarouille.library.batch;

import fr.alexandresarouille.library.api.entities.Loan;
import fr.alexandresarouille.library.api.entities.User;
import fr.alexandresarouille.library.batch.entities.Mail;
import fr.alexandresarouille.library.batch.services.LoanService;
import fr.alexandresarouille.library.batch.services.MailService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.mail.MessagingException;
import java.util.*;
import java.util.logging.Logger;

@SpringBootApplication
@EnableScheduling
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(Application.class);
    }

    @Bean
    public SpringResourceTemplateResolver springResourceTemplateResolver() {
        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setPrefix("classpath:/templates/");
        springResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
        springResourceTemplateResolver.setSuffix(".html");
        springResourceTemplateResolver.setCharacterEncoding("UTF-8");
        return springResourceTemplateResolver;
    }

    @Autowired
    private Environment environment;
    @Autowired
    private LoanService loanService;
    @Autowired
    private MailService mailService;

    @Scheduled(fixedDelay = 1440000, initialDelay = 5000)
    public void scheduleEmailing() {
        Optional<String> mailFrom = Optional.ofNullable(environment.getProperty("spring.mail.email"));
        Optional<String> subject = Optional.ofNullable(environment.getProperty("spring.mail.subject"));

        Optional<String> host = Optional.ofNullable(environment.getProperty("spring.mail.host"));
        Optional<String> username = Optional.ofNullable(environment.getProperty("spring.mail.username"));
        Optional<String> password = Optional.ofNullable(environment.getProperty("spring.mail.password"));
        Integer port = Integer.parseInt(Optional.ofNullable(environment.getProperty("spring.mail.port")).get());

        if(!mailFrom.isPresent() || !subject.isPresent()){
            Logger.getLogger("BATCH").warning("Error in the application.properties, the username or subject hasn't been set");
            return;
        }

        Collection<Loan> loans = loanService.findAllExceededLoan();

        HashMap<User, ArrayList<Loan>> loanHeap = new HashMap<>();
        for(Loan loan : loans) {
            ArrayList<Loan> userLoans = loanHeap.getOrDefault(loan.getUser(), new ArrayList<>());
            userLoans.add(loan);
            loanHeap.put(loan.getUser(), userLoans);
        }

        for(User user : loanHeap.keySet()) {
            List<Loan> userLoans = loanHeap.get(user);

            Mail mail = new Mail();
            mail.setLoans(userLoans);
            mail.setTemplateName("mail");
            mail.setUser(user);
            mail.setFrom(mailFrom.get());
            mail.setSubject(subject.get());

            mail.setHost(host.get());
            mail.setPassword(password.get());
            mail.setPort(port);
            mail.setUsername(username.get());

            try {
                mailService.sendEmail(mail);
            } catch (MessagingException e) {
               Logger.getLogger("[BATCH]").warning("Mail not sent " + Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
