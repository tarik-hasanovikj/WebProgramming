package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.searchBooks(text, rating);
    }

    @Override
    public Book findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Override
    public Book save(String title, String genre, double averageRating, Long authorId) {
        Optional<Author> author = authorRepository.findAll().stream()
                .filter(a -> a.getId().equals(authorId))
                .findFirst();

        if (author.isEmpty()) {
            return null;
        }

        Book book = new Book(title, genre, averageRating, author.get());
        return bookRepository.save(book);
    }

    @Override
    public Book edit(Long id, String title, String genre, double averageRating, Long authorId) {
        Optional<Book> book = bookRepository.findById(id);
        Optional<Author> author = authorRepository.findAll().stream()
                .filter(a -> a.getId().equals(authorId))
                .findFirst();

        if (book.isEmpty() || author.isEmpty()) {
            return null;
        }

        book.get().setTitle(title);
        book.get().setGenre(genre);
        book.get().setAverageRating(averageRating);
        book.get().setAuthor(author.get());

        return bookRepository.save(book.get());
    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(id);
    }

}
