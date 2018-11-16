package web.servlets;

import core.WebApplication;
import db.Books;
import db.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.classes.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(value = "/CartRemoveServlet", loadOnStartup = 1)
public class CartRemoveServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebApplication.class);


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<CartItem> cartItemsList = (List<CartItem>) request.getSession().getAttribute("cartitems");
        List<String> params = Collections.list(request.getParameterNames());

        String isbn = params.get(0);
        String type = params.get(1);

        LOGGER.info("type" + type);

        if (type.equals("rem")) {
            for (CartItem item : cartItemsList) {
                if (item.getIsbn().equals(isbn)) {
                    if (item.getQty() > 1) {
                        item.setQty(item.getQty() - 1);
                        item.setTotal(item.getTotalDouble() - item.getPriceDouble());
                    } else {
                        cartItemsList.remove(item);
                    }
                    break;
                }
            }
        } else if (type.equals("inc")) {
            for (CartItem item : cartItemsList) {
                if (item.getIsbn().equals(isbn)) {
                    item.setQty(item.getQty() + 1);
                    item.setTotal(item.getTotalDouble() + item.getPriceDouble());
                    break;
                }
            }
        }

        Double total = 0.0;
        for (CartItem cartItem : cartItemsList) {
            total += cartItem.getTotalDouble();
        }
        Double tax = total * 0.15;
        Double grandTotal = total + tax;

        request.getSession().setAttribute("subtotal", String.format("%.2f", total));
        request.getSession().setAttribute("tax", String.format("%.2f", tax));
        request.getSession().setAttribute("grandtotal", String.format("%.2f", grandTotal));

        request.getSession().setAttribute("cartitems", cartItemsList);
        request.getRequestDispatcher("/cart/loaded").forward(request, response);

    }
}
