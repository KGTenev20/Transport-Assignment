import DataAccessLayer.models.User;
import DataAccessLayer.repositories.UserRepository;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

        @Test
        @DisplayName("Test: Get All Users")
        void testGetAllUsers() {
            List<User> users = UserRepository.getAllUsers();

            assertNotNull(users);
            assertFalse(users.isEmpty());
        }

        @Test
        @DisplayName("Test: Get User by ID (Existing User)")
        void testGetUserById_existingUser() {
            int userId = 1; // Replace with an existing user ID from your database
            User user = UserRepository.getUserById(userId);

            assertNotNull(user);
            assertEquals(userId, user.getUserId());
        }

        @Test
        @DisplayName("Test: Get User by ID (Non-Existing User)")
        void testGetUserById_nonExistingUser() {
            int userId = -1; // Replace with a non-existing user ID
            User user = UserRepository.getUserById(userId);

            assertNull(user);
        }@Test
        @DisplayName("Test: Create User")
        void testCreateUser() {
            User newUser = new User("NewUser");

            UserRepository.createUser(newUser);

            int createdUserId = newUser.getUserId();
            User retrievedUser = UserRepository.getUserById(createdUserId);

            assertNotNull(retrievedUser);
            assertEquals(newUser.getUsername(), retrievedUser.getUsername());

            // Clean up - delete the created user
            UserRepository.deleteUser(createdUserId);
        }

        @Test
        @DisplayName("Test: Update User")
        void testUpdateUser() {
            int userIdToUpdate = 1; // Replace with an existing user ID from your database

            User existingUser = UserRepository.getUserById(userIdToUpdate);
            assertNotNull(existingUser);

            String updatedUsername = "UpdatedUsername";
            existingUser.setUsername(updatedUsername);

            UserRepository.updateUser(existingUser);

            User updatedUser = UserRepository.getUserById(userIdToUpdate);
            assertNotNull(updatedUser);
            assertEquals(updatedUsername, updatedUser.getUsername());
        }

        @Test
        @DisplayName("Test: Delete User")
        void testDeleteUser() {
            User newUser = new User("UserToDelete");

            UserRepository.createUser(newUser);

            int createdUserId = newUser.getUserId();
            User retrievedUser = UserRepository.getUserById(createdUserId);

            assertNotNull(retrievedUser);

            UserRepository.deleteUser(createdUserId);

            User deletedUser = UserRepository.getUserById(createdUserId);
            assertNull(deletedUser);
        }
    }

