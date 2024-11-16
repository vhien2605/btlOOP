package app.service.mainService;

import app.domain.DTO.PasswordChangeDTO;
import app.domain.DTO.RegisterUserDTO;
import app.domain.User;
import app.repository.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    /**
     * Inject object by controller.
     *
     * @param userRepository add accessData layer for {@link User} query
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * find all {@link User} method.
     *
     * @return {@code ObservableList<User>} to the front-end layer
     */
    public ObservableList<User> getAllUsers() {
        List<User> user = this.userRepository.findAll();
        return FXCollections.observableList(user);
    }

    /**
     * Find by id method.
     *
     * @param userID {@link User}'s id
     * @return {@link User} which has input id
     */
    public User findById(String userID) {
        Optional<User> wrapperResult = this.userRepository.findById(userID);
        return wrapperResult.orElse(null);
    }

    /**
     * handle update user service.
     *
     * @param user new {@link User} to replace the current {@link User}
     * @return {@code boolean} if update in repo success or failed
     */
    public boolean handleUpdateOne(User user) {
        return this.userRepository.updateOne(user);
    }

    /**
     * Handle save {@link User} logic.
     *
     * @param user {@link User} entity want to save to database
     */
    public boolean handleSaveUser(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Handle save {@link User} method.
     *
     * @param user userDTO from front-end
     * @return {@code true or false}
     */
    public boolean handleSaveUser(RegisterUserDTO user) {
        return handleSaveUser(new User(user.getId(),
                user.getUsername(),
                user.getPassword(),
                "USER",
                user.getName(),
                "",
                user.getEmail(),
                ""
        ));
    }

    /**
     * Delete {@link User} method
     *
     * @param id {@link User} id want to delete
     * @return {@code true/false} when delete success or failed
     */
    public boolean deleteUser(String id) {
        return this.userRepository.deleteById(id);
    }

    /**
     * find {@link User} with username and password.
     *
     * @param username username
     * @param password password
     * @return User with mapping username and password
     */
    public User findByUsernameAndPassword(String username, String password) {
        Optional<User> wrapperResult = this.userRepository.findByUsernameAndPassword(username, password);
        return wrapperResult.orElse(null);
    }

    /**
     * Find {@link User} by username service.
     *
     * @param username username
     * @return User with the mapping username or null if not exists
     */
    public User findByUsername(String username) {
        Optional<User> wrapperResult = this.userRepository.findByUsername(username);
        return wrapperResult.orElse(null);
    }

    /**
     * Find {@link User} by email service.
     *
     * @param email email
     * @return User with the mapping email or null if not exists
     */
    public User findByEmail(String email) {
        Optional<User> wrapperResult = this.userRepository.findByUsername(email);
        return wrapperResult.orElse(null);
    }

    /**
     * update {@link User} method.
     *
     * @param user new user
     * @return boolean
     */
    public boolean updateOne(User user) {
        return this.userRepository.update(user);
    }

    /**
     * Find {@link User} by any col.
     *
     * @param col   col
     * @param value col's value
     * @return {@code ObservableList<User>}
     */
    public ObservableList<User> search(String col, String value) {
        return FXCollections.observableList(this.userRepository.findByInput(col, value));
    }

    /**
     * Handle update password method.
     *
     * @param userDTO userDTO
     * @return {@code true/false}
     */
    public boolean handleUpdatePassword(PasswordChangeDTO userDTO) {
        User user = this.findByUsername(userDTO.getUsername());
        System.out.println(userDTO.getUsername());
        user.setPassword(userDTO.getNewPassword());
        return this.updateOne(user);
    }
}
