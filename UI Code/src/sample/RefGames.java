package sample;

import Connector.Connector;
import HttpProtocol.Response;
import HttpProtocol.responseParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RefGames {
    @FXML
    BorderPane MainBorder;
    @FXML
    TableView GamesTable;
    @FXML
    TableColumn DateCol;
    @FXML
    TableColumn StadiumCol;
    @FXML
    TableColumn HomeCol;
    @FXML
    TableColumn AwayCol;
    @FXML
    TableColumn EventsCol;

    public static Stage stage;
    public static Stage ReportStage;
    private Game chosen;
    private static Game chosengame;
    private static String ReportToShow;


    public static String getReportToShow() {
        return ReportToShow;
    }

    public void initialize(){
        ShowGames(Connector.getInstance().getRefGames());
    }

    public class Game {
        private String Date;
        private String Stadium;
        private String Home;
        private String Away;
        private int Events;
        private int Index;

        public Game(String date, String stadium, String home, String away, String events,String index){
            Date = date.replace("#",":");
            Stadium = stadium;
            Home = home;
            Away = away;
            Events = Integer.parseInt(events);
            Index = Integer.parseInt(index);
        }

        public String getDate() {
            return Date;
        }

        public String getStadium() {
            return Stadium;
        }

        public String getHome() {
            return Home;
        }

        public String getAway() {
            return Away;
        }

        public int getEvents() {
            return Events;
        }

        public int getIndex() {
            return Index;
        }
    }

    private void ShowGames(Response Leagues) {
        ArrayList<Pair<String,String>> leagues = Leagues.getBody();
        DateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
        StadiumCol.setCellValueFactory(new PropertyValueFactory<>("Stadium"));
        HomeCol.setCellValueFactory(new PropertyValueFactory<>("Home"));
        AwayCol.setCellValueFactory(new PropertyValueFactory<>("Away"));
        EventsCol.setCellValueFactory(new PropertyValueFactory<>("Events"));
        for (Pair<String,String> p : leagues) {
            String[] info = p.getValue().split(";");
            Game newLeague = new Game(p.getKey().replace("\t","").replace("\"",""),info[0].replace("\"",""),info[1].replace("\"",""),info[2].replace("\"",""),info[3].replace("\"",""),info[4].replace("\"",""));
            GamesTable.getItems().add(newLeague);
        }
    }



    private void LoadMenu(String ui){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException e){

        }
        MainBorder.setCenter(root);
    }


    public void AddnewEvent(ActionEvent actionEvent) {
        Parent root;
        if(ChooseGame()){
            try {
                root = FXMLLoader.load(getClass().getResource("NewEvent.fxml"));
                stage = new Stage();
                stage.setTitle("Add new Event");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root, 500, 400));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please choose a game from the table.");
            alert.showAndWait();
        }
    }

    public void ShowReport(ActionEvent actionEvent) {
        Parent root;
        if(ChooseGame()){
            try {
                getReport();
                root = FXMLLoader.load(getClass().getResource("Report.fxml"));
                ReportStage = new Stage();
                ReportStage.setTitle("Add new Event");
                ReportStage.initStyle(StageStyle.UNDECORATED);
                ReportStage.setScene(new Scene(root, 500, 330));
                ReportStage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please choose a game from the table.");
            alert.showAndWait();
        }
    }

    public void EndGame(ActionEvent actionEvent) {
        Parent root;
        if(ChooseGame()){
            Connector.getInstance().EndGame(chosengame.getIndex());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please choose a game from the table.");
            alert.showAndWait();
        }
    }

    private void getReport() {
        Response Report = Connector.getInstance().GetReport(chosengame.getIndex());
        ReportToShow = responseParser.getElement("report",Report.getBody()).replace("$","\n");
    }


    public boolean ChooseGame(){
        chosen = (Game)GamesTable.getSelectionModel().getSelectedItem();
        if (chosen != null){
            chosengame = chosen;
            return true;
        }
        return false;
    }


    public static Game getChosenGame() {
        return chosengame;
    }


    public void ShowRegisterScreen(ActionEvent actionEvent) {
        LoadMenu("UpdateInfo");
    }
}