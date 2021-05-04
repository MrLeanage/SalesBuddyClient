package view.bookManagement;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Book;
import services.rmiService.RMIClient;
import util.utility.UtilityMethod;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class BookCardController implements Initializable {
    @FXML
    private TextField availableStatusTextField;

    @FXML
    private TextField publisherYearTextField;

    @FXML
    private Label bookNameLabel;

    @FXML
    private TextField languageTextField;

    @FXML
    private Label authorLabel;

    @FXML
    private JFXButton closeCardButton;

    @FXML
    private TextArea desriptionTextField;

    private static Book book = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }

    @FXML
    private void closeCard(ActionEvent event) {
        Stage stage = (Stage) closeCardButton.getScene().getWindow();
        stage.close();
    }


    private void setData() {
        try {
            book = BookDetailController.selectedBook;
            bookNameLabel.setText(book.getbTitle());
            authorLabel.setText("by " + book.getbAuthor());
            desriptionTextField.setText(book.getbDescription());
            languageTextField.setText(book.getbLanguage());
            availableStatusTextField.setText(book.getbAvailability());
            publisherYearTextField.setText(book.getbPublishYear());
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }

        try {
            RMIClient.rmiInterface.addBookView(UtilityMethod.seperateID(book.getbID()));
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
    }
}
