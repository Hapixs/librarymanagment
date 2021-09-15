package fr.alexandresarouille.library.batch.services;

import fr.alexandresarouille.library.batch.entities.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;

@Service
@Transactional
public class MailServiceImpl implements MailService {


    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    private SpringResourceTemplateResolver springResourceTemplateResolver;

    @Override
    public void sendEmail(Mail mail) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Context context = new Context();

        context.setVariable("username", mail.getUser().getName());
        context.setVariable("loans", mail.getLoans());

        springTemplateEngine.setTemplateResolver(springResourceTemplateResolver);
        String html = springTemplateEngine.process("mail", context);


        helper.setTo(mail.getUser().getEmail());
        helper.setText(html, true);
        helper.setFrom(mail.getFrom());
        helper.setSubject(mail.getSubject());

        javaMailSender.setHost(mail.getHost());
        javaMailSender.setPort(mail.getPort());
        javaMailSender.setUsername(mail.getUsername());
        javaMailSender.setPassword(mail.getPassword());

        javaMailSender.send(mimeMessage);
    }
}
