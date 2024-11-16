package DataAccessLayer.models;

public class User {

    private int UserId;
    private String Username;

    public User(){
        UserId = 0;
        Username = "Not defined";
    }

    public User(String Username) {
        this.Username = Username;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    @Override
    public String toString(){
        return "UserId = " + UserId + ", Username = " + Username;
    }
}
