package god.help.me.repository;

public class UserExistException extends Exception {
    public UserExistException() {
        super("User with this name all ready exist!");
    }
}
