package fr.alexandresarouille.services;

import fr.alexandresarouille.dao.BookRepository;
import fr.alexandresarouille.entities.Book;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public Optional<Book> findById(@NotNull int id) {
        return repository.findById(id);
    }

    @Override
    public Book findByIdIfExist(@NotNull int id) throws EntityNotExistException {
        return findById(id).orElseThrow(() -> new EntityNotExistException("Il semblerais que ce livre n'existe pas."));
    }

    @Override
    public Optional<Book> findByName(@NotNull String name) {
        return repository.findByName(name);
    }

    @Override
    public Book create(@NotNull Book book) throws EntityExistException {
        Optional<Book> optionalBook = findByName(book.getName());
        if(optionalBook.isPresent())
            throw new EntityExistException("Un livre avec le même nom existe déjà");

        return repository.saveAndFlush(book);
    }

    @Override
    public void delete(@NotNull int id) throws EntityNotExistException {
        repository.delete(findByIdIfExist(id));
    }

    @Override
    public Book edit(@NotNull int id, @NotNull Book book) throws EntityNotExistException {
        Book target = findByIdIfExist(id);
        target.setName(book.getName());
        target.setAuthor(book.getAuthor());
        target.setQuantity(book.getQuantity());
        return repository.saveAndFlush(target);
    }
}
