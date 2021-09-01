package fr.alexandresarouille.library.web.services;

import fr.alexandresarouille.library.api.entities.Book;
import fr.alexandresarouille.library.web.ApplicationProperties;
import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Book> findAllBook(Pageable pageable, Book bookFilter) {
        StringBuilder url = new StringBuilder(applicationProperties.getRestHostAddress());
        ParameterizedTypeReference<List<Book>> responseType = new ParameterizedTypeReference<List<Book>>() { };
        return  restTemplate.postForObject(url.append("/api/all/books").toString(), bookFilter, List.class, Maps.of("page", pageable.getPageNumber(), "items", pageable.getPageSize()));

    }

    @Override
    public Book findById(int id) {
        StringBuilder url = new StringBuilder(applicationProperties.getRestHostAddress());
        return restTemplate.getForEntity(url.append(String.format("/api/all/books/%s", id)).toString(), Book.class).getBody();
    }
}
