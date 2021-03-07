package view.appHome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import services.Navigation;

public class FeedbackController {
    @FXML
    private void loadHome(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadHome(actionEvent);
    }
    @FXML
    private void loadFeedback(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadFeedback(actionEvent);
    }
}
