package god.help.me.model;

import java.util.ArrayList;

public class Club {
    private final int id;
    private final User owner;
    private final ArrayList<Session> sessions;
    private final String name;
    private final ArrayList<User> moderators;

    public Club(int id, User owner, String name, ArrayList<Session> sessions, ArrayList<User> moderators) {
        this.id = id;
        this.owner = owner;
        this.sessions = sessions;
        this.name = name;
        this.moderators = moderators;
    }

    public Club(int id, User owner, String name) {
        this(id, owner, name, new ArrayList<>(), new ArrayList<>());
    }

    public int getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public String getName() {
        return name;
    }

    public ArrayList<User> getModerators() {
        return moderators;
    }

    public void addModerator(User user) {
        moderators.add(user);
    }

    public void removeModerator(User user) {
        moderators.remove(user);
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(Club.class))
            return ((Club) obj).id == id;
        return false;
    }
}
