package service;

import app.domain.Comment;
import app.domain.DTO.CommentDTO;
import app.repository.CommentRepository;
import app.service.mainService.CommentService;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
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

    @Test
    public void findCommentByBookIdTest() {
        String bookId = "123";
        List<CommentDTO> list = List.of(new CommentDTO(12, "", "",
                "123", "", ""));
        Mockito.when(this.commentRepository.getAllCommentDTOByBookId(bookId)).thenReturn(list);
        ObservableList<CommentDTO> result = this.commentService.findAllCommentDTOByBookId(bookId);
        Assertions.assertEquals(1, result.size());
    }
}
