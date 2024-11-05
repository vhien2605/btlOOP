package app.repository;

import app.config.DbConfig;
import app.domain.BorrowReport;
import app.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReportRepository implements CrudRepository<BorrowReport, Integer> {

    /**
     * Find all Report method.
     *
     * @return List of {@link BorrowReport} to manage the document in library
     * @author hienonichan
     */
    @Override
    public List<BorrowReport> findAll() {
        String query = "SELECT * FROM borrow_report";
        List<BorrowReport> listOfReports = new ArrayList<>();
        try (Connection connection = DbConfig.getInstance().getConnection();
             Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                BorrowReport report = new BorrowReport(
                        resultSet.getInt("id"),
                        resultSet.getString("userId"),
                        resultSet.getString("bookId"),
                        resultSet.getString("borrowDate"),
                        resultSet.getString("returnDate"),
                        resultSet.getString("expectedReturnDate"),
                        resultSet.getString("status")
                );
                listOfReports.add(report);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return listOfReports;
    }

    /**
     * Find {@link BorrowReport} by id.
     *
     * @param id Document's id want to query from database
     * @return {@code Optional<BorrowReport>} wrapper class to avoid
     * Null pointer access error
     */
    @Override
    public Optional<BorrowReport> findById(Integer id) {
        String query = "SELECT * FROM borrow_report WHERE id=?";
        try (Connection connection = DbConfig.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                BorrowReport report = new BorrowReport(
                        resultSet.getInt("id"),
                        resultSet.getString("userId"),
                        resultSet.getString("bookId"),
                        resultSet.getString("borrowDate"),
                        resultSet.getString("returnDate"),
                        resultSet.getString("expectedReturnDate"),
                        resultSet.getString("status")
                );
                resultSet.close();
                return Optional.of(report);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Delete by id method.
     *
     * @param id Object's ID you want to delete from {@code T} entity.
     * @return {@code boolean true/false}
     */
    @Override
    public boolean deleteById(Integer id) {
        String query = "DELETE FROM borrow_report WHERE id=?";
        try (Connection connection = DbConfig.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            int count = preparedStatement.executeUpdate();
            System.out.println(count + " row deleted");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Save {@link BorrowReport} method.
     *
     * @param entity Object want to save to database (mapping to {@code T} type table)
     */
    @Override
    public boolean save(BorrowReport entity) {
        String query = "INSERT INTO borrow_report(userId,bookId,borrowDate,returnDate,expectedReturnDate,status" +
                ") VALUES (?,?,?,?,?,?)";
        try (Connection connection = DbConfig.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, entity.getUserId());
            preparedStatement.setString(2, entity.getBookId());
            preparedStatement.setString(3, entity.getBorrowDate());
            preparedStatement.setString(4, entity.getReturnDate());
            preparedStatement.setString(5, entity.getExpectedReturnDate());
            preparedStatement.setString(6, entity.getStatus());
            int count = preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Count all {@link BorrowReport} document in database.
     *
     * @return the num of {@link BorrowReport}'s document in database
     */
    @Override
    public int count() {
        String query = "SELECT COUNT(*) FROM borrow_report";
        try (Connection connection = DbConfig.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                int result = rs.getInt(1);
                rs.close();
                return result;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Find report by user's name method repository.
     *
     * @param name {@link User}'s name
     * @return List of all {@link BorrowReport} which is exists with the condition in DB
     */
    public List<BorrowReport> findReportByUsername(String name) {
        String query = "SELECT b.id,b.userId,b.bookId,b.borrowDate,b.returnDate,b.expectedReturnDate,b.status" +
                " FROM borrow_report b " +
                "JOIN user u ON b.userId = u.id  WHERE u.name = ?";
        List<BorrowReport> listOfReports = new ArrayList<>();
        try (Connection connection = DbConfig.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BorrowReport report = new BorrowReport(
                        resultSet.getInt("id"),
                        resultSet.getString("userId"),
                        resultSet.getString("bookId"),
                        resultSet.getString("borrowDate"),
                        resultSet.getString("returnDate"),
                        resultSet.getString("expectedReturnDate"),
                        resultSet.getString("status")
                );
                listOfReports.add(report);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return listOfReports;
    }

    /**
     * Find {@link BorrowReport} by status method.
     *
     * @param status status
     * @return {@code List<BorrowReport>}
     */
    public List<BorrowReport> findByStatus(String status) {
        String query = "SELECT * FROM borrow_report WHERE status= ? ";
        List<BorrowReport> listOfReports = new ArrayList<>();
        try (Connection connection = DbConfig.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BorrowReport report = new BorrowReport(
                        resultSet.getInt("id"),
                        resultSet.getString("userId"),
                        resultSet.getString("bookId"),
                        resultSet.getString("borrowDate"),
                        resultSet.getString("returnDate"),
                        resultSet.getString("expectedReturnDate"),
                        resultSet.getString("status")
                );
                listOfReports.add(report);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return listOfReports;
    }

    /**
     * Update {@link BorrowReport} method.
     *
     * @param entity Object of BorrowReport to update in the database
     * @return {@code boolean true/false} indicating whether the update was successful
     */
    public boolean updateOne(BorrowReport entity) {
        String query = "UPDATE borrow_report SET userId = ?, bookId = ?, borrowDate = ?" 
            + ", returnDate = ?, expectedReturnDate = ?, status = ? WHERE id = ?";
        try (Connection connection = DbConfig.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, entity.getUserId());
            preparedStatement.setString(2, entity.getBookId());
            preparedStatement.setString(3, entity.getBorrowDate());
            preparedStatement.setString(4, entity.getReturnDate());
            preparedStatement.setString(5, entity.getExpectedReturnDate());
            preparedStatement.setString(6, entity.getStatus());
            preparedStatement.setInt(7, entity.getId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
