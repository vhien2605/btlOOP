package app.service.mainService;

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
}
