package fr.alexandresarouille.library.batch.entities;

import fr.alexandresarouille.library.api.entities.Loan;
import fr.alexandresarouille.library.api.entities.User;

import java.util.Collection;
import java.util.List;

public class Mail {
    private Collection<Loan> loans;
    private String templateName;
    private String from;
    private User user;
    private String subject;
    private List<Object> attachments;

    public Mail() {
    }

    public Collection<Loan> getLoans() {
        return this.loans;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public String getFrom() {
        return this.from;
    }

    public User getUser() {
        return this.user;
    }

    public String getSubject() {
        return this.subject;
    }

    public List<Object> getAttachments() {
        return this.attachments;
    }

    public void setLoans(Collection<Loan> loans) {
        this.loans = loans;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setAttachments(List<Object> attachments) {
        this.attachments = attachments;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Mail)) return false;
        final Mail other = (Mail) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$loans = this.getLoans();
        final Object other$loans = other.getLoans();
        if (this$loans == null ? other$loans != null : !this$loans.equals(other$loans)) return false;
        final Object this$templateName = this.getTemplateName();
        final Object other$templateName = other.getTemplateName();
        if (this$templateName == null ? other$templateName != null : !this$templateName.equals(other$templateName))
            return false;
        final Object this$from = this.getFrom();
        final Object other$from = other.getFrom();
        if (this$from == null ? other$from != null : !this$from.equals(other$from)) return false;
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        final Object this$subject = this.getSubject();
        final Object other$subject = other.getSubject();
        if (this$subject == null ? other$subject != null : !this$subject.equals(other$subject)) return false;
        final Object this$attachments = this.getAttachments();
        final Object other$attachments = other.getAttachments();
        if (this$attachments == null ? other$attachments != null : !this$attachments.equals(other$attachments))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Mail;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $loans = this.getLoans();
        result = result * PRIME + ($loans == null ? 43 : $loans.hashCode());
        final Object $templateName = this.getTemplateName();
        result = result * PRIME + ($templateName == null ? 43 : $templateName.hashCode());
        final Object $from = this.getFrom();
        result = result * PRIME + ($from == null ? 43 : $from.hashCode());
        final Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        final Object $subject = this.getSubject();
        result = result * PRIME + ($subject == null ? 43 : $subject.hashCode());
        final Object $attachments = this.getAttachments();
        result = result * PRIME + ($attachments == null ? 43 : $attachments.hashCode());
        return result;
    }

    public String toString() {
        return "Mail(loans=" + this.getLoans() + ", templateName=" + this.getTemplateName() + ", from=" + this.getFrom() + ", user=" + this.getUser() + ", subject=" + this.getSubject() + ", attachments=" + this.getAttachments() + ")";
    }
}
