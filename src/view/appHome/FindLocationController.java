package view.appHome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import services.Navigation;

public class FindLocationController {
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
}
