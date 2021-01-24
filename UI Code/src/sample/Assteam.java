package sample;

import Connector.Connector;
import HttpProtocol.Response;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class Assteam {
    @FXML
    BorderPane MainBorder;
    @FXML
    TableView TeamTable;
    @FXML
    TableColumn TeamNameCol;
    @FXML
    TableColumn TeamOwnerCol;
    @FXML
    TableColumn StadiumCol;
    public static Stage stage;

    public void initialize(){
        ShowTeams(Connector.getInstance().GetTeams());
    }

    public class Team {
        private String TeamName;
        private String OwnerName;
        private String Stadium;

        public Team(String teamname, String ownerName, String stadium){
            TeamName = teamname;
            OwnerName = ownerName;
            Stadium = stadium;
        }

        public String getTeamName() {
            return TeamName;
        }

        public String getOwnerName() {
            return OwnerName;
        }

        public String getStadium() {
            return Stadium;
        }
    }

    private void ShowTeams(Response Teams) {
        ArrayList<Pair<String,String>> teams = Teams.getBody();
        TeamNameCol.setCellValueFactory(new PropertyValueFactory<>("TeamName"));
        TeamOwnerCol.setCellValueFactory(new PropertyValueFactory<>("OwnerName"));
        StadiumCol.setCellValueFactory(new PropertyValueFactory<>("Stadium"));
        for (Pair<String,String> p : teams) {
            String[] info = p.getValue().split(";");
            Team newteam = new Team(p.getKey().replace("\t","").replace("\"",""),info[0].replace("\"",""),info[1].replace("\"",""));
            TeamTable.getItems().add(newteam);
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



    public void AddNewTeam(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AddTeam.fxml"));
            stage = new Stage();
            stage.setTitle("Add New Team");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 500, 400));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ShowRegisterScreen(ActionEvent actionEvent) {
        LoadMenu("UpdateInfo");
    }
}