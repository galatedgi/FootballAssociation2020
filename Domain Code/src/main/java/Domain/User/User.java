package Domain.User;//roei cohen
import Domain.Game.Season;
import Domain.System.*;
import Domain.Jobs.*;
import Domain.Game.League;
import Domain.Game.Team;
import Domain.Jobs.LinesManReferee;
import Domain.Jobs.VarReferee;
import javafx.util.Pair;

import java.util.*;

public abstract class User {

    public Visitor visitor;
    public String visitorid;

    public User(){
        //visitor=new Visitor();
        visitorid=Visitor.generateVisitorId();
    }


    public List<String> showLeagueScoringPolicy(String leagueName){
        AlphaSystem system= AlphaSystem.getSystem();
        League chosenLeague=(League) system.GetSpecificFromMemory(1,leagueName);
        if(chosenLeague==null)
            return null;
        LinkedList<String> ret=new LinkedList<>();
        ret.add(String.valueOf(chosenLeague.getScoringPolicy().getPointsPerWin()));
        ret.add(String.valueOf(chosenLeague.getScoringPolicy().getPointsPerDraw()));
        ret.add(String.valueOf(chosenLeague.getScoringPolicy().getPointPerLoss()));
        return ret;
    }

    public List<String> showLeagueSpecSeasonScoringPolicy(String leagueName,int year){
        AlphaSystem system= AlphaSystem.getSystem();
        League chosenLeague=(League) system.GetSpecificFromMemory(1,leagueName);
        if(chosenLeague==null)
            return null;
        Season season=chosenLeague.getSpecSeason(year);
        if(season==null)
            return null;
        LinkedList<String> ret=new LinkedList<>();
        ret.add(String.valueOf(season.getScoringPolicy().getPointsPerWin()));
        ret.add(String.valueOf(season.getScoringPolicy().getPointsPerDraw()));
        ret.add(String.valueOf(season.getScoringPolicy().getPointPerLoss()));
        return ret;
    }


    public LinkedList<Pair<String, Integer>> showLeagueTable(String leagueName,int SeasonYear){
        AlphaSystem system= AlphaSystem.getSystem();
        League chosenLeague=(League) system.GetSpecificFromMemory(1,leagueName);
        if(chosenLeague==null)
            return null;
        LinkedList<Pair<String, Integer>> ret = chosenLeague.getSeasonRankings(SeasonYear);
        return ret;
    }

    public List<String> showLeagueMainReferees(String leagueName){
        AlphaSystem system= AlphaSystem.getSystem();
        League chosenLeague=(League) system.GetSpecificFromMemory(1,leagueName);
        if(chosenLeague==null)
            return null;
        LinkedList<String> ret=new LinkedList<>();
        List<MainReferee> refs = chosenLeague.getLeagueReferees();
        for (MainReferee ref:refs) {
            ret.add(ref.getMemberFullName());
        }
        return ret;
    }

    public List<String> showLeagueVarReferees(String leagueName){
        AlphaSystem system= AlphaSystem.getSystem();
        League chosenLeague=(League) system.GetSpecificFromMemory(1,leagueName);
        if(chosenLeague==null)
            return null;
        LinkedList<String> ret=new LinkedList<>();
        List<VarReferee> refs = chosenLeague.getLeagueVarReferees();
        for (VarReferee ref:refs) {
            ret.add(ref.getMemberFullName());
        }
        return ret;
    }

    public List<String> showLeagueLinesManReferees(String leagueName){
        AlphaSystem system= AlphaSystem.getSystem();
        League chosenLeague=(League) system.GetSpecificFromMemory(1,leagueName);
        if(chosenLeague==null)
            return null;
        LinkedList<String> ret=new LinkedList<>();
        List<LinesManReferee> refs = chosenLeague.getLeagueLinesmans();
        for (LinesManReferee ref:refs) {
            ret.add(ref.getMemberFullName());
        }
        return ret;
    }

    public List<String> showTeamPlayers(String teamName){
        AlphaSystem system= AlphaSystem.getSystem();
        Team chosenTeam=(Team) system.GetSpecificFromMemory(4,teamName);
        if(chosenTeam==null)
            return null;
        LinkedList<String> players=new LinkedList<>();
        for (Player player:chosenTeam.getPlayers()) {
            players.add(player.getMemberFullName());
        }
        return players;
    }

