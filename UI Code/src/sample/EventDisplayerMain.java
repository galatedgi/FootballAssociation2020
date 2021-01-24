package sample;
import Connector.EventHandler;
import Connector.EventListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.TimerTask;

public class EventDisplayerMain extends TimerTask {
    public static Stage EventStage;


    public void start(Stage stage) throws Exception {
        String Events = EventHandler.getEvents();
        Parent root = FXMLLoader.load(getClass().getResource("EventDisplayer.fxml"));
        this.EventStage = stage;
        this.EventStage.initStyle(StageStyle.UNDECORATED);
        this.EventStage.setTitle("SoccerSystem 1.0");
        this.EventStage.setScene(new Scene(root,500 , 400));
        this.EventStage.show();
    }

    @Override
    public void run() {
        try {
            start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
