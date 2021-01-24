package sample;

import Connector.EventListener;
import Connector.Connector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Timer;

public class Main extends Application {
    private Connector con;
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        EventListener Listener = new EventListener(7070);
        Listener.start();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        this.primaryStage = primaryStage;
        this.primaryStage.initStyle(StageStyle.UNDECORATED);
        this.primaryStage.setTitle("SoccerSystem 1.0");
        this.primaryStage.setScene(new Scene(root,500 , 400));
        this.primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