    public List<String> showTeamCoaches(String teamName){
        AlphaSystem system= AlphaSystem.getSystem();
        Team chosenTeam=(Team) system.GetSpecificFromMemory(4,teamName);
        if(chosenTeam==null)
            return null;
        LinkedList<String> coaches=new LinkedList<>();
        for (Coach coach:chosenTeam.getCoaches()) {
            coaches.add(coach.getMemberFullName());
        }
        return coaches;
    }

    public List<String> showTeamManagers(String teamName){
        AlphaSystem system= AlphaSystem.getSystem();
        Team chosenTeam=(Team) system.GetSpecificFromMemory(4,teamName);
        if(chosenTeam==null)
            return null;
        LinkedList<String> managers=new LinkedList<>();
        for (TeamManager manager:chosenTeam.getManagers()) {
            managers.add(manager.getMemberFullName());
        }
        return managers;
    }

    public List<String> showTeamOwners(String teamName){
        AlphaSystem system= AlphaSystem.getSystem();
        Team chosenTeam=(Team) system.GetSpecificFromMemory(4,teamName);
        if(chosenTeam==null)
            return null;
        LinkedList<String> owners=new LinkedList<>();
        for (TeamOwner owner:chosenTeam.getOwners()) {
            owners.add(owner.getMemberFullName());
        }
        return owners;
    }

    public String showTeamStadium(String teamName){
        AlphaSystem system= AlphaSystem.getSystem();
        Team chosenTeam=(Team) system.GetSpecificFromMemory(4,teamName);
        if(chosenTeam==null)
            return null;
        return chosenTeam.getHomeStadium().getStadiumName();
    }

    public String showPlayerTeam(String playerName){
        AlphaSystem system= AlphaSystem.getSystem();
        Player chosenPlayer=(Player) system.GetSpecificFromMemory(7,playerName);
        if(chosenPlayer==null)
            return null;
        return chosenPlayer.getTeam().getTeamName();
    }
    public String showPlayerPosition(String playerName){
        AlphaSystem system= AlphaSystem.getSystem();
        Player chosenPlayer=(Player) system.GetSpecificFromMemory(7,playerName);
        if(chosenPlayer==null)
            return null;
        return chosenPlayer.getPositionName();
    }
    public String showPlayerBirthDate(String playerName){
        AlphaSystem system= AlphaSystem.getSystem();
        Player chosenPlayer=(Player) system.GetSpecificFromMemory(7,playerName);
        if(chosenPlayer==null)
            return null;
        return chosenPlayer.getStringBirthDate();
    }

    public List<Object> searchByName(String name,boolean leagueBool,boolean coachBool,boolean teamBool,boolean manBool,boolean ownerBool,boolean playerBool,boolean refBool,boolean stadBool){
        Search search=new Search();
        List<Object> ret=search.searchByName(name,leagueBool,coachBool,teamBool,manBool,ownerBool,playerBool,refBool,stadBool);
        return ret;
    }

    public List SearchByCategory(int category){
        Search search=new Search();
        List<Object> ret=search.searchByCategory(category);
        return ret;
    }

    public List<String> showCoachPrivateInfo(String coachName){
        AlphaSystem system = AlphaSystem.getSystem();
        Coach coach = (Coach) system.GetSpecificFromMemory(3,coachName);
        if (coach == null)
            return null;
        List<String> coachInfo = new ArrayList<>();
        coachInfo.add(coach.getMember().getFull_name());
        coachInfo.add(coach.getTeam().getTeamName());
        return coachInfo;
    }



    public List<String> showTeamManagerPrivateInfo(String teamManagerName){
        AlphaSystem system = AlphaSystem.getSystem();
        TeamManager teamManager = (TeamManager) system.GetSpecificFromMemory(5,teamManagerName);
        if (teamManager == null)
            return null;
        List<String> teamManagerInfo = new ArrayList<>();
        teamManagerInfo.add(teamManager.getMember().getFull_name());
        teamManagerInfo.add(teamManager.getTeam().getTeamName());
        return teamManagerInfo;
    }

}
