package fr.alexandresarouille.services;

import fr.alexandresarouille.dao.LoanRepository;
import fr.alexandresarouille.dto.BookDTO;
import fr.alexandresarouille.dto.LoanDTO;
import fr.alexandresarouille.entities.Book;
import fr.alexandresarouille.entities.Loan;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.BookNoQuantityException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import fr.alexandresarouille.exceptions.LoanAlreadyExtendedException;
import fr.alexandresarouille.exceptions.SameBookLoanForUserException;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository repository;

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    /**
     * {@link LoanService#findById(int)}
     */
    @Override
    public Optional<Loan> findById(@NotNull int id) {
        return repository.findById(id);
    }

    /**
     * {@link LoanService#findByIdIfExist(int)}
     */
    @Override
    public Loan findByIdIfExist(@NotNull int id) throws EntityNotExistException {
        return findById(id).orElseThrow(() -> new EntityNotExistException("Ce prèt n'existe pas ou plus."));
    }

    /**
     * {@link LoanService#findAllByUser(PageRequest, User)}
     */
    @Override
    public Page<Loan> findAllByUser(@NotNull PageRequest pageRequest, @NotNull User user) {
        return repository.findAllByUser(pageRequest, user);
    }

    /**
     * {@link LoanService#create(LoanDTO)}
     */
    @Override
    public Loan create(@NotNull LoanDTO loanDTO) throws EntityNotExistException, BookNoQuantityException, SameBookLoanForUserException {
        Loan loan = convertToLoan(loanDTO);
        User user = loan.getUser();
        Book book = loan.getBook();

        if(repository.findByUserAndBook(user, book).isPresent())
            throw new SameBookLoanForUserException();

        if(book.getQuantity()<1)
            throw new BookNoQuantityException();

        book.setQuantity(book.getQuantity()-1);

        loan.setBook(book);
        loan.setUser(user);
        return repository.save(convertToLoan(loanDTO));
    }

    /**
     * {@link LoanService#extendLoan(int)}
     */
    @Override
    public Loan extendLoan(int id) throws EntityNotExistException, LoanAlreadyExtendedException {
        Loan loan = findByIdIfExist(id);
        LocalDateTime dateEnd = loan.getDateEnd();


        if(loan.getDateStart().plusWeeks(4).compareTo(dateEnd)!=0)
            throw new LoanAlreadyExtendedException();

        loan.setDateEnd(dateEnd.plusWeeks(4));

        return repository.saveAndFlush(loan);
    }

    /**
     * Convert a LoanDTO object to a Loan object
     *
     * @param loanDTO {@link LoanDTO}
     * @return {@link Loan}
     * @throws EntityNotExistException {@link EntityNotExistException}
     */
    private Loan convertToLoan(@Valid LoanDTO loanDTO) throws EntityNotExistException {

        User user = userService.findByIdIfExist(loanDTO.getUserId());
        Book book = bookService.findByIdIfExist(loanDTO.getBookId());

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);

        LocalDateTime dateStart = LocalDateTime.now();
        loan.setDateStart(dateStart);
        loan.setDateEnd(dateStart.plusWeeks(4));
        loan.setDateReturn(null);
        return loan;
    }
}
