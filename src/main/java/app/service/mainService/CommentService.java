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

    /**
     * Get all comments service method.
     *
     * @return {@code ObservableList<Comment}
     */
    public ObservableList<Comment> getAllComments() {
        return FXCollections.observableList(this.commentRepository.findAll());
    }

    /**
     * Find by id method.
     *
     * @param id id
     * @return {@link Comment} result by id
     */
    public Comment findById(int id) {
        Optional<Comment> wrapper = this.commentRepository.findById(id);
        return wrapper.orElse(null);
    }

    /**
     * Find all comment by book.
     *
     * @param bookId bookId input
     * @return {@code ObservableList<CommentDTO>} to render at front-end
     */
    public ObservableList<CommentDTO> findAllCommentDTOByBookId(String bookId) {
        return FXCollections.observableList(this.commentRepository.getAllCommentDTOByBookId(bookId));
    }

    /**
     * Handle save {@link Comment} object to table in database.
     *
     * @param comment target comment
     * @return {@code boolean}
     */
    public boolean handleSaveComment(Comment comment) {
        return this.commentRepository.save(comment);
    }
}
