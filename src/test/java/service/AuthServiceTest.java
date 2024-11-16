package service;

import app.domain.DTO.PasswordChangeDTO;
import app.domain.User;
import app.repository.BookRepository;
import app.repository.UserRepository;
import app.service.mainService.BookService;
import app.service.mainService.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class AuthServiceTest {

    private UserService userService;

    @BeforeEach
    public void initialize() {
        userService = new UserService(new UserRepository());
    }

    @Test
    void testHandleUpdatePassword() {
        PasswordChangeDTO userDTO = new PasswordChangeDTO("23020064", "123",
                "23020064", "23020064");
        this.userService.handleUpdatePassword(userDTO);
        User user = this.userService.findByUsername(userDTO.getUsername());
        Assertions.assertEquals(userDTO.getNewPassword(), user.getPassword());
    }
}
