package view.RequestManagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Request;
import services.navigation.Navigation;
import services.rmiService.RMIClient;
import util.utility.AlertPopUp;
import util.utility.UtilityMethod;
import util.validation.DataValidation;

import java.rmi.RemoteException;

public class RequestBookDetailController {

    @FXML
    private TextField bookNameTextField;

    @FXML
    private TextField authorTextField;

    @FXML
    private TextField editionTextField;

    @FXML
    private TextField publisherTextField;

    @FXML
    private TextArea descriptionTextField;


    @FXML
    private Label nameLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label editionLabel;

    @FXML
    private Label publisherLabel;

    @FXML
    private Label desccriptionLabel;

    @FXML
    private AnchorPane detailAnchorPane;

    @FXML
    private void loadHomeDetail(ActionEvent actionEvent) {
        Navigation navigation = new Navigation();
        navigation.loadHomeDetail(detailAnchorPane);
    }

    @FXML
    private void loadRequestDetail(ActionEvent actionEvent) {
        Navigation navigation = new Navigation();
        navigation.loadRequestBookDetail(detailAnchorPane);
    }

    @FXML
    void makeRequest(ActionEvent event) {
        clearLabels();
        if (validateData()) {
            Request request = new Request();
            request.setrBName(bookNameTextField.getText());
            request.setrDescription(descriptionTextField.getText());
            request.setrBAuthor(authorTextField.getText());
            request.setrBEdition(editionTextField.getText());
            request.setrBPublisher(publisherTextField.getText());
            request.setrDate(UtilityMethod.getCurrentDate());
            request.setrStatus("Pending");
            try {
                if (RMIClient.rmiInterface.requestBook(request)) {
                    AlertPopUp.sentSuccessfully("Book Request Sent Successfully");
                    clearFields();
                } else
                    AlertPopUp.insertionFailed("Failed to send Book Request");
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
        } else {
            datavalidationMessage();
        }
    }

    @FXML
    void clearFields() {
        bookNameTextField.setText("");
        descriptionTextField.setText("");
        authorTextField.setText("");
        editionTextField.setText("");
        publisherTextField.setText("");
    }


    private void clearLabels() {
        nameLabel.setText("");
        desccriptionLabel.setText("");
        authorLabel.setText("");
        editionLabel.setText("");
        publisherLabel.setText("");
    }

    private boolean validateData() {
        return DataValidation.TextFieldNotEmpty(bookNameTextField)
                && DataValidation.TextFieldNotEmpty(bookNameTextField)
                && DataValidation.TextFieldNotEmpty(bookNameTextField)
                && DataValidation.TextFieldNotEmpty(bookNameTextField)

                && DataValidation.isValidMaximumLength(bookNameTextField.getText(), 45)
                && DataValidation.isValidMaximumLength(bookNameTextField.getText(), 400)
                && DataValidation.isValidMaximumLength(bookNameTextField.getText(), 45)
                && DataValidation.isValidMaximumLength(bookNameTextField.getText(), 45);
    }

    private void datavalidationMessage() {
        if (!(DataValidation.TextFieldNotEmpty(bookNameTextField)
                && DataValidation.TextFieldNotEmpty(authorTextField)
                && DataValidation.TextFieldNotEmpty(editionTextField)
                && DataValidation.TextFieldNotEmpty(publisherTextField))) {
            DataValidation.TextFieldNotEmpty(bookNameTextField, nameLabel, "Name of the book cannot be eEmpty");
            DataValidation.TextFieldNotEmpty(authorTextField, authorLabel, "Author field Cannot be Empty!");
            DataValidation.TextFieldNotEmpty(editionTextField, editionLabel, "Edition Field Cannot be Empty!");
            DataValidation.TextFieldNotEmpty(publisherTextField, publisherLabel, "Publisher Field cannot be Empty!");
        }
        if (!(DataValidation.isValidMaximumLength(bookNameTextField.getText(), 45)
                && DataValidation.isValidMaximumLength(authorTextField.getText(), 45)
                && DataValidation.isValidMaximumLength(descriptionTextField.getText(), 400)
                && DataValidation.isValidMaximumLength(editionTextField.getText(), 45)
                && DataValidation.isValidMaximumLength(publisherTextField.getText(), 45))) {
            DataValidation.isValidMaximumLength(bookNameTextField.getText(), 45, nameLabel, "Maximum Character Limit Exceeded, Limit 45");
            DataValidation.isValidMaximumLength(authorTextField.getText(), 45, authorLabel, "Maximum Character Limit Exceeded, Limit 45");
            DataValidation.isValidMaximumLength(descriptionTextField.getText(), 400, desccriptionLabel, "Maximum Character Limit Exceeded, Limit 400");
            DataValidation.isValidMaximumLength(editionTextField.getText(), 45, editionLabel, "Maximum Character Limit Exceeded, Limit 45");
            DataValidation.isValidMaximumLength(publisherTextField.getText(), 45, publisherLabel, "Maximum Character Limit Exceeded, Limit 45");
        }
    }
}
