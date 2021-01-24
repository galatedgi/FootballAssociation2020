package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Association {
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

    public void ShowPoliciesScreen(ActionEvent actionEvent) {
        LoadMenu("Policies");
    }

    public void ShowTeamsScreen(ActionEvent actionEvent) {
        LoadMenu("AssTeam");
    }
}
