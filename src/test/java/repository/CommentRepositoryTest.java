package repository;

import app.domain.Comment;
import app.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommentRepositoryTest {
    private CommentRepository commentRepository;

    @BeforeEach
    public void initialzie() {
        commentRepository = new CommentRepository();
    }


    @Test
    public void saveCommentTest() {
        Comment comment = new Comment(10, "23020064", "123", "", "");
        this.commentRepository.save(comment);
    }
}
