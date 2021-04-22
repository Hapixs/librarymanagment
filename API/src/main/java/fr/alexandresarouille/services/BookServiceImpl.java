package fr.alexandresarouille.services;

import fr.alexandresarouille.dao.BookRepository;
import fr.alexandresarouille.dto.BookDTO;
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
