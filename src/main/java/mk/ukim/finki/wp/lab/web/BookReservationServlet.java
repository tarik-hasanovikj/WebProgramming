package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookReservationServlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final BookReservationService bookReservationService;

    public BookReservationServlet(SpringTemplateEngine springTemplateEngine, BookReservationService bookReservationService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookReservationService = bookReservationService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        int numberOfCopies = Integer.parseInt(req.getParameter("numCopies"));
        String clientIp = req.getRemoteAddr();

        BookReservation reservation = bookReservationService.placeReservation(
                bookTitle, readerName, readerAddress, numberOfCopies
        );

        context.setVariable("readerName", reservation.getReaderName());
        context.setVariable("clientIp", clientIp);
        context.setVariable("bookTitle", reservation.getBookTitle());
        context.setVariable("numCopies", reservation.getNumberOfCopies());

        springTemplateEngine.process("reservationConfirmation.html", context, resp.getWriter());
    }

}
