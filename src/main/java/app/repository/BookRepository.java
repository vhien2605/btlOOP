package app.repository;

import app.config.DbConfig;
import app.domain.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepository implements CrudRepository<Book, Long> {

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
        } catch (SQLException e) {
            System.out.println("error in findAll function");
        }
        return list;
    }

    @Override
    public Optional<Book> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public <S extends Book> S save(S entity) {
        return null;
    }

    @Override
    public long count() {
        return 1;
    }
}
