package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.model.enums.Gender;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Book> books = null;
    public static List<BookReservation> reservations = null;
    public static List<Author> authors = null;

    public final BookRepository bookRepository;
    public final AuthorRepository authorRepository;

    public DataHolder(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @PostConstruct
    public void init() {

        Author JaneAusten = new Author("Jane", "Austen", "UK",
                "Jane Austen was an English novelist.", Gender.FEMALE);
        Author JohnTolkien = new Author("John", "Tolkien", "UK",
                "John Ronald Reuel Tolkien was an English writer and philologist.", Gender.MALE);
        Author DanBrown = new Author("Dan", "Brown", "USA",
                "Daniel Gerhard Brown (born June 22, 1964) is an American writer.", Gender.MALE);

        authorRepository.save(JaneAusten);
        authorRepository.save(JohnTolkien);
        authorRepository.save(DanBrown);

        bookRepository.save(new Book("To Kill a Mockingbird", "Classic", 4.8, DanBrown));
        bookRepository.save(new Book("1984", "Dystopian", 4.7, DanBrown));
        bookRepository.save(new Book("The Great Gatsby", "Classic", 4.4, JohnTolkien));
        bookRepository.save(new Book("Harry Potter and the Sorcerer's Stone", "Fantasy", 4.9, JohnTolkien));
        bookRepository.save(new Book("The Lord of the Rings", "Fantasy", 4.9, JaneAusten));
        bookRepository.save(new Book("Pride and Prejudice", "Romance", 4.6, JaneAusten));
        bookRepository.save(new Book("The Catcher in the Rye", "Classic", 4.0, JaneAusten));
        bookRepository.save(new Book("The Hobbit", "Fantasy", 4.8, JohnTolkien));
        bookRepository.save(new Book("The Da Vinci Code", "Thriller", 4.3, DanBrown));
        bookRepository.save(new Book("The Alchemist", "Philosophical Fiction", 4.5, DanBrown));
    }
}
