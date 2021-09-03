package fr.alexandresarouille.library.api.services;

import com.google.common.base.Strings;
import fr.alexandresarouille.library.api.entities.Book;
import fr.alexandresarouille.library.api.entities.dto.BookDTO;
import fr.alexandresarouille.library.api.exceptions.EntityExistException;
import fr.alexandresarouille.library.api.exceptions.EntityNotExistException;
import fr.alexandresarouille.library.api.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * {@link BookService}
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    /**
     * {@link BookRepository}
     */
    @Autowired
    private BookRepository repository;

    /**
     * {@link BookService#findById(int)}
     */
    @Override
    public Optional<Book> findById(@NotNull int id) {
        return repository.findById(id);
    }


    /**
     * {@link BookService#findById(int)}
     */
    @Override
    public Book findByIdIfExist(@NotNull int id) throws EntityNotExistException {
        return findById(id).orElseThrow(() -> new EntityNotExistException("Il semblerais que ce livre n'existe pas."));
    }

    /**
     * {@link BookService#findByName(String)}
     */
    @Override
    public Optional<Book> findByName(@NotNull String name) {
        return repository.findByName(name);
    }

    /**
     * {@link BookService#create(BookDTO)}
     */
    @Override
    public Book create(@NotNull BookDTO bookDTO) throws EntityExistException {
        Optional<Book> optionalBook = findByName(bookDTO.getName());
        if (optionalBook.isPresent())
            throw new EntityExistException("Un livre avec le même nom existe déjà");

        Book book = convertFromDTO(bookDTO);

        return repository.saveAndFlush(book);
    }

    /**
     * {@link BookService#findAll(Pageable)}
     */
    @Override
    public Page<Book> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * {@link BookService#findAllByFilter(Pageable, Book)}
     */
    @Override
    public Page<Book> findAllByFilter(Pageable pageable, Book bookFilters) {
        return repository.findAllByFilters(pageable, Strings.emptyToNull(bookFilters.getAuthor()), Strings.emptyToNull(bookFilters.getName()));
    }

    /**
     * Convert a bookDTO object to a Book object
     *
     * @param bookDTO {@link BookDTO}
     * @return {@link Book}
     */
    private Book convertFromDTO(@NotNull BookDTO bookDTO) {
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setQuantity(bookDTO.getQuantity());
        book.setName(bookDTO.getName());
        return book;
    }
}
