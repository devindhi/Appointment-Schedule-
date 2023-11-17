package com.example.demo3;

import com.example.demo3.data.database; // Assuming you have a Database class for MySQL
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class viewController implements Initializable {

    @FXML
    private TableView<Customer> tableView;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, String> lastnameColumn;
    @FXML
    private TableColumn<Customer, String> contactColumn;
    @FXML
    private TableColumn<Customer, Date> dateColumn;
    @FXML
    private TableColumn<Customer, String> timeColumn;
    @FXML
    private TableColumn<Customer, String> emailColumn;
    @FXML
    private TableColumn<Customer, String> idColumn;
    @FXML
    private TableColumn<Customer, String> typeColumn;

    private ObservableList<Customer> data = FXCollections.observableArrayList();

    @FXML
    private TextField searchField;
    @FXML
    private ListView<String> resultListView;

    //switching scenes
    @FXML
    public void switchToScene1(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchtoScene3(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("update.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void search1() {
        String searchCriteria = searchField.getText();
        // Perform the search using the search criteria
        try {
            database db = new database();
            Connection connection = db.dbConnection();
            Statement statement = connection.createStatement();

            // Use a dynamic SQL query to search by multiple criteria
            String query = "SELECT * FROM meetings WHERE " +
                    "(Name LIKE '%" + searchCriteria + "%' OR " +
                    "Lastname LIKE '%" + searchCriteria + "%' OR " +
                    "Date = '" + searchCriteria + "')";

            ResultSet resultSet = statement.executeQuery(query);

            // Create an ObservableList to store the search results
            ObservableList<String> searchResults = FXCollections.observableArrayList();

            // Process and add search results to the ObservableList
            while (resultSet.next()) {
                String result = "Name: " + resultSet.getString("Name") +
                        ", Lastname: " + resultSet.getString("Lastname") +
                        ", Contact: " + resultSet.getString("Contact") +
                        ", Date: " + resultSet.getString("Date");
                searchResults.add(result);
            }

            // Display the search results in the ListView
            resultListView.setItems(searchResults);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        tableView.setItems(data);

        // Retrieve data from MySQL and add it to the ObservableList
        try {
            loadDataFromMySQL();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    //loading data from database
    private void loadDataFromMySQL() throws SQLException {
        database db = new database();
        Connection connection = db.dbConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM meetings");
        while (resultSet.next()) {
            Customer item = new Customer(
                    resultSet.getString("id"),
                    resultSet.getString("Name"),
                    resultSet.getString("Lastname"),
                    resultSet.getString("Contact"),
                    resultSet.getString("Email"),
                    resultSet.getString("Date"),
                    resultSet.getString("Time"),
                    resultSet.getString("Type")
            );
            data.add(item);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    //delete option
    @FXML
    private void handleDeleteButtonAction() {
        Customer selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            data.remove(selectedItem);
            try {
                database db = new database();
                Connection connection = db.dbConnection();
                Statement statement = connection.createStatement();

                // Assuming 'id' is the primary key for the 'meetings' table
                String id = selectedItem.getId();
                String query = "DELETE FROM meetings WHERE id = " + id;
                statement.executeUpdate(query);

                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}


