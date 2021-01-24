package Service;

import Domain.Game.Team;
import Domain.Jobs.Player;
import Domain.System.AlphaSystem;
import Exceptions.DomainException;
import Exceptions.notFoundException;
import http.Parser;
import http.Response;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private GameResponses gameResponses;

    public GameController() {
        gameResponses=new GameResponses();
    }

    public void routing(String path, ArrayList<Parser.StringPair> body, Response response) throws notFoundException, DomainException {
        try {
            String input1, input2, input3, input4;
            switch (path) {
                case "getTeamPlayers":
                    input1 = Parser.getElement("teamName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    List<Player> players= getTeamPlayers(input1);
                    gameResponses.getTeamPlayers(players,response);
                    break;
                default:
                    throw new notFoundException();
            }
        } catch (Exception e) {
            if (e.getClass().equals(DomainException.class))
                throw (DomainException) e;
            if (e.getClass().equals(NumberFormatException.class))
                throw (NumberFormatException) e;
            throw new notFoundException();
        }
    }

    public List<Player> getTeamPlayers(String teamName) throws DomainException {
       Team team= (Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,teamName);
       if(team==null)
           throw new DomainException("There is no team with this name");
       return team.getPlayers();
    }
}
