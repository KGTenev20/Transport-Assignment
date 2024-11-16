package DataAccessLayer.repositories;

import DataAccessLayer.models.User;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static Connection connection = null;
    public UserRepository() {
        DBConnection dbConnection = new DBConnection();
        connection = DBConnection.getInstance();
    }

    private static User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("UserId"));
        user.setUsername(resultSet.getString("Username"));
        return user;
    }

    public static List<User> getAllUsers(){
        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM UserTable";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(mapResultSetToUser(resultSet));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return users;
    }

    public static User getUserById(int userId){
        String query = "SELECT * FROM UserTable WHERE UserId = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToUser(resultSet);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public static void createUser(User user){
        String query = "INSERT INTO UserTable (Username) VALUES (?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, user.getUsername());

            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateUser(User updatedUser){
        String query = "UPDATE UserTable SET Username = ? WHERE UserId = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, updatedUser.getUsername());
            preparedStatement.setInt(2, updatedUser.getUserId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No user found with ID: " + updatedUser.getUserId());
            } else {
                System.out.println("User updated successfully!");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void deleteUser(int UserId){
        String query = "DELETE FROM UserTable WHERE UserId = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, UserId);

            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
