package fr.alexandresarouille.services;

import fr.alexandresarouille.dao.BookRepository;
import fr.alexandresarouille.entities.Book;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public Optional<Book> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Book findByIdIfExist(int id) throws EntityNotExistException {
        Optional<Book> optionalBook = findById(id);
        if(!optionalBook.isPresent())
            throw new EntityNotExistException("Il semblerais que ce livre n'existe pas.");

        return optionalBook.get();
    }

    @Override
    public Optional<Book> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Book create(Book book) throws EntityExistException {
        Optional<Book> optionalBook = findByName(book.getName());
        if(optionalBook.isPresent())
            throw new EntityExistException("Un livre avec le même nom existe déjà");

        return repository.saveAndFlush(book);
    }

    @Override
    public void delete(int id) throws EntityNotExistException {
        repository.delete(findByIdIfExist(id));
    }

    @Override
    public Book edit(int id, Book book) throws EntityNotExistException {
        Book target = findByIdIfExist(id);
        target.setName(book.getName());
        target.setAuthor(book.getAuthor());
        target.setQuantity(book.getQuantity());
        return repository.saveAndFlush(target);
    }
}
