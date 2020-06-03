package god.help.me;

import god.help.me.model.User;
import god.help.me.repository.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager {
    private static UserManager instance;

    private DataBaseManager repository;
    private ArrayList<User> usersList;

    private UserManager() {
        repository = DataBaseManager.getInstance();
        try {
            usersList = repository.getUsersList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserManager getInstance() {
        if (instance == null)
            instance = new UserManager();
        return instance;
    }

    public User getByLogin(String login){
        for(User user:usersList){
            if(user.getLogin().equals(login))
                return user;
        }
        throw new RuntimeException("User not found");
    }

    public User registerUser(String password, String login) throws InvalidNameException, InvalidPasswordException, UserExistException, IOException {
        if (password.length() < 6 || password.length() > 18)
            throw new InvalidPasswordException("Password length must be between 6 and 18!");
        if (password.contains(":"))
            throw new InvalidNameException("Invalid password value (remove ':')!");

        User user = null;

        synchronized (instance) {
            for (User u : usersList) {
                if (u.getLogin().equals(login))
                    throw new UserExistException();
            }

            user = new User(IdProvider.createId(), password, login);

            try {
                repository.addUser(user);
                usersList = repository.getUsersList();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }

        return user;
    }

    public User tryLogin(String login, String password) throws Exception {
        for (User u : usersList) {
            if (u.getLogin().equals(login))
                if (u.getPassword().equals(password))
                    return u;
                else throw new Exception();
        }
        throw new Exception();
    }
}

