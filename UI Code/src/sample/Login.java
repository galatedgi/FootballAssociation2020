package sample;

import Connector.Connector;
import Connector.Permissions;
import HttpProtocol.Parser;
import HttpProtocol.Response;
import HttpProtocol.responseParser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Pair;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Login {
    @FXML
    private BorderPane MainBorder;
    @FXML
    private Label TopLabel;
    @FXML
    private TextField UsernameField;
    @FXML
    private TextField PasswordField;
    @FXML
    private TextField usernameReg;
    @FXML
    private TextField passwordReg;
    @FXML
    private TextField fullnameReg;
    @FXML
    private TextField idReg;
    @FXML
    private Pane TopPane;
    @FXML
    private AnchorPane MainPane;
    private static double xOffset = 0;
    private static double yOffset = 0;


    private void LoadMenu(String ui){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException e){

        }
        MainBorder.setCenter(root);
    }

    public void handle(MouseEvent event) {
        xOffset = Main.primaryStage.getX() - event.getScreenX();
        yOffset = Main.primaryStage.getY() - event.getScreenY();
    }

    public void CloseButton(MouseEvent event) {
        Main.primaryStage.close();
        Platform.exit();
    }


    public void Drag(MouseEvent event) {
        Main.primaryStage.setX(event.getScreenX() + xOffset);
        Main.primaryStage.setY(event.getScreenY() + yOffset);
    }

    public void ShowLoginScreen(ActionEvent actionEvent) {
        LoadMenu("LoginForm");
    }

    public void ShowRegisterScreen(ActionEvent actionEvent) {
        LoadMenu("RegisterForm");
    }

    public void LoginAttempt(ActionEvent actionEvent) throws IOException {
        Connector con = Connector.getInstance();
        Response response = con.sendLoginAttempt(UsernameField.getText(),PasswordField.getText());
        if (response.getResponse() == "200 OK" ){
            CheckPermissions(response.getBody());
            Permissions.getInstance().setUsername(UsernameField.getText());
            FXMLLoader MainWindow = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Parent root = MainWindow.load();
            Main.primaryStage.setScene(new Scene(root,600 , 400));
            Main.primaryStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(responseParser.getElement("error",response.getBody()));
            alert.showAndWait();
        }

    }

    private void CheckPermissions(ArrayList<Pair<String, String>> body) {
        Permissions permissions = Permissions.getInstance();
        if (responseParser.getElement("Fan", body).equals("true"))
            permissions.setFan(true);
        if (responseParser.getElement("Coach", body).equals("true"))
            permissions.setCoach(true);
        if (responseParser.getElement("TeamManager", body).equals("true"))
            permissions.setTeamManager(true);
        if (responseParser.getElement("Player", body).equals("true"))
            permissions.setPlayer(true);
        if (responseParser.getElement("Referee", body).equals("true"))
            permissions.setReferee(true);
        if (responseParser.getElement("TeamOwner", body).equals("true"))
            permissions.setTeamOwner(true);
        if (responseParser.getElement("Admin", body).equals("true"))
            permissions.setAdmin(true);
        if (responseParser.getElement("Association", body).equals("true"))
            permissions.setAssociation(true);
    }
//
//    private void CheckPermissions(ArrayList<Pair<String, String>> body, Controller MainController) {
//        Permissions permissions = Permissions.getInstance();
//        if(responseParser.getElement("Fan",body).equals("true"))
//            MainController.EnableButton("Fan");
//        if(responseParser.getElement("Coach",body).equals("true"))
//            MainController.EnableButton("Coach");
//        if(responseParser.getElement("TeamManager",body).equals("true"))
//            MainController.EnableButton("TeamManager");
//        if(responseParser.getElement("Player",body).equals("true"))
//            MainController.EnableButton("Player");
//        if(responseParser.getElement("Referee",body).equals("true"))
//            MainController.EnableButton("Referee");
//        if(responseParser.getElement("TeamOwner",body).equals("true"))
//            MainController.EnableButton("TeamOwner");
//        if(responseParser.getElement("Admin",body).equals("true"))
//            MainController.EnableButton("Admin");
//        if(responseParser.getElement("Association",body).equals("true"))
//            MainController.EnableButton("Association");
//    }

    public void RegisterAttempt(ActionEvent actionEvent) throws IOException {
        Connector con = Connector.getInstance();
        Response response = con.sendRegister(usernameReg.getText(),passwordReg.getText(),idReg.getText(),fullnameReg.getText());
        if (response.getResponse() == "200 OK" ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Registered successfully to server!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(responseParser.getElement("error",response.getBody()));
            alert.showAndWait();
        }
    }
}
