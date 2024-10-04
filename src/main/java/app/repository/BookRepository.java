package app.repository;

import app.config.DbConfig;
import app.domain.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepository implements CrudRepository<Book, Integer> {

    @Override
    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM book";
        try {
            connection = DbConfig.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                list.add(new Book(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getString("bookPublisher"),
                        resultSet.getInt("bookQuantity"),
                        resultSet.getInt("bookRemaining")));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("error in findAll function");
        }
        return list;
    }

    @Override
    public Optional<Book> findById(Integer Id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM book WHERE id = ?";
        try {
            connection = DbConfig.getInstance().getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, Id);
            resultSet = statement.executeQuery();
            resultSet.next();
            Book book = new Book(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("author"),
                    resultSet.getString("bookPublisher"),
                    resultSet.getInt("bookQuantity"),
                    resultSet.getInt("bookRemaining"));
            return Optional.of(book);
        } catch (SQLException e) {
            System.out.println("error in findById function");
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer Id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM book WHERE id = ?";
        try {
            connection = DbConfig.getInstance().getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, Id);
            int count = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error in deleteById function");
        }
    }

    @Override
    public void save(Book entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "INSERT INTO book(name,author,bookPublisher,bookQuantity,bookRemaining) VALUES(?,?,?,?,?)";
        try {
            connection = DbConfig.getInstance().getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getAuthor());
            statement.setString(3, entity.getBookPublisher());
            statement.setInt(4, entity.getBookQuantity());
            statement.setInt(5, entity.getBookRemaining());
            int count = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error in save function");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int count() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String query = "SELECT COUNT (*) FROM book";
        try {
            connection = DbConfig.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            // resultSet default doesn't ref to any value. must rs.next to ref first value
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error in count function");
        }
        return 0;
    }
}
