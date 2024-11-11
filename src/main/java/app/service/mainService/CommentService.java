package app.service.mainService;

import app.domain.Comment;
import app.repository.CommentRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Service class attribute for Comment
 */
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public ObservableList<Comment> getAllComments() {
        return FXCollections.observableList(this.commentRepository.findAll());
    }
}
