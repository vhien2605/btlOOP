package app.repository;

import app.config.DbConfig;
import app.domain.Comment;
import app.domain.DTO.CommentDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentRepository implements CrudRepository<Comment, Integer> {

    /**
     * Find all {@link Comment} mapping from database.
     *
     * @return {@code List<Comment>}
     */
    @Override
    public List<Comment> findAll() {
        List<Comment> list = new ArrayList<>();
        String query = "SELECT * FROM comment";
        try (Connection connection = DbConfig.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                list.add(new Comment(
                        resultSet.getInt("id"),
                        resultSet.getString("userId"),
                        resultSet.getString("bookId"),
                        resultSet.getString("information"),
                        resultSet.getString("date"))
                );
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("error in findAll function in Comment repo");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Find comment by id repository method.
     *
     * @param id Comment's id want to query from database
     * @return {@code Optional<Comment>} wrapper for solving null pointer
     */
    @Override
    public Optional<Comment> findById(Integer id) {
        String query = "SELECT * FROM comment WHERE id = ?";
        try (Connection connection = DbConfig.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Comment comment = new Comment(
                        resultSet.getInt("id"),
                        resultSet.getString("userId"),
                        resultSet.getString("bookId"),
                        resultSet.getString("information"),
                        resultSet.getString("date")
                );
                resultSet.close();
                return Optional.of(comment);
            }
        } catch (SQLException e) {
            System.out.println("error in findById function in Comment repo");
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Delete {@link Comment} repository method.
     *
     * @param id Object's ID you want to delete from {@link Comment} entity.
     * @return {@code true/false} if delete successfully or not
     */
    @Override
    public boolean deleteById(Integer id) {
        String query = "DELETE FROM comment WHERE id = ?";
        try (Connection connection = DbConfig.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int count = statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in deleteById function in comment repo");
            return false;
        }
    }

    /**
     * Save {@link Comment} to the database.
     *
     * @param comment Object want to save to database (mapping to {@link Comment} type table)
     * @return {@code true/false}
     */
    @Override
    public boolean save(Comment comment) {
        return false;
    }

    /**
     * Count all the document {@link  Comment } in database.
     *
     * @return the number of {@link Comment}
     */
    @Override
    public int count() {
        String query = "SELECT COUNT(*) FROM comment";
        try (Connection connection = DbConfig.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                int result = rs.getInt(1);
                rs.close();
                return result;
            }
        } catch (SQLException e) {
            System.out.println("error in count function in Book repo");
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * Get all commentDTO.
     *
     * @return {@code List<CommentDTO>}
     */
    public List<CommentDTO> getAllCommentDTOByBookId(String bookId) {
        List<CommentDTO> list = new ArrayList<>();
        String query = "SELECT * FROM comment " +
                "JOIN user " +
                "ON comment.userId=user.id " +
                "WHERE comment.bookId = ?";
        try (Connection connection = DbConfig.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new CommentDTO(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getString("bookId"),
                                resultSet.getString("information"),
                                resultSet.getString("date")
                        )
                );
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("error in findAll function in Comment repo");
            e.printStackTrace();
        }
        return list;
    }
}
