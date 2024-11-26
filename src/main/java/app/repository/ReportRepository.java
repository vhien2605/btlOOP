package app.repository;

import app.config.DbConfig;
import app.domain.Book;
import app.domain.BorrowReport;
import app.domain.DTO.ReportDetail;
import app.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
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
                        resultSet.getString("status"),
                        resultSet.getString("qrcodeUrl")
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
                        resultSet.getString("status"),
                        resultSet.getString("qrcodeUrl")
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
        String query = "INSERT INTO borrow_report(userId,bookId,borrowDate,returnDate,expectedReturnDate,status," +
                "qrcodeUrl) VALUES (?,?,?,?,?,?,?)";
        try (Connection connection = DbConfig.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, entity.getUserId());
            preparedStatement.setString(2, entity.getBookId());
            preparedStatement.setString(3, entity.getBorrowDate());
            preparedStatement.setString(4, entity.getReturnDate());
            preparedStatement.setString(5, entity.getExpectedReturnDate());
            preparedStatement.setString(6, entity.getStatus());
            preparedStatement.setString(7, entity.getQrcodeUrl());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                    System.out.println(entity.getId());
                }
            }
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
                        resultSet.getString("status"),
                        resultSet.getString("qrcodeUrl")
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
                        resultSet.getString("status"),
                        resultSet.getString("qrcodeUrl")
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
        String query = "UPDATE borrow_report SET bookId = ?, borrowDate = ?"
                + ", returnDate = ?, expectedReturnDate = ?, status = ?, qrcodeUrl=? WHERE id = ?";
        try (Connection connection = DbConfig.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, entity.getBookId());
            preparedStatement.setString(2, entity.getBorrowDate());
            preparedStatement.setString(3, entity.getReturnDate());
            preparedStatement.setString(4, entity.getExpectedReturnDate());
            preparedStatement.setString(5, entity.getStatus());
            preparedStatement.setString(6, entity.getQrcodeUrl());
            preparedStatement.setInt(7, entity.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Find records of search results
     *
     * @param col    Search column.
     * @param value  Search value.
     * @param status Status issue.
     * @return List of {@link BorrowReport} to manage the document in library
     */
    public List<BorrowReport> findByInput(String col, String value, String status) {
        List<BorrowReport> list = new ArrayList<>();
        String query = "SELECT * FROM borrow_report WHERE " + col + " LIKE ? AND status LIKE ?";
        try (Connection connection = DbConfig.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + value + "%");
            preparedStatement.setString(2, "%" + status + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new BorrowReport(
                        resultSet.getInt("id"),
                        resultSet.getString("userId"),
                        resultSet.getString("bookId"),
                        resultSet.getString("borrowDate"),
                        resultSet.getString("returnDate"),
                        resultSet.getString("expectedReturnDate"),
                        resultSet.getString("status"),
                        resultSet.getString("qrcodeUrl")
                ));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    /**
     * find by one column method.
     *
     * @param col   col
     * @param value value
     * @return {@code List<BorrowReport>}
     */
    public List<BorrowReport> findByOneColumn(String col, String value) {
        List<BorrowReport> list = new ArrayList<>();
        String query = "SELECT * FROM borrow_report WHERE " + col + " = ?";
        try (Connection connection = DbConfig.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new BorrowReport(
                        resultSet.getInt("id"),
                        resultSet.getString("userId"),
                        resultSet.getString("bookId"),
                        resultSet.getString("borrowDate"),
                        resultSet.getString("returnDate"),
                        resultSet.getString("expectedReturnDate"),
                        resultSet.getString("status"),
                        resultSet.getString("qrcodeUrl")
                ));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return list;
    }


    public List<ReportDetail> getAllReportDetailDTO() {
        String query = "SELECT b1.id,u.name AS userName,b2.name AS bookName,b1.borrowDate,b1.returnDate,b1.expectedReturnDate,b1.status " +
                "FROM borrow_report b1 " +
                "INNER JOIN user u " +
                "ON b1.userId=u.id " +
                "INNER JOIN book b2 " +
                "ON b1.bookId=b2.id ";
        List<ReportDetail> listOfReports = new ArrayList<>();
        try (Connection connection = DbConfig.getInstance().getConnection();
             Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ReportDetail report = new ReportDetail(
                        resultSet.getInt("id"),
                        resultSet.getString("userName"),
                        resultSet.getString("bookName"),
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
     * Special method for update book quantity and report.
     * when only 1 in 2 query failed. Reject all request
     *
     * @param book         {@link Book}
     * @param borrowReport {@link BorrowReport}
     */
    public boolean updateReportAndBookTransaction(BorrowReport borrowReport, Book book) {
        try (Connection connection = DbConfig.getInstance().getConnection()) {
            String query1 = "UPDATE borrow_report SET bookId = ?, borrowDate = ?, returnDate = ?, "
                    + "expectedReturnDate = ?, status = ?, qrcodeUrl = ? WHERE id = ?";
            String query2 = "UPDATE book SET name = ?, author = ?, description = ?, category = ?, "
                    + "bookPublisher = ?, bookQuantity = ?, bookRemaining = ?, imagePath = ? WHERE id = ?";

            connection.setAutoCommit(false);

            try (PreparedStatement stmt1 = connection.prepareStatement(query1);
                 PreparedStatement stmt2 = connection.prepareStatement(query2)) {

                // Cập nhật BorrowReport
                stmt1.setString(1, borrowReport.getBookId());
                stmt1.setDate(2, Date.valueOf(borrowReport.getBorrowDate()));
                stmt1.setDate(3, Date.valueOf(borrowReport.getReturnDate()));
                stmt1.setDate(4, Date.valueOf(borrowReport.getExpectedReturnDate()));
                stmt1.setString(5, borrowReport.getStatus());
                stmt1.setString(6, borrowReport.getQrcodeUrl());
                stmt1.setInt(7, borrowReport.getId());
                stmt1.executeUpdate();

                // Cập nhật Book
                stmt2.setString(1, book.getName());
                stmt2.setString(2, book.getAuthor());
                stmt2.setString(3, book.getDescription());
                stmt2.setString(4, book.getCategory());
                stmt2.setString(5, book.getBookPublisher());
                stmt2.setInt(6, book.getBookQuantity());
                stmt2.setInt(7, book.getBookRemaining());
                stmt2.setString(8, book.getImagePath());
                stmt2.setString(9, book.getId());
                stmt2.executeUpdate();

                // Cam kết transaction
                connection.commit();
                return true;
            } catch (SQLException e) {
                connection.rollback();
                System.err.println("Transaction rolled back due to error: " + e.getMessage());
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Database operation failed.");
            return false;
        }
    }
}
