package fr.alexandresarouille.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Loan {
    @Id
    private int uniqueId;

    @ManyToOne
    private User user;
    @ManyToOne
    private Book book;

    private Date dateStart;
    private Date dateEnd;
}
