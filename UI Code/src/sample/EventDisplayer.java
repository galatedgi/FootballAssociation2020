package sample;

import Connector.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class EventDisplayer {
    @FXML
    private Label Report;

    public void initialize(){
        Report.setText(EventHandler.getEvents());
    }

    private static double xOffset = 0;
    private static double yOffset = 0;

    public void handle(javafx.scene.input.MouseEvent event) {
        xOffset = EventDisplayerMain.EventStage.getX() - event.getScreenX();
        yOffset = EventDisplayerMain.EventStage.getY() - event.getScreenY();
    }

    public void CloseButton(javafx.scene.input.MouseEvent event) {
        EventDisplayerMain.EventStage.close();
    }

    public void Drag(MouseEvent event) {
        EventDisplayerMain.EventStage.setX(event.getScreenX() + xOffset);
        EventDisplayerMain.EventStage.setY(event.getScreenY() + yOffset);
    }
}
