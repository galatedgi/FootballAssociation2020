package sample;

import Connector.Connector;
import HttpProtocol.Response;
import HttpProtocol.responseParser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AddTeam {
    @FXML
    private TextField TeamNameText;
    @FXML
    private TextField TeamOwnerText;
    @FXML
    private TextField StadiumText;

    private static double xOffset = 0;
    private static double yOffset = 0;

    public void handle(javafx.scene.input.MouseEvent event) {
        xOffset = Assteam.stage.getX() - event.getScreenX();
        yOffset = Assteam.stage.getY() - event.getScreenY();
    }

    public void CloseButton(javafx.scene.input.MouseEvent event) {
        Assteam.stage.close();
    }

    public void AddTeamAttempt(ActionEvent actionEvent) throws IOException {
        Connector con = Connector.getInstance();
        Response response = con.AddnewTeam(TeamNameText.getText(),TeamOwnerText.getText(),StadiumText.getText());
        if (response.getResponse() == "200 OK" ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(responseParser.getElement("message",response.getBody()));
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(responseParser.getElement("error",response.getBody()));
            alert.showAndWait();
        }
        Main.primaryStage.show();
    }


    public void Drag(MouseEvent event) {
        Assteam.stage.setX(event.getScreenX() + xOffset);
        Assteam.stage.setY(event.getScreenY() + yOffset);
    }
}
