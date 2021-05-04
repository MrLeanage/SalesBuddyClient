package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import model.Book;
import services.rmiService.RMIClient;

import java.rmi.RemoteException;
import java.util.List;

public class SearchService {
    public SortedList<Book> searchTable(TextField searchTextField) {
        //Retreiving all data from database
        ObservableList<Book> bookData = null;
        try {
            List<Book> bookList = RMIClient.rmiInterface.getAllBooks();
            bookData = FXCollections.observableList(bookList);
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
        //Wrap the ObservableList in a filtered List (initially display all data)
        FilteredList<Book> filteredData = new FilteredList<>(bookData, b -> true);

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(book -> {
                //if filter text is empty display all data
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                //comparing search text with table columns one by one
                String lowerCaseFilter = newValue.toLowerCase();

                if (book.getbISBN().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    //return if filter matches data
                    return true;
                } else if (book.getbTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    //return if filter matches data
                    return true;
                } else if (book.getbAuthor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    //return if filter matches data
                    return true;
                } else if (book.getbPublishYear().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    //return if filter matches data
                    return true;
                } else if (book.getbLanguage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    //return if filter matches data
                    return true;
                } else {
                    //have no matchings
                    return false;
                }
            });
        });
        //wrapping the FilteredList in a SortedList
        SortedList<Book> sortedData = new SortedList<>(filteredData);
        return sortedData;
    }
}
