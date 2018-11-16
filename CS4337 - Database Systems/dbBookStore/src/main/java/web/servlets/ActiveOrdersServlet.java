package web.servlets;


import db.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import springdb.OrdersRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/order", loadOnStartup = 1)
public class ActiveOrdersServlet extends HttpServlet {

    @Autowired
    OrdersRepository ordersRepository;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userid = request.getSession().getAttribute("userid").toString();

        List<Orders> ordersList = ordersRepository.findByUserId(userid);

        request.getSession().setAttribute("orderitems", ordersList);
        request.getRequestDispatcher("/order/loaded").include(request, response);

    }

}
