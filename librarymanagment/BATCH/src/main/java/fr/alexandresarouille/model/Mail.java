package fr.alexandresarouille.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Mail {
    private String from;
    private String mailTo;
    private String subject;
    private List<Object> attachments;
    private Map<String, Object> props;
}
