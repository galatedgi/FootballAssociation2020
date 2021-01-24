package sample;

import Connector.Connector;
import Connector.EventHandler;
import Connector.EventListener;
import Connector.Permissions;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;

public class Controller {
    private static boolean newEvents;
    @FXML
    BorderPane MainBorder;
    @FXML
    private Pane MainPane;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private javafx.scene.control.Button TeamOwnerB;
    @FXML
    private javafx.scene.control.Button PlayerB;
    @FXML
    private javafx.scene.control.Button TeamManagerB;
    @FXML
    private javafx.scene.control.Button CoachB;
    @FXML
    private javafx.scene.control.Button FanB;
    @FXML
    private javafx.scene.control.Button AssociationB;
    @FXML
    private javafx.scene.control.Button RefereeB;
    @FXML
    private javafx.scene.control.Button AdminB;
    public static Stage EventStage;
    private EventListener Listener;
    private static double xOffset = 0;
    private static double yOffset = 0;

    public void initialize() throws IOException {
        DisableButtons();
        EnableButtons();
        scrollpane.setFitToWidth(true);
//        java.util.Timer timer = new Timer();
//        timer.schedule(new EventDisplayerMain(), 0, 50);
        newEvents = false;
    }

    private void LoadMenu(String ui){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException e){
            System.out.println(ui+".fxml not found");
        }
        MainBorder.setCenter(root);
    }

    public void handle(javafx.scene.input.MouseEvent event) {
        xOffset = Main.primaryStage.getX() - event.getScreenX();
        yOffset = Main.primaryStage.getY() - event.getScreenY();
    }

    public void CloseButton(javafx.scene.input.MouseEvent event) {
        Main.primaryStage.close();
        Platform.exit();
    }

    public void ShowEvents() throws Exception {
        String Events = EventHandler.getEvents();
        Parent root = FXMLLoader.load(getClass().getResource("EventDisplayer.fxml"));
        this.EventStage = new Stage();
        this.EventStage.initStyle(StageStyle.UNDECORATED);
        this.EventStage.setTitle("SoccerSystem 1.0");
        this.EventStage.setScene(new Scene(root,500 , 400));
        this.EventStage.show();
    }


    public void Drag(MouseEvent event) {
        Main.primaryStage.setX(event.getScreenX() + xOffset);
        Main.primaryStage.setY(event.getScreenY() + yOffset);
    }

    public void Logout(ActionEvent actionEvent) throws IOException {
        Connector.getInstance().sendlogout();
        Permissions.getInstance().reset();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Main.primaryStage.setScene(new Scene(root,500 , 400));
        Main.primaryStage.show();
    }

    public void ShowLoginScreen(ActionEvent actionEvent) {
        LoadMenu("Login");
    }

    public void ShowAssociationScreen(ActionEvent actionEvent) {
        LoadMenu("Association");
//        AlertofEvent();
    }

    public void ShowRefereeScreen(ActionEvent actionEvent) {
        LoadMenu("Referee");
    }

    public static void AlertofEvent() {
        String test = EventHandler.getEvents();
        if(newEvents){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(EventHandler.getEvents());
            alert.showAndWait();
            newEvents = false;
        }
    }

    public static void AlertofEvent2(String event) {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(event.replace("$","\n"));
                alert.showAndWait();
            }
        });
    }

    public static void NewEvent(){
        newEvents = true;
    }


    public void ShowPlayerScreen(ActionEvent actionEvent) {
        LoadMenu("Player");
        AlertofEvent();
    }

    public void DisableButtons(){
        FanB.setDisable(true);
        TeamOwnerB.setDisable(true);
        TeamManagerB.setDisable(true);
        PlayerB.setDisable(true);
        RefereeB.setDisable(true);
        AssociationB.setDisable(true);
        CoachB.setDisable(true);
        AdminB.setDisable(true);
    }

    public void EnableButtons() {
        Permissions permissions = Permissions.getInstance();
        if(permissions.isFan()){
            FanB.setDisable(false);
        }
        if(permissions.isTeamOwner()){
            TeamOwnerB.setDisable(false);
        }
        if(permissions.isTeamManager()){
            TeamManagerB.setDisable(false);
        }
        if(permissions.isPlayer()){
            PlayerB.setDisable(false);
        }
        if(permissions.isReferee()){
            RefereeB.setDisable(false);
        }
        if(permissions.isAssociation()){
            AssociationB.setDisable(false);
        }
        if(permissions.isCoach()){
            CoachB.setDisable(false);
        }
        if(permissions.isAdmin()){
            AdminB.setDisable(false);
        }
    }
}
