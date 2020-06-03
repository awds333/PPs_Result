package god.help.me.model;

public class User {
    private final int id;
    private final String password;
    private final String login;

    public User(int id, String password, String login) {
        this.id = id;
        this.password = password;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(User.class))
            return ((User)obj).id == id;
        return false;
    }
}
