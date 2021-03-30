package fr.alexandresarouille.services;

import fr.alexandresarouille.dao.LoanRepository;
import fr.alexandresarouille.entities.Loan;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new EntityNotExistException(Loan.class, id);

        return optionalLoan.get();
    }

    @Override
    public void create(Loan loan) {
        repository.save(loan);
    }

    @Override
    public void delete(int id) throws EntityNotExistException {
        repository.delete(findByIdIfExist(id));
    }

    @Override
    public void edit(int id, Loan loan) throws EntityNotExistException {
        Loan target = findByIdIfExist(id);

        target.setBook((loan.getBook() == null) ? target.getBook() : loan.getBook());
        target.setUser((loan.getUser() == null) ? target.getUser() : loan.getUser());
        target.setDateStart((loan.getDateStart() == null) ? target.getDateStart() : loan.getDateStart());
        target.setDateEnd((loan.getDateEnd() == null) ? target.getDateEnd() : loan.getDateEnd());

        repository.saveAndFlush(target);
    }
}
