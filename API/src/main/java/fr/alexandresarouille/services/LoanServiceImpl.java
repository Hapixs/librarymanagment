package fr.alexandresarouille.services;

import fr.alexandresarouille.dao.LoanRepository;
import fr.alexandresarouille.entities.Loan;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository repository;

    @Override
    public Optional<Loan> findById(@NotNull int id) {
        return repository.findById(id);
    }

    @Override
    public Loan findByIdIfExist(@NotNull int id) throws EntityNotExistException {
        return findById(id).orElseThrow(() -> new EntityNotExistException("Ce prèt n'existe pas ou plus."));
    }

    @Override
    public Loan create(@NotNull Loan loan) {
        return repository.save(loan);
    }

    @Override
    public void delete(@NotNull int id) throws EntityNotExistException {
        repository.delete(findByIdIfExist(id));
    }

    @Override
    public Loan edit(@NotNull int id,@NotNull Loan loan) throws EntityNotExistException {
        Loan target = findByIdIfExist(id);
        target.setBook(loan.getBook());
        target.setUser(loan.getUser());
        target.setDateStart(loan.getDateStart());
        target.setDateEnd(loan.getDateEnd());
        return repository.saveAndFlush(target);
    }

    @Override
    public Page<Loan> findAllByUser(@NotNull PageRequest pageRequest,@NotNull User user) {
        return repository.findAllByUser(pageRequest, user);
    }
}
