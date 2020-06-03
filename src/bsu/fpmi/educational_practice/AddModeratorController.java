package bsu.fpmi.educational_practice;

import god.help.me.ClubManager;
import god.help.me.UserManager;
import god.help.me.model.Club;
import god.help.me.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddModeratorController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = ((String) req.getParameter("login")).trim();

        try {
            ClubManager clubManager = ClubManager.getInstance();
            UserManager userManager = UserManager.getInstance();

            User user = (User) req.getSession().getAttribute("user");
            User moderator = userManager.getByLogin(login);
            Club club = clubManager.myClub(user);
            for(User u :club.getModerators())
                if(u.getLogin().equals(login))
                    throw new Exception("Moderator all ready added");

            clubManager.addModerator(moderator, club);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            getServletContext().getRequestDispatcher("/club_menu.jsp").forward(req, resp);
        }
    }
}
