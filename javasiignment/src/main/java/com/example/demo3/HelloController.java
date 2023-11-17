package com.example.demo3;
import com.example.demo3.data.database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class HelloController {

    @FXML
    private TextField sName;
    @FXML
    private TextField sLastname;
    @FXML
    private TextField sContact;
    @FXML
    private TextField sEmail;
    @FXML
    private TextField sTime;
    @FXML
    private TextField sType;
    @FXML
    private DatePicker datePicker;

    @FXML
    protected void onHelloButtonClick() {
        String name = sName.getText();
        String lastname = sLastname.getText();
        String contact = sContact.getText();
        String email = sEmail.getText();
        LocalDate date = datePicker.getValue();
        Date sqlDate = Date.valueOf(date);
        String time = sTime.getText();
        String type = sType.getText();


        try {
            int number = Integer.parseInt(contact);

            // Create a MySQL database connection
            database db = new database();
            Connection connection = db.dbConnection();

            if (connection != null) {

                // Insert data into the MySQL database
                db.insertRow(connection, name, lastname, email, number, sqlDate, time,type);

                sName.clear();
                sLastname.clear();
                sContact.clear();
                sEmail.clear();
                sTime.clear();
                sType.clear();

                // Close the MySQL database connection
                connection.close();
            }
        } catch (NumberFormatException | SQLException e) {
            // Handle exceptions
           System.out.println(e);
        }
    }


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

