package services;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    public void loadHome(ActionEvent actionEvent){
        try {
            AnchorPane home_page = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/appHome/home.fxml"));

            Scene scene = new Scene(home_page);
            Stage app=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            app.setScene(scene);
            app.show();
        } catch ( IOException ex) {
        }
    }
    public void loadFindItem(ActionEvent actionEvent){
        try {
            AnchorPane home_page = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/appHome/findLocation.fxml"));

            Scene scene = new Scene(home_page);
            Stage app=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            app.setScene(scene);
            app.show();
        } catch ( IOException ex) {
        }
    }
    public void loadFeedback(ActionEvent actionEvent){
        try {
            AnchorPane home_page = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/appHome/feedback.fxml"));

            Scene scene = new Scene(home_page);
            Stage app=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            app.setScene(scene);
            app.show();
        } catch ( IOException ex) {
        }
    }
    public void loadReaderCorner(ActionEvent actionEvent){
        try {
            AnchorPane home_page = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/appHome/readerCorner.fxml"));

            Scene scene = new Scene(home_page);
            Stage app=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            app.setScene(scene);
            app.show();
        } catch ( IOException ex) {
        }
    }
    public void loadRequestItem(ActionEvent actionEvent){
        try {
            AnchorPane home_page = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/appHome/requestItem.fxml"));

            Scene scene = new Scene(home_page);
            Stage app=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            app.setScene(scene);
            app.show();
        } catch ( IOException ex) {
        }
    }
}
