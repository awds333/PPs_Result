package god.help.me.repository;

import god.help.me.model.Club;
import god.help.me.model.Game;
import god.help.me.model.Session;
import god.help.me.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class DataBaseManager {
    private static DataBaseManager instance = null;

    private Connection con;

    public static DataBaseManager getInstance() {
        if (instance == null)
            instance = new DataBaseManager();
        return instance;
    }

    private DataBaseManager() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:mafiadatabase.db");
        } catch (ClassNotFoundException | SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public ArrayList<User> getUsersList() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT * FROM Users;");
        while (resultSet.next()) {
            users.add(new User(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(2)));
        }
        return users;
    }

    public void addUser(User user) throws SQLException {
        con.createStatement().executeQuery("INSERT INTO Users VALUES (" + user.getId() + ",'" + user.getLogin() + "','" + user.getPassword() + "');");
    }

    public User getUser(Long id) throws SQLException {
        ResultSet resultSet = con.createStatement().executeQuery("SELECT * FROM Users WHERE ID = " + id + ";");
        if (resultSet.next())
            return new User(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(2));
        throw new SQLException("User not fount");
    }

    public Game getGame(int id) throws SQLException {
        ResultSet resultSet = con.createStatement().executeQuery("SELECT * FROM Games WHERE ID = " + id + ";");
        if (resultSet.next())
            return new Game(resultSet.getInt(1), resultSet.getString(2), getUser(resultSet.getLong(3)));
        throw new SQLException("Game not fount");
    }

    public Session getSession(int id) throws SQLException {
        ResultSet resultSet = con.createStatement().executeQuery("SELECT * FROM Sessions WHERE ID = " + id + ";");
        if (resultSet.next()) {
            ArrayList<Game> games = new ArrayList<>();
            if (!resultSet.getString(3).isEmpty())
                games = new ArrayList(Arrays.asList(Stream.of(resultSet.getString(3).split(" "))
                        .mapToInt(it -> Integer.parseInt(it))
                        .mapToObj(it -> {
                            try {
                                return getGame(it);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            return new Game(-1, "not found", new User(-1, "", "not found"));
                        }).toArray()));

            return new Session(resultSet.getInt(1), resultSet.getString(2), games);
        }
        throw new SQLException("Session not fount");
    }

    public ArrayList<Club> getClubs() throws SQLException {
        ArrayList<Club> clubs = new ArrayList<>();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT * FROM Clubs;");
        while (resultSet.next()) {
            User owner = getUser(resultSet.getLong(3));
            ArrayList<User> moderators = new ArrayList<>();
            if (!resultSet.getString(4).isEmpty())
                moderators = new ArrayList(Arrays.asList(Stream.of(resultSet.getString(4).split(" "))
                        .mapToInt(it -> Integer.parseInt(it))
                        .mapToObj(it -> {
                            try {
                                return getUser((long) it);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            return new User(-1, "", "not found");
                        }).toArray()));

            ArrayList<Session> sessions = new ArrayList<>();
            if (!resultSet.getString(5).isEmpty())
                sessions = new ArrayList(Arrays.asList(Stream.of(resultSet.getString(5).split(" "))
                        .mapToInt(it -> Integer.parseInt(it))
                        .mapToObj(it -> {
                            try {
                                return getSession(it);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            return new Session(-1, "not found", new ArrayList<>());
                        }).toArray()));

            clubs.add(new Club(resultSet.getInt(1), owner, resultSet.getString(2), sessions, moderators));
        }
        return clubs;
    }

    public void addClub(Club club) throws SQLException {
        StringBuilder moderators = new StringBuilder("");
        StringBuilder sessions = new StringBuilder("");

        for (Session session : club.getSessions())
            sessions.append(session.getId() + " ");
        for (User moderator : club.getModerators())
            moderators.append(moderator.getId() + " ");

        PreparedStatement st = con.prepareStatement("INSERT INTO Clubs(ID, NAME, OWNER, MODERATORS, SESSIONS) VALUES(?,?,?,?,?);");

        st.setInt(1, club.getId());
        st.setString(2, club.getName());
        st.setLong(3, club.getOwner().getId());
        st.setString(4, moderators.toString());
        st.setString(5, sessions.toString());

        st.executeUpdate();
    }
}
