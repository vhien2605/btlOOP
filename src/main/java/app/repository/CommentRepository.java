package app.repository;

import app.config.DbConfig;
import app.domain.Comment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentRepository implements CrudRepository<Comment, Integer> {

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
                        resultSet.getString("date")));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("error in findAll function in Comment repo");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<Comment> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Integer integer) {
        return false;
    }

    @Override
    public boolean save(Comment entity) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }
}
