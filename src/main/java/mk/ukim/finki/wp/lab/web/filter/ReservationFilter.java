package mk.ukim.finki.wp.lab.web.filter;

//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebFilter
//public class ReservationFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//
//        String path = req.getServletPath();
//
//        String bookTitle = req.getParameter("bookTitle");
//        String readerName = req.getParameter("readerName");
//        String readerAddress = req.getParameter("readerAddress");
//        String numberOfCopies = req.getParameter("numCopies");
//
//        if (((bookTitle == null || bookTitle.isEmpty()) ||
//                (readerName == null || readerName.isEmpty()) ||
//                (readerAddress == null || readerAddress.isEmpty()) ||
//                (numberOfCopies == null || numberOfCopies.isEmpty())) &&
//                !path.isEmpty()
//        ) {
//            resp.sendRedirect("/");
//        } else {
//            filterChain.doFilter(req, resp);
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
//
