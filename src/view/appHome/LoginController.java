package view.appHome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.navigation.Navigation;
import services.rmiService.RMIClient;
import util.authentication.UserAuthentication;
import util.utility.AlertPopUp;
import util.validation.DataValidation;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    private Label serverStatusLabel;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label validationLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setServer();
    }

    @FXML
    public void loginUser(ActionEvent actionEvent) {
        if (fieldValidation()) {
            String authenticateMessage = UserAuthentication.authenticateUser(userNameTextField.getText(), passwordTextField.getText());
            Navigation navigation = new Navigation();
            if (authenticateMessage.equals("true")) {
                navigation.loadHome(baseAnchorPane);
            } else if (authenticateMessage.equals("false")) {
                AlertPopUp.serverFailed();
                setServer();
            } else
                validationLabel.setText(authenticateMessage);
        } else
            fieldValidationMessage();
    }

    private boolean fieldValidation() {
        return DataValidation.TextFieldNotEmpty(userNameTextField.getText())
                && DataValidation.TextFieldNotEmpty(passwordTextField.getText())
                && DataValidation.isValidEmail(userNameTextField.getText());
    }

    private void fieldValidationMessage() {
        if (!(DataValidation.TextFieldNotEmpty(userNameTextField.getText())
                && DataValidation.TextFieldNotEmpty(passwordTextField.getText()))) {
            DataValidation.TextFieldNotEmpty(userNameTextField.getText(), validationLabel, "User Name/Password Fields Cannot be Empty");
            DataValidation.TextFieldNotEmpty(passwordTextField.getText(), validationLabel, "User Name/Password Fields Cannot be Empty");
            DataValidation.isValidEmail(userNameTextField.getText(), validationLabel, "Invalid Email Address ");
        }
    }

    @FXML
    private void setServer() {
        String defaultColor = "-fx-text-fill:  #00B605; ";
        String errortColor = "-fx-text-fill:  #ff0000; ";
        RMIClient rmiClient = new RMIClient();
        if (rmiClient.runClient()) {
            serverStatusLabel.setStyle(defaultColor);
            serverStatusLabel.setText("Server is Running");
            loginButton.setVisible(true);
        } else {
            serverStatusLabel.setText("Server Connection Failed. Please Contact Your Administrator!");
            serverStatusLabel.setStyle(errortColor);
            loginButton.setVisible(false);
        }
    }
}
