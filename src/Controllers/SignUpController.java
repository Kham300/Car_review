package Controllers;
import javafx.fxml.FXML;
import com.jfoenix.controls.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DBConnection.DBHandler;

public class SignUpController implements Initializable {

    @FXML
    private AnchorPane parentPane;

    @FXML
    private JFXTextField userName;

    @FXML
    private JFXButton signUp;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private ImageView logo;

    @FXML
    private JFXRadioButton female;

    @FXML
    private JFXRadioButton male;

    @FXML
    private JFXTextField location;

    @FXML
    private JFXButton login;

    @FXML
    private ImageView progress;

    private Connection connection;
    private DBHandler dbHandler;
    private PreparedStatement preparedStatement;

    @Override
    public void initialize(URL URLlocation, ResourceBundle resources) {

        progress.setVisible(false);
        userName.setStyle("-fx-text-inner-color: #dacaca");
        passwordField.setStyle("-fx-text-inner-color: #dacaca");
        location.setStyle("-fx-text-inner-color: #dacaca");

        dbHandler = new DBHandler();
    }



    @FXML
    public void signUp(javafx.event.ActionEvent event) {
        //Saving Data
        String insert = "INSERT INTO youtubers(names,password,gender,location)"
                + "VALUES (?,?,?,?)";
        connection = dbHandler.getConnection();
        try {
            preparedStatement = connection.prepareStatement(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            preparedStatement.setString(1, userName.getText());
            preparedStatement.setString(2, passwordField.getText());
            preparedStatement.setString(3, getGender());
            preparedStatement.setString(4, location.getText());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loginAction(javafx.event.ActionEvent event) throws IOException{
        signUp.getScene().getWindow().hide();
        Stage login = new Stage();
        Parent root =  FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    public String getGender(){
        String gen = "";
        if (male.isSelected()){
            gen = "Man";
        } else {
            gen = "Woman";
        }
        return gen;
    }
}
