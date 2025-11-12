package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> listAll();

    List<Book> searchBooks(String text, Double rating);

    Book findById(Long id);

    Book save(String title, String genre, double averageRating, Long authorId);

    Book edit(Long id, String title, String genre, double averageRating, Long authorId);

    void delete(Long id);

}
