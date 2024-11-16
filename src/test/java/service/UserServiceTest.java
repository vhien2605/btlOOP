package service;

import app.domain.User;
import app.repository.UserRepository;
import app.service.mainService.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void initialize() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByIdTest() {
        Mockito.when(userRepository.findById("23020064")).thenReturn(Optional.of(new User("23020064", "hvu6582"
                , "hienhien123@", "ADMIN", "Vu Minh Hien", "Nam Dinh", "hvu6582@gmail.com"
                , "0368379729")));
        Mockito.when(userRepository.findById("")).thenReturn(Optional.empty());

        User result1 = userService.findById("23020064");
        User result2 = userService.findById("");
        Assertions.assertEquals(result1.getName(), "Vu Minh Hien");
        Assertions.assertNull(result2);
    }

    @Test
    public void findByUsernameTest() {
        Mockito.when(userRepository.findByUsername("23020064")).thenReturn(Optional.of(new User("23020064", "hvu6582"
                , "hienhien123@", "ADMIN", "Vu Minh Hien", "Nam Dinh", "hvu6582@gmail.com"
                , "0368379729")));
        User user = this.userService.findByUsername("23020064");
        Assertions.assertNotNull(user);
    }
}
