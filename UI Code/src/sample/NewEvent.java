package sample;

import Connector.Connector;
import HttpProtocol.Response;
import HttpProtocol.responseParser;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

import javax.swing.text.LabelView;
import java.io.IOException;
import java.util.ArrayList;

public class NewEvent {
    @FXML
    private ChoiceBox EventChooser;
    @FXML
    private ChoiceBox<String> TeamBox;
    @FXML
    private ChoiceBox<String> PlayerBox;
    @FXML
    private Label FirstLabel;
    @FXML
    private TextField HourText;
    @FXML
    private TextField MinuteText;
    @FXML
    private TextField SecondText;
    @FXML
    private Label PlayerLabel2;
    @FXML
    private ChoiceBox<String> PlayerBox2;
    @FXML
    private Button AddEventB;
    private String ChosenPlayerUsername;
    private String ChosenPlayerUsername2;
    private String ChosenTeam;
    ArrayList<Pair<String, String>> PlayerBody;
    private static double xOffset = 0;
    private static double yOffset = 0;
    private int typeofevent = 1;

    public void handle(javafx.scene.input.MouseEvent event) {
        xOffset = RefGames.stage.getX() - event.getScreenX();
        yOffset = RefGames.stage.getY() - event.getScreenY();
    }

    public void initialize(){
        GetTeams();
    }

    private void GetTeams() {
        String Home = RefGames.getChosenGame().getHome();
        String Away = RefGames.getChosenGame().getAway();
        ArrayList<String> Teams = new ArrayList<String>();
        Teams.add(Home);
        Teams.add(Away);
        ObservableList<String> TeamList = FXCollections.observableArrayList(Teams);
        TeamBox.setItems(TeamList);
    }

    public void CloseButton(javafx.scene.input.MouseEvent event) {
        RefGames.stage.close();
    }


    //SecondText.getText().matches("\\d\\d\\:\\d\\d\\:\\d\\d")

    public void AddnewGoalEvent() throws IOException {
        if(ChosenPlayerUsername != null){
            Connector con = Connector.getInstance();
            RefGames.Game test = RefGames.getChosenGame();
            String Time =HourText.getText()+"#"+MinuteText.getText()+"#"+SecondText.getText();
            Response response = con.AddnewGoalEvent(String.valueOf(RefGames.getChosenGame().getIndex()),Time,ChosenTeam,ChosenPlayerUsername);
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
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Choose a player");
            alert.showAndWait();
        }
    }

    public void AddnewYellowCardEvent() throws IOException {
        if(ChosenPlayerUsername != null){
            Connector con = Connector.getInstance();
            RefGames.Game test = RefGames.getChosenGame();
            String Time =HourText.getText()+"#"+MinuteText.getText()+"#"+SecondText.getText();
            Response response = con.AddnewYellowCardEvent(String.valueOf(RefGames.getChosenGame().getIndex()),Time,ChosenTeam,ChosenPlayerUsername);
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
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Choose a player");
            alert.showAndWait();
        }
    }

    public void AddnewRedCardEvent() throws IOException {
        if(ChosenPlayerUsername != null){
            Connector con = Connector.getInstance();
            RefGames.Game test = RefGames.getChosenGame();
            String Time =HourText.getText()+"#"+MinuteText.getText()+"#"+SecondText.getText();
            Response response = con.AddnewRedCardEvent(String.valueOf(RefGames.getChosenGame().getIndex()),Time,ChosenTeam,ChosenPlayerUsername);
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
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Choose a player");
            alert.showAndWait();
        }
    }

    public void AddnewInjuryEvent() throws IOException {
        if(ChosenPlayerUsername != null){
            Connector con = Connector.getInstance();
            RefGames.Game test = RefGames.getChosenGame();
            String Time =HourText.getText()+"#"+MinuteText.getText()+"#"+SecondText.getText();
            Response response = con.AddnewInjuryEvent(String.valueOf(RefGames.getChosenGame().getIndex()),Time,ChosenTeam,ChosenPlayerUsername);
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
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Choose a player");
            alert.showAndWait();
        }
    }

    public void AddnewFoulEvent() throws IOException {
        if(ChosenPlayerUsername != null){
            Connector con = Connector.getInstance();
            RefGames.Game test = RefGames.getChosenGame();
            String Time =HourText.getText()+"#"+MinuteText.getText()+"#"+SecondText.getText();
            Response response = con.AddnewFoulEvent(String.valueOf(RefGames.getChosenGame().getIndex()),Time,ChosenTeam,ChosenPlayerUsername,ChosenPlayerUsername2);
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
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Choose a player");
            alert.showAndWait();
        }
    }

