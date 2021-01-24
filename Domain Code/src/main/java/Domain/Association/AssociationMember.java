package Domain.Association;

import Domain.Game.*;
import Domain.User.*;
import Domain.System.*;
import Domain.Jobs.*;
import Exceptions.DomainException;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AssociationMember extends Member {

    public AssociationMember(Member member) {
        super(member);
    }

    public AssociationMember(String user_name, String user_password, String user_id, String full_name) {
        super(user_name, user_password, user_id, full_name);
        AlphaSystem.getSystem().AddtoMemory(2,this);
    }

    public void NewLeague(String LeagueName){
        //should be in League
        AlphaSystem system = AlphaSystem.getSystem();
        League league=new League(LeagueName,new SchedulingPolicy(),new ScoringPolicy());
        system.AddtoMemory(1,league);
        AlphaSystem.getSystem().getDB().insert(league);
    }

    public void startLeagueSeason(String leagueName,int seasonYear){
        AlphaSystem system = AlphaSystem.getSystem();
        League league = (League) system.GetSpecificFromMemory(1,leagueName);
        Season season = league.getSpecSeason(seasonYear);
        season.scheduleGames(leagueName);
        season.scheduleMainReferees(league.getLeagueReferees());
        season.scheduleVarReferees(league.getLeagueVarReferees());
        season.scheduleLinesMansReferees(league.getLeagueLinesmans());
    }

    public void AddSeasonToLeague(String LeagueName, int year ){
        AlphaSystem system = AlphaSystem.getSystem();
        League CurrLeague = (League)system.GetSpecificFromMemory(1,LeagueName);
        CurrLeague.addSeason(year, CurrLeague.getSchedulingPolicy(), CurrLeague.getScoringPolicy());
        AlphaSystem.getSystem().getDB().addSeasonToDB(CurrLeague.getSpecSeason(year),LeagueName);
    }

    public void AddNewTeam(String TeamName, Member teamOwner, Stadium HomeStadium ) throws DomainException {
        TeamOwner Owner = new TeamOwner(teamOwner);
        teamOwner.addJob(Owner);
        Team NewTeam = new Team(TeamName, Owner, HomeStadium);
        NewTeam.addOwner(Owner);
        AlphaSystem system = AlphaSystem.getSystem();
        List<SystemAdmin> Admins = system.getAdmin();
        if (Admins.size()>0)
            NewTeam.registerSystemAdmin(Admins.get(0));
        AlphaSystem.getSystem().getDB().insert(NewTeam);
        AlphaSystem.getSystem().getDB().insert(teamOwner);
    }

    public void addNewStadium(String name,String city){
        Stadium stadium=new Stadium(name,city);
        AlphaSystem.getSystem().getDB().insert(stadium);
    }


    // to be continued
    public void AddNewRef(Member member){
        new Referee(member);
        //שליחת הזמנה?
    }

    public void AddRefToLeague(Referee RefToAdd, League league){
         league.AddRef(RefToAdd);
        if(RefToAdd instanceof MainReferee)
            AlphaSystem.getSystem().getDB().addMainRefereeToLeagueInDB((MainReferee) RefToAdd,league);
        if(RefToAdd instanceof LinesManReferee)
            AlphaSystem.getSystem().getDB().addLineRefereeToLeagueInDB((LinesManReferee) RefToAdd,league);
        if(RefToAdd instanceof VarReferee)
            AlphaSystem.getSystem().getDB().addVarRefereeToLeagueInDB((VarReferee) RefToAdd,league);
    }

    public void ChangeScoringPolicyForLeague(String LeagueToChange, int pPerWin,int pPerLoss,int pPerDraw){
        AlphaSystem system = AlphaSystem.getSystem();
        League CurrLeague = (League)system.GetSpecificFromMemory(1,LeagueToChange);
        CurrLeague.setScoringPolicy(pPerWin,pPerLoss,pPerDraw);
        AlphaSystem.getSystem().getDB().updateLeagueScoringP(LeagueToChange,pPerWin,pPerLoss,pPerDraw);
    }

    public void ChangeSchedulingPolicyForLeague(String LeagueToChange, int numOfMatches){
        AlphaSystem system = AlphaSystem.getSystem();
        League CurrLeague = (League)system.GetSpecificFromMemory(1,LeagueToChange);
        CurrLeague.setSchedulingPolicy(numOfMatches);
        AlphaSystem.getSystem().getDB().updateLeagueSchedualingP(LeagueToChange,numOfMatches);
    }

    public boolean ChangeScoringPolicyForSeason(String LeagueToChange,int year, int pPerWin,int pPerLoss,int pPerDraw){
        AlphaSystem system = AlphaSystem.getSystem();
        League CurrLeague = (League)system.GetSpecificFromMemory(1,LeagueToChange);
        if(CurrLeague.setSeasonScoringPolicy(year,pPerWin,pPerLoss,pPerDraw)){
            AlphaSystem.getSystem().getDB().updateSeasonScoringP(LeagueToChange,year,pPerWin,pPerLoss,pPerDraw);
            return true;
        }

        return false;
    }

    public boolean ChangeSchedulingPolicyForSeason(String LeagueToChange,int year, int numOfMatches){
        AlphaSystem system = AlphaSystem.getSystem();
        League CurrLeague = (League)system.GetSpecificFromMemory(1,LeagueToChange);
        if(CurrLeague.setSeasonSchedulingPolicy(year,numOfMatches)) {
            AlphaSystem.getSystem().getDB().updateSeasonSchedualingP(LeagueToChange,year,numOfMatches);
            return true;
        }
        return false;
    }

    public void AddTeamToSeasonInLeague(String LeagueName, int seasonYear,Team team ){
        AlphaSystem system = AlphaSystem.getSystem();
        League CurrLeague = (League)system.GetSpecificFromMemory(1,LeagueName);
        try {
            LeaguePosition position=CurrLeague.getSpecSeason(seasonYear).addTeamToSeason(team);
            AlphaSystem.getSystem().getDB().addLeaguePositionToDB(position,seasonYear+"",LeagueName);
        } catch (DomainException e) {
            e.printStackTrace();
        }
    }

    public boolean AddPlayerJobToMember(Member member, Player.Position pos, LocalDate dateOfBirth){
        Player newplayer = new Player(member, pos, dateOfBirth);
        AlphaSystem.getSystem().getDB().insert(newplayer);
        return member.addJob(newplayer);
    }

    public boolean AddCoachJobToMember(Member member, Coach.Certification certification){
        Coach newCoach = new Coach(member, certification);
        AlphaSystem.getSystem().getDB().insert(newCoach);
        return member.addJob(newCoach);
    }

    public boolean AddManagerJobToMember(Member member){
        TeamManager newCoach = new TeamManager(member,null,new ArrayList<>());
        AlphaSystem.getSystem().getDB().insert(newCoach);
        return member.addJob(newCoach);
    }

    public boolean AddOwnerJobToMember(Member member){
        TeamOwner newOwner = new TeamOwner(member);
        AlphaSystem.getSystem().getDB().insert(newOwner);
        return member.addJob(newOwner);
    }





}
