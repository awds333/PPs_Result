package god.help.me.model;

public class Game {
    private final int id;
    private final String name;
    private final User moderator;

    public Game(int id, String name, User moderator) {
        this.id = id;
        this.name = name;
        this.moderator = moderator;
    }

    public User getModerator() {
        return moderator;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(Game.class))
            return ((Game) obj).id == id;
        return false;
    }
}