    public void AddnewSubEvent() throws IOException {
        if((ChosenPlayerUsername != null && typeofevent==1) || (typeofevent==2 && ChosenPlayerUsername != null && ChosenPlayerUsername2 != null)){
            Connector con = Connector.getInstance();
            RefGames.Game test = RefGames.getChosenGame();
            String Time =HourText.getText()+"#"+MinuteText.getText()+"#"+SecondText.getText();
            Response response = con.AddnewSubEvent(String.valueOf(RefGames.getChosenGame().getIndex()),Time,ChosenTeam,ChosenPlayerUsername,ChosenPlayerUsername2);
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
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Bad Input");
            alert.showAndWait();
        }
    }

    public void ChangeEventType(){
        String ChosenEvent = (String)EventChooser.getSelectionModel().getSelectedItem();
        switch (ChosenEvent){
            case "Goal Event":
                PlayerLabel2.setDisable(true);
                PlayerBox2.setDisable(true);
                typeofevent = 1;
                AddEventB.setOnAction((event) -> {
                    try {
                        AddnewGoalEvent();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case "Yellow Card Event":
                PlayerLabel2.setDisable(true);
                PlayerBox2.setDisable(true);
                typeofevent = 1;
                AddEventB.setOnAction((event) -> {
                    try {
                        AddnewYellowCardEvent();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case "Red Card Event":
                PlayerLabel2.setDisable(true);
                PlayerBox2.setDisable(true);
                typeofevent = 1;
                AddEventB.setOnAction((event) -> {
                    try {
                        AddnewRedCardEvent();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case "Injury Event":
                PlayerLabel2.setDisable(true);
                PlayerBox2.setDisable(true);
                typeofevent = 1;
                AddEventB.setOnAction((event) -> {
                    try {
                        AddnewInjuryEvent();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case "Foul Event":
                PlayerLabel2.setDisable(true);
                PlayerBox2.setDisable(true);
                typeofevent = 1;
                AddEventB.setOnAction((event) -> {
                    try {
                        AddnewFoulEvent();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case "Subtitute Event":
                PlayerLabel2.setDisable(false);
                PlayerBox2.setDisable(false);
                typeofevent = 2;
                AddEventB.setOnAction((event) -> {
                    try {
                        AddnewSubEvent();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
        }
    }


    public void Drag(MouseEvent event) {
        RefGames.stage.setX(event.getScreenX() + xOffset);
        RefGames.stage.setY(event.getScreenY() + yOffset);
    }

    public void ChooseTeam(ActionEvent actionEvent) {
        String ChosenTeam = (String)TeamBox.getSelectionModel().getSelectedItem();
        ShowPlayers(Connector.getInstance().getPlayers(ChosenTeam));
        switch (typeofevent){
            case 1:
                break;
            case 2:
                ShowPlayers2(Connector.getInstance().getPlayers(ChosenTeam));
                break;
        }
        this.ChosenTeam = ChosenTeam;
    }

    private void ShowPlayers2(Response players) {
        ArrayList<String> Players = new ArrayList<String>();
        PlayerBody = players.getBody();
        for (Pair<String, String> p:PlayerBody) {
            Players.add(p.getKey().replace("\t","").replace("\"",""));
        }
        ObservableList<String> PlayerList = FXCollections.observableArrayList(Players);
        PlayerBox2.setItems(PlayerList);
    }


    private void ShowPlayers(Response players) {
        ArrayList<String> Players = new ArrayList<String>();
        PlayerBody = players.getBody();
        for (Pair<String, String> p:PlayerBody) {
            Players.add(p.getKey().replace("\t","").replace("\"",""));
        }
        ObservableList<String> PlayerList = FXCollections.observableArrayList(Players);
        PlayerBox.setItems(PlayerList);
    }

    public void ChoosePlayer(ActionEvent actionEvent) {
        ChosenPlayerUsername  = responseParser.getElement((String)PlayerBox.getSelectionModel().getSelectedItem().replace(" ", ""),PlayerBody);
    }

    public void ChoosePlayer2(ActionEvent actionEvent) {
        ChosenPlayerUsername2  = responseParser.getElement((String)PlayerBox2.getSelectionModel().getSelectedItem().replace(" ", ""),PlayerBody);
    }
}
