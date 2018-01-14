package Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleAction;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private HBox toolBarRight;

    @FXML
    private Text welcome;

    @FXML
    private Label idMenu;

    @FXML
    private VBox overFlowConteiner;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnExit;

    @FXML
    private AnchorPane holderPane;
    AnchorPane home;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createPage();
    }

    @FXML
    private void actionExit(ActionEvent event){
        Platform.exit();
    }

    @FXML
    private void actionLogOut(ActionEvent event){

        btnLogOut.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
        } catch (IOException e){e.printStackTrace();}
    }

    private void setNode(Node node){
        holderPane.getChildren().clear();
        holderPane.getChildren().add(node);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1500));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(0.1);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();
    }
    private void createPage() {

        try {
            home = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));
            setNode(home);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void actionHome(ActionEvent event){
        setNode(home);
    }
}
