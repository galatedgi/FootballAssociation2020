package Service;

import Domain.Jobs.Player;
import http.Response;

import java.util.List;

public class GameResponses {

    public void getTeamPlayers(List<Player> players, Response response){
        for(Player player:players)
            response.addToBody(player.getMemberFullName(),player.getMemberUserName());
    }
}
