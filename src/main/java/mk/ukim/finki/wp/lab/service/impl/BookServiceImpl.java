package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static mk.ukim.finki.wp.lab.service.FieldFilterSpecification.*;

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
    public List<Book> searchBooks(String title, Double minRating) {
        Specification<Book> specification = Specification.allOf(
                filterContainsText(Book.class, "title", title),
                greaterThan(Book.class, "averageRating", minRating)
        );

        return this.bookRepository.findAll(specification);
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
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAllByAuthorId(Long authorId) {
        return bookRepository.findAllByAuthor_Id(authorId);
    }

}
