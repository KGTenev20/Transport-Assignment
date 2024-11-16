package PresentationLayer.menus;

import PresentationLayer.controller.UserController;
import DataAccessLayer.models.User;

import java.util.List;
import java.util.Scanner;

public class UserMenu{
    private UserController userController;
    private Scanner scanner;

    public UserMenu() {
        this.userController = new UserController();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            System.out.println("User Management Menu");
            System.out.println("1. Add User");
            System.out.println("2. Update Username");
            System.out.println("3. Delete User");
            System.out.println("4. Display User");
            System.out.println("5. Display All Users");
            System.out.println("0. Return");
            System.out.println("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    updateUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    viewUserById();
                    break;
                case 5:
                    viewAllUsers();
                    break;
                case 0:
                    System.out.println("Exiting User Management Menu. Goodbye!");
                    break;
            }
        } while (choice != 0);
    }

    private void viewAllUsers() {
        List<User> users = userController.getAllUsers();
        displayUsers(users);
    }

    private void viewUserById() {
        System.out.println("Enter User ID: ");
        int userId = scanner.nextInt();
        User user = userController.getUserById(userId);
        if (user != null) {
            displayUser(user);
        } else {
            System.out.println("User not found with ID: " + userId);
        }
    }

    private void addUser() {
        System.out.println("Enter User Details:");
        System.out.println("Username: ");
        String Username = scanner.nextLine();

        User newUser = new User(Username);
        userController.createUser(newUser);
        System.out.println("User added successfully!");
    }

    private void updateUser() {
        System.out.println("Enter User ID to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User existingUser = userController.getUserById(userId);
        if (existingUser != null) {
            System.out.println("Enter updated User Details:");
            System.out.println("Username: ");
            String Username = scanner.nextLine();

            existingUser.setUsername(Username);
            userController.updateUser(existingUser);
            System.out.println("User updated successfully!");
        } else {
            System.out.println("User not found with ID: " + userId);
        }
    }

    private void deleteUser() {
        System.out.println("Enter User ID to delete: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User existingUser = userController.getUserById(userId);
        if (existingUser != null) {
            userController.deleteUser(userId);
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User not found with ID: " + userId);
        }
    }

    private void displayUsers(List<User> users) {
        System.out.println("List of Users:");
        for (User user : users) {
            displayUser(user);
        }
    }

    private void displayUser(User user) {
        System.out.println("User ID: " + user.getUserId());
        System.out.println("Username: " + user.getUsername());
        System.out.println("---------------------------------");
    }
}