package web.servlets;

import core.WebApplication;
import db.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import springdb.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(value = "/SubmitOrderServlet", loadOnStartup = 1)
public class SubmitOrderServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebApplication.class);

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OdetailsRepository odetailsRepository;

    @Autowired
    MembersRepository membersRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String typeB = request.getParameter("next-button");
        String userid = (String) request.getSession().getAttribute("userid");

        String shipaddress = "";
        String shipcity = "";
        String shipzip = "";
        String state = "";
        String creditcardnumber = "";
        String creditcardtype = "";

        LOGGER.info("typeB: " + typeB);

        if (typeB != null) {
            shipaddress = request.getParameter("shipaddress");
            shipcity = request.getParameter("shipcity");
            shipzip = request.getParameter("shipzip");
            state = request.getParameter("state");
            creditcardnumber = request.getParameter("creditcardnumber");
            creditcardtype = request.getParameter("creditcardtype");
        } else {
            Members m = membersRepository.findByUserId(userid);
            shipaddress = m.getAddress();
            shipcity = m.getCity();
            shipzip = String.valueOf(m.getZip());
            state = m.getState();
            creditcardnumber = m.getCreditcardnumber();
            creditcardtype = m.getCreditcardtype();
        }

        int ono = 0;
        while (true) {
            ono = new Random().nextInt(99999);
            try {
                Orders o = ordersRepository.findByOno(ono);
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (creditcardnumber.length() == 16) {
            Members m = membersRepository.findByUserId(userid);
            Members t = m;
            t.setCreditcardnumber(creditcardnumber);
            t.setCreditcardtype(creditcardtype);
            membersRepository.save(t);
        }

        Calendar cal = Calendar.getInstance();
        Date shi = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date rec = cal.getTime();


        Orders o = new Orders(userid, ono, rec, shi, shipaddress, shipcity, state, 77381);

        LOGGER.info("order: " + o);

        ordersRepository.save(o);

        List<Cart> cartList = cartRepository.getCartByUserId(userid);
        LOGGER.info("cart: " + cartList);

        // Clear cart after order HERE

        for (Cart cart : cartList) {
            Books b = booksRepository.findByIsbn(cart.getIsbn());
            odetailsRepository.save(new Odetails(ono, cart.getIsbn(), cart.getQty(), (b.getPriceDouble() * cart.getQty())));
        }

        request.getSession().setAttribute("cartitems", new ArrayList<>());
        cartRepository.deleteAll(cartList);
        request.getRequestDispatcher("/index/loaded").forward(request, response);
    }
}
