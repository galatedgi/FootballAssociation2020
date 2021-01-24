package Service;

import Domain.Game.League;
import Domain.Game.SchedulingPolicy;
import Domain.Game.ScoringPolicy;
import Domain.Game.Team;
import Domain.Jobs.TeamOwner;
import Domain.System.AlphaSystem;
import Exceptions.DomainException;
import http.Response;

import java.util.List;

public class AssociationResponses {

    public void getTeamsResponse(List<Team> teams, Response response){
        for(Team team: teams){
            String value="";
            for(TeamOwner owner:team.getOwners())
                value+=owner.getMemberFullName()+",";
            value=value.substring(0,value.length()-1)+";"+team.getHomeStadium().getStadiumName();
            response.addToBody(team.getTeamName(),value);
        }

    }

    public void getPoliciesResponse(List<League> leagues, Response response) throws DomainException {
        for(League league: leagues){
            ScoringPolicy scoP=league.getScoringPolicy();
            SchedulingPolicy schP=league.getSchedulingPolicy();
            String value=scoP.getPointsPerWin()+";"+scoP.getPointsPerDraw()+";"+scoP.getPointPerLoss()+"/"+schP.getNumOf2TeamsGames();
            response.addToBody(league.getName(),value);
        }
    }
}
