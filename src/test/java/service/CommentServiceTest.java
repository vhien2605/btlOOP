package service;

import app.domain.Comment;
import app.repository.CommentRepository;
import app.service.mainService.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    public void initialize() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void findCommentByIdTest() {
        int testId = 12;
        Mockito.when(this.commentRepository.findById(testId)).thenReturn(
                Optional.of(new Comment(testId, "", "", "", ""))
        );
        Comment comment = this.commentService.findById(testId);
        Assertions.assertEquals(testId, comment.getId());
        Assertions.assertNotNull(comment);
    }
}
