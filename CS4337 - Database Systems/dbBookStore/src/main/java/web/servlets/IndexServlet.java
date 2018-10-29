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
import java.util.List;

@WebServlet(value = "/index", loadOnStartup = 1)
public class IndexServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebApplication.class);

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Books> booksList = booksRepository.findAllBooks();
        request.getSession().setAttribute("indexitems", booksList);
        request.getRequestDispatcher("/index/loaded").include(request, response);
    }

}
