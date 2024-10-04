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
        try {
            Connection connection = DbConfig.getInstance().getConnection();
            String query = "SELECT*FROM book";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                String bookPublisher = resultSet.getString("bookPublisher");
                int bookQuantity = resultSet.getInt("bookQuantity");
                int bookRemaining = resultSet.getInt("bookRemaining");
                list.add(new Book(id, name, author, bookPublisher, bookQuantity, bookRemaining));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("error in findAll function");
        }
        return list;
    }

    @Override
    public Optional<Book> findById(Integer Id) {
        try {
            Connection connection = DbConfig.getInstance().getConnection();
            String query = "SELECT * FROM book WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String author = resultSet.getString("author");
            String bookPublisher = resultSet.getString("bookPublisher");
            int bookQuantity = resultSet.getInt("bookQuantity");
            int bookRemaining = resultSet.getInt("bookRemaining");
            Book book = new Book(id, name, author, bookPublisher, bookQuantity, bookRemaining);
            return Optional.of(book);
        } catch (SQLException e) {
            System.out.println("error in findById function");
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer Id) {
        try {
            Connection connection = DbConfig.getInstance().getConnection();
            String query = "DELETE FROM book WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Id);
            int count = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error in deleteById function");
        }
    }

    @Override
    public void save(Book entity) {
        try {
            Connection connection = DbConfig.getInstance().getConnection();
            String query = "INSERT INTO book(name,author,bookPublisher,bookQuantity,bookRemaining) VALUES(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
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
        try {
            Connection connection = DbConfig.getInstance().getConnection();
            String query = "SELECT COUNT(*) FROM book";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
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
