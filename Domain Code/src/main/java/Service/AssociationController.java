package Service;

import Domain.Association.AssociationMember;
import Domain.Game.*;
import Domain.Jobs.Coach;
import Domain.Jobs.Player;
import Domain.Jobs.Referee;
import Domain.System.*;
import Domain.User.Member;
import Exceptions.DomainException;
import Exceptions.notFoundException;
import Server.core.Logger;
import http.Parser;
import http.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AssociationController {

    private AssociationResponses associationResponses;

    public AssociationController() {
        associationResponses=new AssociationResponses();
    }

    public void routing(String path, ArrayList<Parser.StringPair> body, Response response) throws notFoundException, DomainException {
        try {
            String input1, input2, input3, input4, input5;
            switch (path) {
                case "NewLeague":
                    input1 = Parser.getElement("Username", body);
                    input2 = Parser.getElement("LeagueName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    NewLeague(input1, input2);
                    break;
                case "AddSeasonToLeague":
                    input1 = Parser.getElement("Username", body);
                    input2 = Parser.getElement("LeagueName", body);
                    input3 = Parser.getElement("year", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    AddSeasonToLeague(input1, input2, Integer.parseInt(input3));
                    break;
                case "AddNewTeam":
                    input1 = Parser.getElement("Username", body);
                    input2 = Parser.getElement("TeamName", body);
                    input3 = Parser.getElement("ownerUserName", body);
                    input4 = Parser.getElement("stadiumName", body);
                    if (input1 == null || input2 == null || input3 == null || input4==null)
                        throw new DomainException("invalid input");
                    AddNewTeam(input1, input2, input3,input4);
                    response.addToBody("message","the team "+input2+" was created");
                    break;
                case "getTeams":
                    List<Team> teams=getTeams();
                    associationResponses.getTeamsResponse(teams,response);
                    break;
                case "getPolicies":
                    List<League> leagues=getLeagues();
                    associationResponses.getPoliciesResponse(leagues,response);
                    break;
                case "ChangeScoringPolicyForLeague":
                    input1 = Parser.getElement("Username", body);
                    input2 = Parser.getElement("LeagueToChange", body);
                    input3 = Parser.getElement("pPerWin", body);
                    input4 = Parser.getElement("pPerLoss", body);
                    input5 = Parser.getElement("pPerDraw", body);
                    if (input1 == null || input2 == null || input3 == null || input4 == null || input5 == null)
                        throw new DomainException("invalid input");
                    ChangeScoringPolicyForLeague(input1,input2,Integer.parseInt(input3),Integer.parseInt(input4),Integer.parseInt(input5));
                    response.addToBody("message","The scoring policy for league "+input2+" was change");
                    break;
                case "ChangeSchedulingPolicyForLeague":
                    input1 = Parser.getElement("Username", body);
                    input2 = Parser.getElement("LeagueToChange", body);
                    input3 = Parser.getElement("numOfMatches", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    ChangeSchedulingPolicyForLeague(input1,input2,Integer.parseInt(input3));
                    response.addToBody("message","The scheduling policy for league "+input2+" was change");
                    break;
                default:
                    throw new notFoundException();
            }
        }catch (Exception e){
            if(e.getClass().equals(DomainException.class))
                throw (DomainException)e;
            if(e.getClass().equals(NumberFormatException.class))
                throw (NumberFormatException)e;
            throw new notFoundException();
        }
    }

    public void NewLeague(String Username, String LeagueName) throws DomainException {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromMemory(8,Username);
        if(AssMember==null){
            throw new DomainException("No such username exists");
        }
        AssMember.NewLeague(LeagueName);
        Logger.getLogger().writeToEventLogger(LocalDateTime.now(),AssMember.getUser_name(),"add new league: "+LeagueName);
    }

    public void AddSeasonToLeague(String Username, String LeagueName, int year ) throws DomainException {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromMemory(8,Username);
        if(AssMember==null){
            throw new DomainException("No such username exists");
        }
        AssMember.AddSeasonToLeague(LeagueName,year);
        Logger.getLogger().writeToEventLogger(LocalDateTime.now(),AssMember.getUser_name(),"add new season:"+LeagueName+" "+year);
    }

    public void AddNewTeam(String Username, String TeamName, String ownerUserName, String stadiumName ) throws DomainException {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromMemory(2,Username);
        Member teamOwner= (Member) AlphaSystem.getSystem().GetSpecificFromMemory(2,ownerUserName);
        Stadium HomeStadium= (Stadium) AlphaSystem.getSystem().GetSpecificFromMemory(11,stadiumName);
        if(AssMember==null){
            throw new DomainException("No such username exists");
        }
        if(teamOwner==null){
            throw new DomainException("No such team owner exists");
        }
        if(HomeStadium==null){
            throw new DomainException("No such stadium exists");
        }
        AssMember.AddNewTeam(TeamName,teamOwner,HomeStadium);
        Logger.getLogger().writeToEventLogger(LocalDateTime.now(),AssMember.getUser_name(),"add new team: "+TeamName);
    }


    // to be continued
    public void AddNewRef(String Username,Member member) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromMemory(8,Username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        AssMember.AddNewRef(member);
    }

    public void AddRefToLeague(String Username,Referee RefToAdd, League league) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromMemory(8,Username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        AssMember.AddRefToLeague(RefToAdd, league);
    }

    public boolean AddPlayerJobToMember(String username, Member member, Player.Position pos, LocalDate dateOfBirth) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromMemory(8,username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        return AssMember.AddPlayerJobToMember(member, pos,dateOfBirth );
    }

    public boolean AddCoachJobToMember(String username,Member member, Coach.Certification certification) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromMemory(8,username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        return AssMember.AddCoachJobToMember(member, certification);
    }

    public void ChangeScoringPolicyForLeague(String Username,String LeagueToChange, int pPerWin,int pPerLoss,int pPerDraw) throws DomainException {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromMemory(2,Username);
        if(AssMember==null){
            throw new DomainException("No such username exists");
        }
        AssMember.ChangeScoringPolicyForLeague(LeagueToChange, pPerWin,pPerLoss, pPerDraw);
        Logger.getLogger().writeToEventLogger(LocalDateTime.now(),AssMember.getUser_name(),"change scoring policy");
    }

    public void ChangeSchedulingPolicyForLeague(String Username, String LeagueToChange, int numOfMatches) throws DomainException {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromMemory(2,Username);
        if(AssMember==null){
            throw new DomainException("No such username exists");
        }
        AssMember.ChangeSchedulingPolicyForLeague(LeagueToChange, numOfMatches);
        Logger.getLogger().writeToEventLogger(LocalDateTime.now(),AssMember.getUser_name(),"change scheduling policy");
    }

    public boolean ChangeScoringPolicyForSeason(String Username, String LeagueToChange,int year, int pPerWin,int pPerLoss,int pPerDraw) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromMemory(8,Username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        return AssMember.ChangeScoringPolicyForSeason(LeagueToChange, year, pPerWin, pPerLoss, pPerDraw);
    }

    public boolean ChangeSchedulingPolicyForSeason(String Username, String LeagueToChange,int year, int numOfMatches) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromMemory(8,Username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        return AssMember.ChangeSchedulingPolicyForSeason(LeagueToChange, year, numOfMatches);
    }

    public void AddTeamToSeasonInLeague(String Username, String LeagueName, int seasonYear,Team team ) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromMemory(8,Username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        AssMember.AddTeamToSeasonInLeague(LeagueName, seasonYear, team);
    }

    public List<Team> getTeams(){
        return (List<Team>) AlphaSystem.getSystem().GetAllFromMemory(4);
    }

    public List<League> getLeagues() throws DomainException {
        return (List<League>) AlphaSystem.getSystem().GetAllFromMemory(1);
    }


}
