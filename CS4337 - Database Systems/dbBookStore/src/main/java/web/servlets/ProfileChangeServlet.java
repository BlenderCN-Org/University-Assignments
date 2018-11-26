package web.servlets;

import db.Members;
import org.springframework.beans.factory.annotation.Autowired;
import springdb.MembersRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ProfileChangeServlet", loadOnStartup = 1)
public class ProfileChangeServlet extends HttpServlet {

    @Autowired
    MembersRepository membersRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = (String) request.getSession().getAttribute("userid");

        Members members = membersRepository.findByUserId(username);

        String fname = (request.getParameter("fname").equals("") ? members.getFname() : request.getParameter("fname"));
        String lname = (request.getParameter("lname").equals("") ? members.getLname() : request.getParameter("lname"));
        String address = (request.getParameter("address").equals("") ? members.getAddress() : request.getParameter("address"));
        String city = (request.getParameter("city").equals("") ? members.getCity() : request.getParameter("city"));
        String state = (request.getParameter("state").equals("") ? members.getState() : request.getParameter("state"));
        Integer zip = Integer.parseInt((request.getParameter("zip").equals("") ? members.getZip().toString() : request.getParameter("zip")));
        Long phone = Long.parseLong((request.getParameter("phone").equals("") ? members.getPhone().toString() : request.getParameter("phone")));
        String email = (request.getParameter("email").equals("") ? members.getEmail() : request.getParameter("email"));
        String creditcardtype = (request.getParameter("creditcardtype").equals("") ? members.getCreditcardtype() : request.getParameter("creditcardtype"));
        String creditcardnumber = (request.getParameter("creditcardnumber").equals("") ? members.getCreditcardnumber() : request.getParameter("creditcardnumber"));

        membersRepository.save(new Members(fname, lname, address, city, state, zip, phone, email, members.getUserid(), members.getPassword(), creditcardtype, creditcardnumber));
        request.setAttribute("errorMessage", "Account changed!");
        request.getRequestDispatcher("/index").include(request, response);
    }

}
