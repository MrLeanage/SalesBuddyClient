package view.bookManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Book;
import services.SearchService;
import services.navigation.Navigation;
import services.rmiService.RMIClient;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

public class BookDetailController implements Initializable {
    @FXML
    private AnchorPane detailAnchorPane;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> bTitleColumn;

    @FXML
    private TableColumn<Book, String> bAuthorColumn;

    @FXML
    private TableColumn<Book, String> bPublisherYearColumn;

    @FXML
    private TableColumn<Book, String> bAvailabilityColumn;

    @FXML
    private TableColumn<Book, String> bActionColumn;

    public static Book selectedBook = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableData();
        searchTable();
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

    private void loadTableData() {

        ObservableList<Book> bookObservableList = null;
        try {
            List<Book> bookList = RMIClient.rmiInterface.getAllBooks();
            bookObservableList = FXCollections.observableList(bookList);
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }


        bTitleColumn.setCellValueFactory(new PropertyValueFactory<>("bTitle"));
        bAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("bAuthor"));
        bPublisherYearColumn.setCellValueFactory(new PropertyValueFactory<>("bPublishYear"));
        bAvailabilityColumn.setCellValueFactory(new PropertyValueFactory<>("bAvailability"));
        bActionColumn.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<Book, String>, TableCell<Book, String>> parentCellFactory
                =
                new Callback<TableColumn<Book, String>, TableCell<Book, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Book, String> param) {
                        final TableCell<Book, String> cell = new TableCell<Book, String>() {

                            final Button button = new Button("View");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    button.setOnMouseClicked(event -> {
                                        // student = StudentTable.getSelectionModel().getSelectedItem();
                                        //String sID = student.getsID();
                                    });
                                    button.setOnAction(event -> {
                                        Book book = getTableView().getItems().get(getIndex());
                                        selectedBook = book;
                                        FXMLLoader loader = new FXMLLoader();
                                        loader.setLocation(getClass().getResource("bookCard.fxml"));
                                        try {
                                            loader.load();
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
                                        BookCardController bookCardController = loader.getController();

                                        Parent p = loader.getRoot();
                                        Stage stage = new Stage();
                                        stage.setScene(new Scene(p));
                                        stage.setResizable(false);
                                        stage.sizeToScene();

                                        stage.showAndWait();
                                        loadTableData();
                                    });
                                    setGraphic(button);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        bActionColumn.setCellFactory(parentCellFactory);
        bookTable.setItems(null);
        bookTable.setItems(bookObservableList);
    }

    public void searchTable() {

        SearchService searchService = new SearchService();

        SortedList<Book> sortedData = searchService.searchTable(searchTextField);

        //binding the SortedList to TableView
        sortedData.comparatorProperty().bind(bookTable.comparatorProperty());
        //adding sorted and filtered data to the table
        bookTable.setItems(sortedData);
    }
}
