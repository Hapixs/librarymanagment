package fr.alexandresarouille.library.batch.services;

import fr.alexandresarouille.library.batch.entities.Mail;

import javax.mail.MessagingException;

public interface MailService {
    void sendEmail(Mail mail) throws MessagingException;
}
