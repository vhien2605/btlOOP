package repository;

import app.domain.User;
import app.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class UserRepositoryTest {
    private UserRepository userRepository;

    @BeforeEach
    public void initialize() {
        System.setProperty("db.config", "database-testing.properties");
        userRepository = new UserRepository();
    }

    @Test
    public void findAllTest() {
        List<User> users = this.userRepository.findAll();
        Assertions.assertEquals(3, users.size());
    }

    @Test
    public void findByInputTest() {
        List<User> users = this.userRepository.findByInput("email", "jane.smith@email.com");
        Assertions.assertEquals(1, users.size());
    }

    @Test
    public void findByUsernameTest() {
        String username = "jane_smith";
        Optional<User> user = this.userRepository.findByUsername(username);
        Assertions.assertNotNull(user.get());
    }
}
