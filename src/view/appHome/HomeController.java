package view.appHome;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Book;
import services.navigation.Navigation;
import services.rmiService.RMIClient;
import util.authentication.UserAuthentication;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label userSessionLabel;

    @FXML
    private AnchorPane card1AnchorPane;

    @FXML
    private Label card1BookNameLabel;

    @FXML
    private Label card1AuthorLabel;

    @FXML
    private Label card1AvailabilityLabel;

    @FXML
    private TextArea card1InfoTextArea;

    @FXML
    private AnchorPane card2AnchorPane;

    @FXML
    private Label card2BookNameLabel;

    @FXML
    private Label card2AuthorLabel;

    @FXML
    private Label card2AvailablityLabel;

    @FXML
    private TextArea card2InfoTextArea;

    @FXML
    private AnchorPane card3AnchorPane;

    @FXML
    private Label card3BookName;

    @FXML
    private Label card3AuthorNameLabel;

    @FXML
    private Label card3AvalabilityLabel;

    @FXML
    private TextArea card3InfoTextArea;

    @FXML
    private AnchorPane card4AnchorPane;

    @FXML
    private Label card4BookName;

    @FXML
    private Label card4AuthorNameLabel;

    @FXML
    private Label card4AvalabilityLabel;

    @FXML
    private TextArea card4InfoTextArea;

    @FXML
    private Label timeLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private AnchorPane detailAnchorPane;

    @FXML
    private Label shopNameLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadHomeDetail();
        setData();
    }

    @FXML
    private void loadHomeDetail() {
        Navigation navigation = new Navigation();
        navigation.loadHomeDetail(detailAnchorPane);
    }

    @FXML
    private void logout() {
        Navigation navigation = new Navigation();
        navigation.logout(baseAnchorPane);
    }

    private void setData() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String strDate = formatter.format(date);
        dateLabel.setText(strDate);


        card1AnchorPane.setVisible(true);
        card2AnchorPane.setVisible(true);
        card3AnchorPane.setVisible(true);
        try {
            List<Book> bookList = RMIClient.rmiInterface.getAllBooks();
            ObservableList<Book> bookObservableList = FXCollections.observableList(bookList);
            ObservableList<Book> newArrivalList = FXCollections.observableArrayList();
            for (Book book : bookObservableList) {
                newArrivalList.add(book);
            }
            if (newArrivalList.isEmpty()) {
                card1AnchorPane.setVisible(false);
                card2AnchorPane.setVisible(false);
                card3AnchorPane.setVisible(false);
                card4AnchorPane.setVisible(false);
            } else if (newArrivalList.size() == 1) {
                card1AnchorPane.setVisible(true);
                card2AnchorPane.setVisible(false);
                card3AnchorPane.setVisible(false);
                card4AnchorPane.setVisible(false);

                card1BookNameLabel.setText(newArrivalList.get(0).getbTitle().toUpperCase());
                card1AuthorLabel.setText(newArrivalList.get(0).getbAuthor());
                card1InfoTextArea.setText(newArrivalList.get(0).getbDescription());
                card1AvailabilityLabel.setText(newArrivalList.get(0).getbAvailability());
            } else if (newArrivalList.size() == 2) {
                card1AnchorPane.setVisible(true);
                card2AnchorPane.setVisible(true);
                card3AnchorPane.setVisible(false);
                card4AnchorPane.setVisible(false);

                card1BookNameLabel.setText(newArrivalList.get(0).getbTitle().toUpperCase());
                card1AuthorLabel.setText(newArrivalList.get(0).getbAuthor());
                card1InfoTextArea.setText(newArrivalList.get(0).getbDescription());
                card1AvailabilityLabel.setText(newArrivalList.get(0).getbAvailability());

                card2BookNameLabel.setText(newArrivalList.get(1).getbTitle().toUpperCase());
                card2AuthorLabel.setText(newArrivalList.get(1).getbAuthor());
                card2InfoTextArea.setText(newArrivalList.get(1).getbDescription());
                card2AvailablityLabel.setText(newArrivalList.get(1).getbAvailability());
            } else if (newArrivalList.size() == 3) {
                card1AnchorPane.setVisible(true);
                card2AnchorPane.setVisible(true);
                card3AnchorPane.setVisible(true);
                card4AnchorPane.setVisible(false);

                card1BookNameLabel.setText(newArrivalList.get(0).getbTitle().toUpperCase());
                card1AuthorLabel.setText(newArrivalList.get(0).getbAuthor());
                card1InfoTextArea.setText(newArrivalList.get(0).getbDescription());
                card1AvailabilityLabel.setText(newArrivalList.get(0).getbAvailability());

                card2BookNameLabel.setText(newArrivalList.get(1).getbTitle().toUpperCase());
                card2AuthorLabel.setText(newArrivalList.get(1).getbAuthor());
                card2InfoTextArea.setText(newArrivalList.get(1).getbDescription());
                card2AvailablityLabel.setText(newArrivalList.get(1).getbAvailability());

                card3BookName.setText(newArrivalList.get(1).getbTitle().toUpperCase());
                card3AuthorNameLabel.setText(newArrivalList.get(1).getbAuthor());
                card3InfoTextArea.setText(newArrivalList.get(1).getbDescription());
                card3AvalabilityLabel.setText(newArrivalList.get(1).getbAvailability());
            } else {
                card1AnchorPane.setVisible(true);
                card2AnchorPane.setVisible(true);
                card3AnchorPane.setVisible(true);
                card4AnchorPane.setVisible(true);

                card1BookNameLabel.setText(newArrivalList.get(newArrivalList.size() - 1).getbTitle().toUpperCase());
                card1AuthorLabel.setText(newArrivalList.get(newArrivalList.size() - 1).getbAuthor());
                card1InfoTextArea.setText(newArrivalList.get(newArrivalList.size() - 1).getbDescription());
                card1AvailabilityLabel.setText(newArrivalList.get(newArrivalList.size() - 1).getbAvailability());

                card2BookNameLabel.setText(newArrivalList.get(newArrivalList.size() - 2).getbTitle().toUpperCase());
                card2AuthorLabel.setText(newArrivalList.get(newArrivalList.size() - 2).getbAuthor());
                card2InfoTextArea.setText(newArrivalList.get(newArrivalList.size() - 2).getbDescription());
                card2AvailablityLabel.setText(newArrivalList.get(newArrivalList.size() - 2).getbAvailability());

                card3BookName.setText(newArrivalList.get(newArrivalList.size() - 3).getbTitle());
                card3AuthorNameLabel.setText(newArrivalList.get(newArrivalList.size() - 3).getbAuthor().toUpperCase());
                card3InfoTextArea.setText(newArrivalList.get(newArrivalList.size() - 3).getbDescription());
                card3AvalabilityLabel.setText(newArrivalList.get(newArrivalList.size() - 3).getbAvailability());

                card4BookName.setText(newArrivalList.get(newArrivalList.size() - 4).getbTitle());
                card4AuthorNameLabel.setText(newArrivalList.get(newArrivalList.size() - 4).getbAuthor().toUpperCase());
                card4InfoTextArea.setText(newArrivalList.get(newArrivalList.size() - 4).getbDescription());
                card4AvalabilityLabel.setText(newArrivalList.get(newArrivalList.size() - 4).getbAvailability());
            }

        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }


        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        final Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(500),
                        event -> {
                            final long diff = System.currentTimeMillis();
                            timeLabel.setText(timeFormat.format(diff));
                        }
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        userSessionLabel.setText(UserAuthentication.getAuthenticatedUser().getuName());

        try {
            shopNameLabel.setText(RMIClient.rmiInterface.getShopInformation().getaBookshopName());
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
    }
}
