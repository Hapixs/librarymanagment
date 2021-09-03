package fr.alexandresarouille.librairy.web.services;

import fr.alexandresarouille.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BookService {

    Page<Book> findAllBook(Pageable pageable);

    Book findById(int id);
}
