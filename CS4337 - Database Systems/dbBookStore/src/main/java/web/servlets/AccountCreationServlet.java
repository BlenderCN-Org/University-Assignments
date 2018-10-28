package web.servlets;

import core.WebApplication;
import db.Members;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import springdb.MembersRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/AccountCreationServlet", loadOnStartup = 1)
public class AccountCreationServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebApplication.class);

    @Autowired
    private MembersRepository membersRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        Integer zip = Integer.parseInt(request.getParameter("zip"));
        Long phone = Long.parseLong(request.getParameter("phone"));
        String email = request.getParameter("email");
        String userid = request.getParameter("uname");
        String password = request.getParameter("psw");
        String creditcardtype = request.getParameter("creditcardtype");
        String creditcardnumber = request.getParameter("creditcardnumber");

        LOGGER.debug(new Members(fname, lname, address, city, state, zip, phone, email, userid, password, creditcardtype, creditcardnumber).toString());

        // Performs the Query(value = "INSERT INTO Members (values...) VALUES (values...);")
        if (membersRepository.existsById(userid)) {
            request.setAttribute("errorMessage", "Member with Username \'" + userid + "\' already exists!");
            request.getRequestDispatcher("/account-creation").forward(request, response);
        } else {
            membersRepository.save(new Members(fname, lname, address, city, state, zip, phone, email, userid, password, creditcardtype, creditcardnumber));
            request.setAttribute("errorMessage", "Account Successfully Created!");
            request.getRequestDispatcher("/index").include(request, response);
        }
    }
}