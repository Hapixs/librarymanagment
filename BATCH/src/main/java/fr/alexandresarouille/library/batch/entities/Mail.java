package fr.alexandresarouille.library.batch.entities;

import fr.alexandresarouille.library.api.entities.Loan;
import fr.alexandresarouille.library.api.entities.User;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class Mail {
    private Collection<Loan> loans;
    private String templateName;
    private String from;
    private User user;
    private String subject;
    private List<Object> attachments;
}
