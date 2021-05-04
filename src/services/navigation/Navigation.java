package services.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import services.rmiService.RMIClient;
import util.utility.AlertPopUp;

import java.io.IOException;
import java.util.Optional;

public class Navigation {
    private static AnchorPane currentBasePane;

    public void logout(AnchorPane basePane) {
        try {
            Optional<ButtonType> action = AlertPopUp.logoutConfirmation();
            if (action.get() == ButtonType.OK) {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/appHome/login.fxml"));
                basePane.getChildren().setAll(pane);
                RMIClient.clearRMISession();
                currentBasePane = basePane;
            }

        } catch (IOException ex) {
            AlertPopUp.generalError(ex);
        }
    }

    public void loadHome(AnchorPane basePane) {

        try {
            if (RMIClient.isServerUp()) {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/appHome/home.fxml"));
                basePane.getChildren().setAll(pane);
            } else {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/appHome/login.fxml"));
                basePane.getChildren().setAll(pane);
            }
            currentBasePane = basePane;

        } catch (IOException ex) {
        }
    }

    public void loadHomeDetail(AnchorPane detailPane) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/appHome/homeDetail.fxml"));
            detailPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            sessionExpired();
        }
    }

    public void loadBookDetail(AnchorPane detailPane) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/bookManagement/bookDetail.fxml"));
            detailPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            sessionExpired();
        }
    }

    public void loadFeedbackDetail(AnchorPane detailPane) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/feedbackManagement/feedbackDetail.fxml"));
            detailPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            sessionExpired();
        }
    }

    public void loadShopInfoDetail(AnchorPane detailPane) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/storeManagement/shopInfoDetail.fxml"));
            detailPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            sessionExpired();
        }
    }

    public void loadRequestBookDetail(AnchorPane detailPane) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/RequestManagement/requestBookDetail.fxml"));
            detailPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            sessionExpired();
        }
    }

    private void sessionExpired() {
        Optional<ButtonType> action = AlertPopUp.sessionExpired();
        if (action.get() == ButtonType.OK) {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("/view/appHome/login.fxml"));
            } catch (IOException exception) {
            }
            currentBasePane.getChildren().setAll(pane);
            RMIClient.clearRMISession();
        }
    }
}
