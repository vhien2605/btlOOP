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
 *
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
        String query = "SELECT * FROM user WHERE role = 'USER'";
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
     * @return {@code Optional<Student>} wrapper of {@link User} object. Avoid null
     *         pointer access error
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
            System.out.println(count);
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
    public boolean save(User user) {
        String query = "INSERT INTO user(id,username,password,role,name,address,email,phoneNumber)" +
                " VALUES(?,?,?,?,?,?,?,?)";
        try (Connection connection = DbConfig.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getName());
            statement.setString(6, user.getAddress());
            statement.setString(7, user.getEmail());
            statement.setString(8, user.getPhoneNumber());
            int count = statement.executeUpdate();
            System.out.println(count);
            return true;
        } catch (SQLException e) {
            System.out.println("error in save function in User repo");
            System.out.println(e.getMessage());
            return false;
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
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
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

    /**
     * Find user by username and password for authentication.
     *
     * @param username username login
     * @param password password login
     * @return {@code Optional<User>} wrapper checking for user
     */
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        String query = "SELECT * FROM user WHERE username=? AND password=?";
        try (Connection connection = DbConfig.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Access data method find user by username.
     *
     * @param username username of the user want to find in database
     * @return {@code Optional<User>}
     */
    public Optional<User> findByUsername(String username) {
        String query = "SELECT * FROM user WHERE username=?";
        try (Connection connection = DbConfig.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Find {@link User} by email field method.
     *
     * @param email email's student want to search in DB
     * @return {@code Optional<User>} wrapper handling null pointer
     */
    public Optional<User> findByEmail(String email) {
        String query = "SELECT * FROM user WHERE email=?";
        try (Connection connection = DbConfig.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Update {@link User} repository method.
     *
     * @param user new {@link User}
     * @return boolean
     */
    public boolean update(User user) {
        String query = "UPDATE user SET username=?, password=?, role=?, name=?" +
                ", address=?, email=?, phoneNumber=? WHERE id=?";
        try (Connection connection = DbConfig.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getPhoneNumber());
            preparedStatement.setString(8, user.getId());
            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println(rowsUpdated);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating user: " + e.getMessage());
            return false;
        }
    }

    public List<User> findByInput(String col, String value) {
        List<User> list = new ArrayList<>();
        String query = "SELECT * FROM user WHERE " + col + " LIKE ? AND role='USER'";
        try (Connection connection = DbConfig.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + value + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
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
            System.out.println("error in findByInput function in User repo");
            System.out.println(e.getMessage());
        }
        return list;
    }
}
