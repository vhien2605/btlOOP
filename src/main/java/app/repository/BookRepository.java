package app.repository;

import app.config.DbConfig;
import app.domain.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepository implements CrudRepository<Book, Integer> {
    /** this function will get all books document in database
     * @return return list of all books in database
     * @throws SQLException if there are any error when excute query or getConnection
     */
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
            System.out.println("error in findAll function in Book repo");
        }
        return list;
    }
    /** Find book by id
     * @param Id book's id want to query(primary key in database)
     * @return return Optional wrapper of Book
     * @throws SQLException if there are any error when excute query or getConnection
     */
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
            System.out.println("error in findById function in Book repo");
        }
        return Optional.empty();
    }

    /**
     * This function is used to delete one book by id in database
     * @param Id Book's id want to delete from database
     * @throws SQLException if there are any error when excute query or getConnection
     */
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
            System.out.println("error in deleteById function in Book repo");
        }
    }

    /**
     * This function is used to save Book object in database
     * @param entity Book object you want to save in Book table in database
     * @throws SQLException if there are any error when excute query or getConnection
     */
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
            System.out.println("error in save function in Book repo");
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function is used to count the num of document in Book database
     * @return the num of document in Book database
     * @throws SQLException if there are any error when excute query or getConnection
     */
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
            System.out.println("error in count function in Book repo");
        }
        return 0;
    }
}
