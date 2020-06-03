package bsu.fpmi.educational_practice;

import god.help.me.model.User;
import god.help.me.UserManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = ((String) req.getParameter("password")).trim();
        String login = ((String) req.getParameter("login")).trim();

        try {
            User user = UserManager.getInstance().tryLogin(login, password);
            req.getSession().setAttribute("user", user);
            getServletContext().getRequestDispatcher("/usermenu.jsp").forward(req, resp);
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/login_error.jsp").forward(req, resp);
        }
    }
}
