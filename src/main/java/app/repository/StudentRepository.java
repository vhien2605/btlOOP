package app.repository;

import app.config.DbConfig;
import app.domain.Book;
import app.domain.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository implements CrudRepository<Student, Integer> {
    /**
     * Find all {@link Student} in database
     *
     * @return Collection of all {@link Student} in database
     */
    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM student";
        try {
            connection = DbConfig.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                list.add(new Student(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber")));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("error in findAll function in Student repo");
        }
        return list;
    }

    /**
     * Find the {@link Student} entity which have {@code Id} input in database
     *
     * @param Id Document's {@code Id} want to query from database
     * @return {@code Optional<Student>} wrapper of {@link Student} object. Avoid null pointer access error
     */
    @Override
    public Optional findById(Integer Id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM student WHERE id = ?";
        try {
            connection = DbConfig.getInstance().getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, Id);
            resultSet = statement.executeQuery();
            resultSet.next();
            Student student = new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("email"),
                    resultSet.getString("phoneNumber"));
            return Optional.of(student);
        } catch (SQLException e) {
            System.out.println("error in findById function in Student repo");
        }
        return Optional.empty();
    }


    /**
     * Delete document which have {@code Id} input from database
     *
     * @param Id {@link Student}'s {@code Id} want to delete from {@link Student}
     */
    @Override
    public void deleteById(Integer Id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM student WHERE id = ?";
        try {
            connection = DbConfig.getInstance().getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, Id);
            int count = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error in deleteById function in Student repo");
        }
    }

    /**
     * Save the {@link Student} object mapping to entity in database
     *
     * @param student Object want to save to database (mapping to T type table)
     */
    @Override
    public void save(Student student) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "INSERT INTO book(name,author,bookPublisher,bookQuantity,bookRemaining) VALUES(?,?,?,?,?)";
        try {
            connection = DbConfig.getInstance().getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, student.getName());
            statement.setString(2, student.getAddress());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPhoneNumber());
            int count = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error in save function in Student repo");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Count all {@link Student} document in database
     *
     * @return the num of {@link Student}'s document in database
     */
    @Override
    public int count() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String query = "SELECT COUNT (*) FROM student";
        try {
            connection = DbConfig.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error in count function in Student repo");
        }
        return 0;
    }
}
