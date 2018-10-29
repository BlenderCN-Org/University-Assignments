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
import java.util.Collection;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/LoginAcceptanceServlet", loadOnStartup = 1)
public class LoginAcceptanceServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebApplication.class);

    @Autowired
    private MembersRepository membersRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        LOGGER.info("Login Status: " + request.getParameter("logout-button") + ", " + request.getParameter("next-button"));

        Object s;
        Enumeration<String> collection = request.getParameterNames();
        while(collection.hasMoreElements()) {
            s = collection.nextElement();
            LOGGER.info(s.toString());
        }

        if (request.getParameter("logout-button") != null) {
            doLogout(request, response);
        } else if (request.getParameter("next-button") != null) {
            doLogin(request, response);
        }

    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("usertype", "notlogged");
        request.getSession().setAttribute("userid", null);
        request.setAttribute("errorMessage", "User has been logged out!");
        request.getRequestDispatcher("/index").include(request, response);
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userid = request.getParameter("uname");
        String password = request.getParameter("psw");

        Members m = membersRepository.findByUserId(userid);

        if (m == null) {
            request.setAttribute("errorMessage", "Invalid Username or Password!");
            request.getRequestDispatcher("/login").forward(request, response);
        } else if (!m.getPassword().equals(password)) {
            request.setAttribute("errorMessage", "Invalid Username or Password!");
            request.getRequestDispatcher("/login").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "You Are Now Logged In!!");
            request.getSession().setAttribute("usertype", "logged");
            request.getSession().setAttribute("userid", userid);
            request.getRequestDispatcher("/index").include(request, response);
        }
    }

}
