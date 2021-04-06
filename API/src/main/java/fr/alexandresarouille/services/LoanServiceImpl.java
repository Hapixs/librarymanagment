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
import java.util.Optional;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository repository;

    @Override
    public Optional<Loan> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Loan findByIdIfExist(int id) throws EntityNotExistException {
        Optional<Loan> optionalLoan = findById(id);
        if(!optionalLoan.isPresent())
            throw new EntityNotExistException("Ce prèt n'existe pas ou plus.");

        return optionalLoan.get();
    }

    @Override
    public Loan create(Loan loan) {
        return repository.save(loan);
    }

    @Override
    public void delete(int id) throws EntityNotExistException {
        repository.delete(findByIdIfExist(id));
    }

    @Override
    public Loan edit(int id, Loan loan) throws EntityNotExistException {
        Loan target = findByIdIfExist(id);
        target.setBook(loan.getBook());
        target.setUser(loan.getUser());
        target.setDateStart(loan.getDateStart());
        target.setDateEnd(loan.getDateEnd());
        return repository.saveAndFlush(target);
    }

    @Override
    public Page<Loan> findAllByUser(PageRequest pageRequest, User user) {
        return repository.findAllByUser(pageRequest, user);
    }
}
