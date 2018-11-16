package web.servlets;

import core.WebApplication;
import db.Books;
import db.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import springdb.BooksRepository;
import springdb.CartRepository;
import web.classes.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/cart", loadOnStartup = 1)
public class CartServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebApplication.class);

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userid = request.getSession().getAttribute("userid").toString();

        List<Cart> cartArrayList = cartRepository.getCartByUserId(userid);
        List<CartItem> cartItemsList = new ArrayList<>();

        for (Cart item : cartArrayList) {
            Books b = booksRepository.findByIsbn(item.getIsbn());
            cartItemsList.add(new CartItem(b.getIsbn(), b.getTitle(), Double.parseDouble(b.getPrice()), item.getQty()));
        }

        Double total = 0.0;
        for (CartItem cartItem : cartItemsList) {
            total += cartItem.getTotalDouble();
        }
        Double tax = total*0.15;
        Double grandTotal = total + tax;

        request.getSession().setAttribute("subtotal", String.format("%.2f", total));
        request.getSession().setAttribute("tax", String.format("%.2f", tax));
        request.getSession().setAttribute("grandtotal", String.format("%.2f", grandTotal));

        request.getSession().setAttribute("cartitems", cartItemsList);
        request.getRequestDispatcher("/cart/loaded").include(request, response);

    }
}
