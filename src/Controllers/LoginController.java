package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DBConnection.DBHandler;

public class LoginController implements Initializable {
    @FXML
    private AnchorPane screen;

    @FXML
    private JFXTextField userName;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton forgotPassword;

    @FXML
    private ImageView logo;

    @FXML
    private ImageView man;

    @FXML
    private ImageView castle;

    @FXML
    private ImageView progress;

    @FXML
    private JFXButton login;
    @FXML
    private JFXButton signUp;

    private DBHandler dbHandler;
    private Connection connection;
    private PreparedStatement preparedStatement;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progress.setVisible(false);
        userName.setStyle("-fx-text-inner-color: #dacaca;");
        passwordField.setStyle("-fx-text-inner-color: #dacaca;");
        dbHandler = new DBHandler();
    }

    @FXML
    public void loginAction(ActionEvent e){
//        progress.setVisible(true);
//        PauseTransition pt = new PauseTransition();
//        pt.setDuration(Duration.seconds(3));
//        pt.setOnFinished(event -> System.out.println("Login Succesfully"));
//        pt.play();

        //Retrieve data from DataBase
        connection = dbHandler.getConnection();
        String q1 = "SELECT * from youtubers where names=? and password=?";
        try {
            preparedStatement = connection.prepareStatement(q1);
            preparedStatement.setString(1, userName.getText());
            preparedStatement.setString(2, passwordField.getText());
            if (userName.getText() != null && passwordField.getText()!= null) {
                executeQuery();
            } else if (userName.getText() == null && passwordField.getText()== null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Username and Password fields shouldn't be empty");
                alert.show();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void executeQuery() throws SQLException {
        ResultSet rs = preparedStatement.executeQuery();
        int count = 0;
        while (rs.next()){
            count +=1;
        } if (count == 1){
            login.getScene().getWindow().hide();
            Stage home = new Stage();
            try {
                Parent root  = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));
                Scene scene = new Scene(root);
                home.setScene(scene);
                home.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Username and Password are incorrect");
            alert.show();
        }
    }

    @FXML
    public void signUp(ActionEvent event) throws IOException {
        login.getScene().getWindow().hide();

        Stage signUpStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/SignUp.fxml"));
        Scene scene = new Scene(root);
        signUpStage.setScene(scene);
        signUpStage.show();
        signUpStage.setResizable(false);
    }
}
