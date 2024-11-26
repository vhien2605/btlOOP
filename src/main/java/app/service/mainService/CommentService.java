package app.service.mainService;

import app.domain.Comment;
import app.domain.DTO.CommentDTO;
import app.repository.CommentRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

/**
 * Service class attribute for Comment.
 */
public class CommentService {
    private final CommentRepository commentRepository;

    /**
     * Constructor.
     *
     * @param commentRepository inject dependency by constructor
     */
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public ObservableList<Comment> getAllComments() {
        return FXCollections.observableList(this.commentRepository.findAll());
    }

    public Comment findById(int id) {
        Optional<Comment> wrapper = this.commentRepository.findById(id);
        return wrapper.orElse(null);
    }
    
    public List<CommentDTO> findAllCommentDTO() {
        return FXCollections.observableList(this.commentRepository.getAllCommentDTO());
    }
}
