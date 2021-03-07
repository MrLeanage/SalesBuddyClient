package view.appHome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import services.Navigation;

public class ReaderCornerController {
    @FXML
    private void loadHome(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadHome(actionEvent);
    }
    @FXML
    private void loadReaderCorner(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadReaderCorner(actionEvent);
    }
}
