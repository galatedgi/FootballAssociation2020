package Service;

import Domain.Game.FootballGame;
import http.Response;

import java.util.List;

public class JobsResponses {

    public void GetRefereeGamesResponse(List<FootballGame> games, Response response){
        for(FootballGame game: games){
            String key=game.getDate().toString().replace(":","#");
            String value=game.getStadium().getStadiumName()+";"+game.getHomeTeamName()+";"+game.getAwayTeamName()+";"+game.getNumOfEvents()+";"+games.indexOf(game);
            response.addToBody(key,value);
        }
    }
}
