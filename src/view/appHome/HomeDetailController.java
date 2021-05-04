package view.appHome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import services.navigation.Navigation;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeDetailController implements Initializable {
    @FXML
    private AnchorPane detailAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void loadHomeDetail(ActionEvent actionEvent) {
        Navigation navigation = new Navigation();
        navigation.loadHomeDetail(detailAnchorPane);
    }

    @FXML
    private void loadBookDetail(ActionEvent actionEvent) {
        Navigation navigation = new Navigation();
        navigation.loadBookDetail(detailAnchorPane);
    }

    @FXML
    private void loadRequestBookDetail(ActionEvent actionEvent) {
        Navigation navigation = new Navigation();
        navigation.loadRequestBookDetail(detailAnchorPane);
    }

    @FXML
    private void loadShopInfoDetail(ActionEvent actionEvent) {
        Navigation navigation = new Navigation();
        navigation.loadShopInfoDetail(detailAnchorPane);
    }

    @FXML
    private void loadFeedbackDetail(ActionEvent actionEvent) {
        Navigation navigation = new Navigation();
        navigation.loadFeedbackDetail(detailAnchorPane);
    }
}
