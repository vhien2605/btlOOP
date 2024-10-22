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

    public ObservableList<User> getAllStudents() {
        List<User> students = this.userRepository.findAll();
        return FXCollections.observableList(students);
    }

    public User findById(String studentID) {
        Optional<User> wrapperResult = this.userRepository.findById(studentID);
        return wrapperResult.orElse(null);
    }
}
