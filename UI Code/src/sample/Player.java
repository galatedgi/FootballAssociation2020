package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Player {
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

    public void ShowTweetScreen(ActionEvent actionEvent) {
        LoadMenu("TweetForm");
    }

    public void ShowRegisterScreen(ActionEvent actionEvent) {
        LoadMenu("UpdateInfo");
    }
}
