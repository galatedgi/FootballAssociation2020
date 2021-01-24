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

public class Policies {
    @FXML
    BorderPane MainBorder;
    @FXML
    TableView PolicyTable;
    @FXML
    TableColumn LeagueNameCol;
    @FXML
    TableColumn NumCol;
    @FXML
    TableColumn WinCol;
    @FXML
    TableColumn TieCol;
    @FXML
    TableColumn LossCol;
    public static Stage stage;
    private League chosen;
    private static String chosenname;

    public void initialize(){
        ShowPolicies(Connector.getInstance().getPolicies());
    }

    public class League {
        private String LeagueName;
        private int NumofGames;
        private int Win;
        private int Tie;
        private int Loss;

        public League(String leaguename, int num, int W, int T, int L){
           LeagueName = leaguename;
           NumofGames = num;
           Win = W;
           Tie = T;
           Loss = L;
        }

        public String getLeagueName() {
            return LeagueName;
        }

        public int getNumofGames() {
            return NumofGames;
        }

        public int getWin() {
            return Win;
        }

        public int getTie() {
            return Tie;
        }

        public int getLoss() {
            return Loss;
        }
    }

    private void ShowPolicies(Response Leagues) {
        ArrayList<Pair<String,String>> leagues = Leagues.getBody();
        LeagueNameCol.setCellValueFactory(new PropertyValueFactory<>("LeagueName"));
        NumCol.setCellValueFactory(new PropertyValueFactory<>("NumofGames"));
        WinCol.setCellValueFactory(new PropertyValueFactory<>("Win"));
        TieCol.setCellValueFactory(new PropertyValueFactory<>("Tie"));
        LossCol.setCellValueFactory(new PropertyValueFactory<>("Loss"));
        for (Pair<String,String> p : leagues) {
            String[] info = p.getValue().split("/");
            String[] Scoreinfo = info[0].split(";");
            League newLeague = new League(p.getKey().replace("\t","").replace("\"",""),Integer.parseInt(info[1].replace("\"","")),Integer.parseInt(Scoreinfo[0].replace("\"","")),Integer.parseInt(Scoreinfo[1].replace("\"","")),Integer.parseInt(Scoreinfo[2].replace("\"","")));
            PolicyTable.getItems().add(newLeague);
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

    public boolean ChooseLeague(){
        chosen = (League)PolicyTable.getSelectionModel().getSelectedItem();
        if (chosen != null){
            chosenname = chosen.getLeagueName();
            return true;
        }
        return false;
    }



    public static String getChosenname() {
        return chosenname;
    }

    public void AddnewScoringPolicy(ActionEvent actionEvent) {
        Parent root;
        if(ChooseLeague()){
            try {
                root = FXMLLoader.load(getClass().getResource("ChangeScoringPolicy.fxml"));
                stage = new Stage();
                stage.setTitle("Change Scoring Policy for "+chosenname+" League.");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root, 500, 400));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please choose a league by selecting a league in the table.");
            alert.showAndWait();
        }
    }


    public void AddnewSchedulingPolicy(ActionEvent actionEvent) {
        Parent root;
        if(ChooseLeague()){
            try {
                root = FXMLLoader.load(getClass().getResource("ChangeSchedulingPolicy.fxml"));
                stage = new Stage();
                stage.setTitle("Change Scheduling Policy for "+chosenname+" League.");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root, 500, 240));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please choose a league by selecting a league in the table.");
            alert.showAndWait();
        }
    }

    public void ShowRegisterScreen(ActionEvent actionEvent) {
        LoadMenu("UpdateInfo");
    }
}