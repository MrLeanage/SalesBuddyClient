package view.storeManagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.About;
import services.navigation.Navigation;
import services.rmiService.RMIClient;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class ShopInfoDetailController implements Initializable {

    @FXML
    private AnchorPane detailAnchorPane;

    @FXML
    private Label shopNameLabel;

    @FXML
    private Label designationLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label contactNoLabel;

    @FXML
    private Label emailLabel;


    private static About aboutInfo = null;

    @FXML
    private void loadHomeDetail(ActionEvent actionEvent) {
        Navigation navigation = new Navigation();
        navigation.loadHomeDetail(detailAnchorPane);
    }

    @FXML
    private void loadShopInfoDetail(ActionEvent actionEvent) {
        Navigation navigation = new Navigation();
        navigation.loadShopInfoDetail(detailAnchorPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }

    private void setData() {
        try {
            aboutInfo = RMIClient.rmiInterface.getShopInformation();
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
        if (aboutInfo != null) {
            shopNameLabel.setText(aboutInfo.getaBookshopName());
            addressLabel.setText(aboutInfo.getaBookshopAddress());
            nameLabel.setText(aboutInfo.getaContactPerson());
            designationLabel.setText(aboutInfo.getaContactDesignation());
            emailLabel.setText(aboutInfo.getaContactEmail());
            contactNoLabel.setText(aboutInfo.getaBookshopNumber());
        }


    }
}
