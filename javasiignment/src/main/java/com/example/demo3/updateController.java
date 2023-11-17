package com.example.demo3;


import com.example.demo3.data.database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class updateController {

    @FXML
    private TextField customerName;
    @FXML
    private TextField newValue;
    @FXML
    private RadioButton nameRadio;
    @FXML
    private RadioButton lastnameRadio;
    @FXML
    private RadioButton emailRadio;
    @FXML
    private RadioButton contactRadio;
    @FXML
    private RadioButton dateRadio;
    @FXML
    private RadioButton timeRadio;
    @FXML
    private RadioButton typeRadio;


    private ToggleGroup toggleGroup; // Define a ToggleGroup

    public void initialize() {
        toggleGroup = new ToggleGroup(); // Initialize the ToggleGroup
        nameRadio.setToggleGroup(toggleGroup);
        lastnameRadio.setToggleGroup(toggleGroup);
        dateRadio.setToggleGroup(toggleGroup);
        timeRadio.setToggleGroup(toggleGroup);
        contactRadio.setToggleGroup(toggleGroup);
        emailRadio.setToggleGroup(toggleGroup);
        typeRadio.setToggleGroup(toggleGroup);

    }

    //Updating
    @FXML
    private void handleSaveButtonAction() {
        // Get the selected radio button's text
        Toggle selectedToggle = toggleGroup.getSelectedToggle();
        if (selectedToggle != null) {
            RadioButton selectedRadio = (RadioButton) selectedToggle;
            String selectedText = selectedRadio.getText();
            database db = new database();
            Connection connection = db.dbConnection();
            Statement statement = null;
            try {
                statement = connection.createStatement();
                String query = "UPDATE meetings SET " + selectedText + " = '" + newValue.getText() + "' WHERE Name = '" + customerName.getText() + "'";
                statement.executeUpdate(query);
                System.out.println("Data updated.");
            } catch (SQLException e) {
                System.out.println("Data insertion error: " + e.getMessage());
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }


    }
    //switching scenes
    @FXML
    public void switchToScene2(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
