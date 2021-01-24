package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Referee {
    @FXML
    BorderPane MainBorder;




    private void LoadMenu(String ui){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException e){

        }
        MainBorder.setCenter(root);
    }

    public void ShowGamesScreen(ActionEvent actionEvent) {
        LoadMenu("RefGames");
    }

    public void ShowUpdateInfo(ActionEvent actionEvent) {
        LoadMenu("UpdateInfo");
    }
}
