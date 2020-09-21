package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    void initialize() {

        authSignInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String passwordText = passwordField.getText().trim();
            if(!loginText.equals("")&&!passwordText.equals("")) {
                try {
                    loginUser(loginText,passwordText);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else
                System.out.println("Error");
        });

        loginSignUpButton.setOnAction(event -> {
            openNewScene("/sample/views/signUp.fxml");
            loginSignUpButton.getScene().getWindow().hide();
        });
    }

    private void loginUser(String loginText, String passwordText) throws SQLException, ClassNotFoundException {
        DatabaseHandler dbhandler = new DatabaseHandler();
        User user = new User();
        user.setUsername(loginText);
        user.setPassword(passwordText);
        ResultSet result = dbhandler.getUser(user);

        int counter = 0;
        while(result.next())
            counter++;
        if (counter>=1)
            openNewScene("/sample/views/app.fxml");
        else
            System.out.println("Error authorization!");
    }
    public void openNewScene(String window){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }
}

