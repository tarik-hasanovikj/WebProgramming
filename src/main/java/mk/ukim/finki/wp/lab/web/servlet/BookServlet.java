package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookServlet", urlPatterns = "/servlet/books")
public class BookServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;

    public BookServlet(SpringTemplateEngine springTemplateEngine, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        String searchText = req.getParameter("searchText");
        String ratingParam = req.getParameter("minRating");

        List<Book> books;
        if ((searchText != null && !searchText.isEmpty()) || (ratingParam != null && !ratingParam.isEmpty())) {
            Double rating = null;
            try {
                rating = Double.parseDouble(ratingParam);
            } catch (NumberFormatException ignored) {
            }
            books = bookService.searchBooks(searchText.trim(), rating);
        } else {
            books = bookService.listAll();
        }

        context.setVariable("ipAddress", req.getRemoteAddr());
        context.setVariable("books", books);

        springTemplateEngine.process("listBooks", context, resp.getWriter());
    }

}
