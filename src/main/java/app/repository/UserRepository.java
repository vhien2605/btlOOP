package app.repository;

import app.config.DbConfig;
import app.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * {@link UserRepository}
 * Doing all accessData logics in table Student mapping to
 * {@link User} object in Java application
 */
public class UserRepository implements CrudRepository<User, String> {
    /**
     * Find all {@link User} in database.
     *
     * @return Collection of all {@link User} in database
     */
    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String query = "SELECT * FROM user";
        try (Connection connection = DbConfig.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                list.add(new User(resultSet.getString("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber")));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("error in findAll function in User repo");
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * Find the {@link User} entity which have {@code Id} input in database.
     *
     * @param Id Document's {@code Id} want to query from database
     * @return {@code Optional<Student>} wrapper of {@link User} object. Avoid null pointer access error
     */
    @Override
    public Optional<User> findById(String Id) {
        String query = "SELECT * FROM user WHERE id = ?";
        try (Connection connection = DbConfig.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, Id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User student = new User(
                        resultSet.getString("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber"));
                resultSet.close();
                return Optional.of(student);
            }
        } catch (SQLException e) {
            System.out.println("error in findById function in User repo");
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }


    /**
     * Delete document which have {@code Id} input from database.
     *
     * @param Id {@link User}'s {@code Id} want to delete from {@link User}
     */
    @Override
    public boolean deleteById(String Id) {
        String query = "DELETE FROM user WHERE id = ?";
        try (Connection connection = DbConfig.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, Id);
            int count = statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("error in delete function in User repo");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method handle save {@link User}.
     *
     * @param user Object want to save to database (mapping to {@code T} type table)
     */
    @Override
    public void save(User user) {
        String query = "INSERT INTO user(id,username,password,role,name,address,email,phoneNumber)" +
                " VALUES(?,?,?,?,?,?,?,?)";
        try (Connection connection = DbConfig.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getId());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole());
            statement.setString(6, user.getName());
            statement.setString(7, user.getAddress());
            statement.setString(8, user.getEmail());
            statement.setString(9, user.getPhoneNumber());
            int count = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error in save function in User repo");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Count all {@link User} document in database.
     *
     * @return the num of {@link User}'s document in database
     */
    @Override
    public int count() {
        String query = "SELECT COUNT(*) FROM user";
        try (Connection connection = DbConfig.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                int result = rs.getInt(1);
                rs.close();
                return result;
            }
        } catch (SQLException e) {
            System.out.println("error in count function in User repo");
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * Update one {@link User}.
     * <p>
     * The document of {@link User} always one because of the unique id(primary key)
     * </p>
     *
     * @param user the {@link User} want to update
     * @return {@code boolean} when update successfully or failed
     */
    public boolean updateOne(User user) {
        String query = "UPDATE user SET username=?,password=?,role=?,name=?,address=?" +
                ",email=?,phoneNumber=? WHERE id=?";
        try (Connection connection = DbConfig.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getPhoneNumber());
            preparedStatement.setString(8, user.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
