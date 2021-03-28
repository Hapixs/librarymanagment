package fr.alexandresarouille.services;

import fr.alexandresarouille.dao.BookRepository;
import fr.alexandresarouille.entities.Book;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
            throw new EntityNotExistException(Book.class, id);

        return optionalBook.get();
    }

    @Override
    public Optional<Book> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void create(Book book) throws EntityExistException {
        Optional<Book> optionalBook = findByName(book.getName());
        if(optionalBook.isPresent())
            throw new EntityExistException(Book.class, optionalBook.get().getUniqueId());

        repository.saveAndFlush(book);
    }

    @Override
    public void delete(int id) throws EntityNotExistException {
        repository.delete(findByIdIfExist(id));
    }

    @Override
    public void edit(int id, Book book) throws EntityNotExistException {
        Book target = findByIdIfExist(id);

        target.setAuthor((book.getAuthor() == null || book.getAuthor().isEmpty()) ? target.getAuthor() : book.getAuthor());
        target.setName((book.getName() == null || book.getName().isEmpty()) ? target.getName() : book.getName());
        target.setQuantity((book.getQuantity() == null) ? target.getQuantity() : book.getQuantity());

        repository.saveAndFlush(target);
    }
}
