package web.servlets;

import core.WebApplication;
import db.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import springdb.CartRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = "/CartAddServlet", loadOnStartup = 1)
public class CartAddServlet extends HttpServlet {

    @Autowired
    CartRepository cartRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(WebApplication.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (request.getSession().getAttribute("usertype") != null && request.getSession().getAttribute("usertype").equals("logged")) {
            List<String> params = Collections.list(request.getParameterNames());

            LOGGER.info(params.toString());
            String userid = params.get(1);
            String isbn = params.get(2);
            Integer qty = 1;

            Cart c = cartRepository.getCartByIsbn(isbn);

            if (c != null) {
                qty = c.getQty() + 1;
                cartRepository.deleteById(isbn);
            }

            cartRepository.save(new Cart(userid, isbn, qty));
        }
        request.getRequestDispatcher("/index/loaded").forward(request, response);
    }

}
