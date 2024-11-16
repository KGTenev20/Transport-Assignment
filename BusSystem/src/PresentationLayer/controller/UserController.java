package PresentationLayer.controller;

import BusinessLayer.service.UserService;
import DataAccessLayer.models.User;

import java.util.List;

public class UserController {
    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public User getUserById(int userId) {
        return userService.getUserById(userId);
    }

    public void createUser(User user) {
        userService.createUser(user);
    }

    public void updateUser(User user) {
        userService.updateUser(user);
    }

    public void deleteUser(int userId) {
        userService.deleteUser(userId);
    }
}