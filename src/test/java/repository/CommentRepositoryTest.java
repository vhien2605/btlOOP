package repository;

import app.domain.Comment;
import app.domain.DTO.CommentDTO;
import app.repository.CommentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class CommentRepositoryTest {
    private CommentRepository commentRepository;

    @BeforeEach
    public void initialize() {
        System.setProperty("db.config", "database-testing.properties");
        commentRepository = new CommentRepository();
    }

    @Test
    public void findCommentByIdTest() {
        int id = 1;
        Optional<Comment> comment = this.commentRepository.findById(id);
        Assertions.assertEquals("Great book! Highly recommend it.", comment.get().getInformation());
    }

    @Test
    public void findAllCommentTest() {
        List<Comment> list = this.commentRepository.findAll();
        Assertions.assertEquals(6, list.size());
    }

    @Test
    public void findAllCommentDtoByBookIdTest() {
        String bookId = "1";
        List<CommentDTO> list = this.commentRepository.getAllCommentDTOByBookId(bookId);
        Assertions.assertEquals(1, list.size());
    }
}
