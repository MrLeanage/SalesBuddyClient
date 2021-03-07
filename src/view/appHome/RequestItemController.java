package view.appHome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import services.Navigation;

public class RequestItemController {
    @FXML
    private void loadHome(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadHome(actionEvent);
    }
    @FXML
    private void loadRequestItem(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadRequestItem(actionEvent);
    }
}
