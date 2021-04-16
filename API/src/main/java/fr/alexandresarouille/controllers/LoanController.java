package fr.alexandresarouille.controllers;

import fr.alexandresarouille.dto.LoanDTO;
import fr.alexandresarouille.entities.Book;
import fr.alexandresarouille.entities.Loan;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import fr.alexandresarouille.services.BookService;
import fr.alexandresarouille.services.LoanService;
import fr.alexandresarouille.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;


    @PostMapping
    public Loan createLoan(@NotNull LoanDTO loanDTO) throws EntityNotExistException {
        return loanService.create(convertToLoan(loanDTO));
    }

    @PutMapping("{id}")
    public Loan editLoan(@NotNull @PathVariable int id, @NotNull LoanDTO loanDTO) throws EntityNotExistException {
        return loanService.edit(id, convertToLoan(loanDTO));
    }

    @DeleteMapping("{id}")
    public void deleteLoan(@NotNull @PathVariable int id) throws EntityNotExistException {
        loanService.delete(id);
    }

    private Loan convertToLoan(@NotNull LoanDTO loanDTO) throws EntityNotExistException {
        User user = userService.findByIdIfExist(loanDTO.getUserId());
        Book book = bookService.findByIdIfExist(loanDTO.getBookId());
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setDateStart(loanDTO.getDateStart());
        loan.setDateEnd(loanDTO.getDateEnd());
        return loan;
    }
}
