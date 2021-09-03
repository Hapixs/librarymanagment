package fr.alexandresarouille.library.web.services;

import fr.alexandresarouille.library.api.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface BookService {

    List<Book> findAllBook(Pageable pageable, Book bookFilter);

    Book findById(int id);
}
