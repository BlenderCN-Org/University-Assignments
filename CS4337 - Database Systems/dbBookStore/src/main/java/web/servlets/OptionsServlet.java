package web.servlets;

import core.WebApplication;
import db.Books;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import springdb.BooksRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(value = "/OptionsServlet", loadOnStartup = 1)
public class OptionsServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebApplication.class);

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<String> params = Collections.list(request.getParameterNames());
        LOGGER.info("Called Options! " + request.getParameter("sorttype") + ", " + params);

        String author = request.getParameter("aname");

        if (request.getParameter("sorttype").equals("subject")) {

            List<Books> booksList = booksRepository.findAllBooks();
            booksList.sort(Comparator.comparing(Books::getSubject));
            request.getSession().setAttribute("indexitems", booksList);

        } else if (request.getParameter("sorttype").equals("author") && author != null) {

            LOGGER.info("Author: " + author);
            List<Books> booksList = booksRepository.findAllBooks();
            List<Books> booksListUpdated = new ArrayList<>();

            for (Books book : booksList) {
                if (book.getAuthor().contains(author) || book.getTitle().contains(author)) {
                    LOGGER.info(book.getAuthor() + ", " + book.getSubject());
                    booksListUpdated.add(book);
                }
            }
            request.getSession().setAttribute("indexitems", booksListUpdated);

        } else if (request.getParameter("sorttype").equals("author")) {

            List<Books> booksList = booksRepository.findAllBooks();
            request.getSession().setAttribute("indexitems", booksList);

        }

        request.getSession().setAttribute("selectedSort", request.getParameter("sorttype"));
        request.getRequestDispatcher("/index/loaded").include(request, response);
    }
}
