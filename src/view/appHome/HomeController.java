package view.appHome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import services.Navigation;

public class HomeController {
    @FXML
    private void loadHome(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadHome(actionEvent);

    }
    @FXML
    private void loadFindItem(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadFindItem(actionEvent);

    }
    @FXML
    private void loadFeedback(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadFeedback(actionEvent);

    }
    @FXML
    private void loadReaderCorner(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadReaderCorner(actionEvent);

    }
    @FXML
    private void loadRequestItem(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadRequestItem(actionEvent);

    }
}
