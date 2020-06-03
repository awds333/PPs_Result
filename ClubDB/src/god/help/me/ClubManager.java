package god.help.me;

import god.help.me.model.Club;
import god.help.me.model.User;
import god.help.me.repository.DataBaseManager;
import god.help.me.repository.IdProvider;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class ClubManager {
    private static ClubManager instance;

    private ArrayList<Club> clubs;
    private DataBaseManager dataBaseManager;

    public static ClubManager getInstance() {
        if (instance == null)
            instance = new ClubManager();
        return instance;
    }

    private ClubManager() {
        dataBaseManager = DataBaseManager.getInstance();
        try {
            clubs = dataBaseManager.getClubs();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            clubs = new ArrayList<>();
        }
    }

    public Club myClub(User user) {
        for (Club club : clubs)
            if (club.getOwner().equals(user))
                return club;
        return null;
    }

    public ArrayList<Club> getClubs() {
        try {
            clubs = dataBaseManager.getClubs();
            return clubs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void addModerator(User user, Club club){
        club.addModerator(user);
        try {
            dataBaseManager.addClub(club);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean createClub(User owner, String name) {
        try {
            for (Club club : clubs)
                if (club.getName().equals(name))
                    return false;
            Club club = new Club(IdProvider.createId(), owner, name);
            clubs.add(club);
            dataBaseManager.addClub(club);
            return true;
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
