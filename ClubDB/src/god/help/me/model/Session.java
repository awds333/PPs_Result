package god.help.me.model;

import java.util.ArrayList;

public class Session {
    private final int id;
    private final String name;
    private final ArrayList<Game> games;

    public Session(int id, String name, ArrayList<Game> games) {
        this.id = id;
        this.name = name;
        this.games = games;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(Session.class))
            return ((Session) obj).id == id;
        return false;
    }
}
