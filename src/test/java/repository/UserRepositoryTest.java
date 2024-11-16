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
    public void init() {
        userRepository = new UserRepository();
    }

    @Test
    public void findAllTest() {
        List<User> users = this.userRepository.findAll();
        Assertions.assertEquals(2, users.size());
    }

    @Test
    public void findByInputTest() {
        List<User> users = this.userRepository.findByInput("role", "USER");
        Assertions.assertEquals(2, users.size());
    }

    @Test
    public void findByUsernameTest() {
        String username = "23020064";
        Optional<User> user = this.userRepository.findByUsername(username);
        Assertions.assertNotNull(user.get());
    }
}
