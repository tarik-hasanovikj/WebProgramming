package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookReservationController {

    private final BookReservationService bookReservationService;
    private final BookService bookService;

    public BookReservationController(BookReservationService bookReservationService, BookService bookService) {
        this.bookReservationService = bookReservationService;
        this.bookService = bookService;
    }

    @PostMapping("/bookReservation")
    public String makeReservation(@RequestParam Long bookId,
                                  @RequestParam String readerName,
                                  @RequestParam String readerAddress,
                                  @RequestParam int numCopies,
                                  HttpServletRequest request,
                                  Model model) {

        String clientIp = request.getRemoteAddr();

        Book book = bookService.findById(bookId);

        BookReservation reservation = bookReservationService.placeReservation(book.getTitle(), readerName, readerAddress, numCopies);

        model.addAttribute("bookTitle", reservation.getBookTitle());
        model.addAttribute("readerName", reservation.getReaderName());
        model.addAttribute("numCopies", reservation.getNumberOfCopies());
        model.addAttribute("clientIp", clientIp);

        return "reservationConfirmation";
    }
}
