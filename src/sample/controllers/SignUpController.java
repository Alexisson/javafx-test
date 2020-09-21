package sample.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;


public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpCountry;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    void initialize() {



        signUpButton.setOnAction(event -> {
            signUpNewUser();

        });
    }

    private void signUpNewUser() {
        DatabaseHandler dbhandler = new DatabaseHandler();
        String firstname = signUpName.getText();
        String lastname = signUpLastName.getText();
        String username = loginField.getText();
        String password = passwordField.getText();
        String location = signUpCountry.getText();
        String gender = "";
        if(signUpCheckBoxMale.isSelected())
            gender = "Male";
        else
            gender = "Female";
        User user = new User(firstname,lastname,username,password,location,gender);

        try {
            dbhandler.signUpUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

