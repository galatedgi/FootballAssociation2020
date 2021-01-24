package sample;
import Connector.Connector;
import HttpProtocol.Response;
import HttpProtocol.responseParser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Ref;

public class Report {
    @FXML
    private Label Report;

    public void initialize(){
        Report.setText(RefGames.getReportToShow());
    }

    private static double xOffset = 0;
    private static double yOffset = 0;

    public void handle(javafx.scene.input.MouseEvent event) {
        xOffset = RefGames.ReportStage.getX() - event.getScreenX();
        yOffset = RefGames.ReportStage.getY() - event.getScreenY();
    }

    public void CloseButton(javafx.scene.input.MouseEvent event) {
        RefGames.ReportStage.close();
    }

    public void Drag(MouseEvent event) {
        RefGames.ReportStage.setX(event.getScreenX() + xOffset);
        RefGames.ReportStage.setY(event.getScreenY() + yOffset);
    }
}

