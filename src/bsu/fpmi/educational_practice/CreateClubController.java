package bsu.fpmi.educational_practice;

import god.help.me.ClubManager;
import god.help.me.model.Club;
import god.help.me.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class CreateClubController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = ((String) req.getParameter("name")).trim();
        User user = (User) req.getSession().getAttribute("user");
        ClubManager clubManager = ClubManager.getInstance();
        ArrayList<Club> clubs = clubManager.getClubs();
        for (Club club : clubs)
            if (club.getName().equals(name))
                getServletContext().getRequestDispatcher("/club_create_error.jsp").forward(req, resp);

        clubManager.createClub(user, name);
        getServletContext().getRequestDispatcher("/club_menu.jsp").forward(req, resp);
    }
}
