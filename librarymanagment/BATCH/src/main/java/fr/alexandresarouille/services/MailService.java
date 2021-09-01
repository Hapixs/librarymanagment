package fr.alexandresarouille.services;

import fr.alexandresarouille.model.Mail;

import javax.mail.MessagingException;

public interface MailService {
    void sendEmail(Mail mail) throws MessagingException;
}
