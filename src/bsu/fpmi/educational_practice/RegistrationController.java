package bsu.fpmi.educational_practice;

import god.help.me.model.User;
import god.help.me.UserManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = ((String) req.getParameter("password")).trim();
        String login = ((String)  req.getParameter("login")).trim();

        User user = null;
        try {
            user = UserManager.getInstance().registerUser(password,login);
        } catch (Exception e) {
            req.getSession().setAttribute("error", e);
            getServletContext().getRequestDispatcher("/registration_error.jsp").forward(req, resp);
        }

        if (user != null) {
            req.getSession().setAttribute("user", user);
            getServletContext().getRequestDispatcher("/user_created.jsp").forward(req, resp);
        }
    }
}
