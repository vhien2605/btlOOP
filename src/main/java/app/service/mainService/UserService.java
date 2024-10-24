package app.service.mainService;

import app.domain.Book;
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
    public ObservableList<User> getAllStudents() {
        List<User> students = this.userRepository.findAll();
        return FXCollections.observableList(students);
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
}
