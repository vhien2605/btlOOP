package app.controller.helper;

import app.controller.admin.BookLoanTab.MainBookLoanController;
import app.domain.BorrowReport;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookLoanHelper {
    /**
     * BookLoanTab view constructor to get controller.
     * 
     * @param borrowReport {@link BorrowReport}
     * @return MainBookLoanController to send email to recipient.
     */
    public static MainBookLoanController getBookLoanController(BorrowReport borrowReport) {
        try {
            String path = "/view/admin/bookLoanTab/bookloan_tab.fxml";
            FXMLLoader loader = new FXMLLoader(BookLoanHelper.class.getResource(path));

            Parent root = loader.load();

            Scene tempScene = new Scene(root);
            Stage tempStage = new Stage();
            tempStage.setScene(tempScene);

            MainBookLoanController controller = loader.getController();
            controller.renderData(borrowReport, null);

            return controller;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
