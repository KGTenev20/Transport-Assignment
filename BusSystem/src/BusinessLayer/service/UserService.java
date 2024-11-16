package BusinessLayer.service;

import DataAccessLayer.models.User;
import DataAccessLayer.repositories.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public List<User> getAllUsers() {
        return UserRepository.getAllUsers();
    }

    public User getUserById(int userId) {
        return userRepository.getUserById(userId);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUser(int userId) {
        userRepository.deleteUser(userId);
    }

    public void createUser(User user) {
        userRepository.createUser(user);
    }
}