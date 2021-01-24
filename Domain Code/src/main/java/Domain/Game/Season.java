package Domain.Game;
import  Domain.Events.*;
import  Domain.User.*;
import  Domain.System.*;
import  Domain.Jobs.*;
import  Domain.Association.*;

import  Domain.Game.*;
import  Domain.Jobs.LinesManReferee;
import Exceptions.DomainException;
import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.*;

public class Season {

    private LeagueTable table;
    private LinkedList<FootballGame> games;
    private int year;
    private SchedulingPolicy schedulingPolicy;
    private ScoringPolicy scoringPolicy;

    public boolean setSchedulingPolicy(int numOfMatches) {
        if(games.size()!=0)
            return false;
        this.schedulingPolicy = new SchedulingPolicy(numOfMatches);
        return true;
    }

    public boolean setScoringPolicy(int pPerWin,int pPerLoss,int pPerDraw) {
        if(games.size()!=0)
            return false;
        this.scoringPolicy = new ScoringPolicy(pPerWin,pPerLoss,pPerDraw);
        return true;
    }

    public Season(int year, SchedulingPolicy schedulingPolicy, ScoringPolicy scoringPolicy) {
        this.year = year;
        this.schedulingPolicy = schedulingPolicy;
        this.scoringPolicy = scoringPolicy;
        if(this.schedulingPolicy==null){
            this.schedulingPolicy=new SchedulingPolicy(1);
        }
        if(this.scoringPolicy==null){
            this.scoringPolicy=new ScoringPolicy(3,1,0);
        }
        this.table=new LeagueTable();
        this.games=new LinkedList();
    }

    public void scheduleMainReferees(List<MainReferee> referees){
        int index=0;
        if(referees.size()==0)
            return;
        for (FootballGame game:games) {
            game.setMainReferee(referees.get(index));
            referees.get(index).AddGameToRef(game);
            index++;
            if(index==referees.size())
                index=0;
        }
    }

    public void scheduleLinesMansReferees(List<LinesManReferee> referees){
        int index=0;
        if(referees.size()<2)
            return;
        for (FootballGame game:games) {
            game.setLinesManLeft(referees.get(index));
            index++;
            if(index==referees.size())
                index=0;
            game.setLinesManRight(referees.get(index));
            index++;
            if(index==referees.size())
                index=0;
        }
    }

    public void scheduleVarReferees(List<VarReferee> referees){
        int index=0;
        if(referees.size()==0)
            return;
        for (FootballGame game:games) {
            game.setVarReferee(referees.get(index));
            index++;
            if(index==referees.size())
                index=0;
        }
    }

    public LeaguePosition addTeamToSeason(Team team) throws DomainException {
        LeaguePosition position=table.addTeam(team);
        if(table.addTeam(team)==null)
            throw new DomainException("this team already plays in this season");
        return position;
    }

    public int getYear() {
        return year;
    }

    //need to fix
    public LinkedList<Pair<LeaguePosition, Integer>> getRankings() { //need to break the tie!!!!!!!!!!!!!!!!!!
        LinkedList<Pair<LeaguePosition, Integer>> rankings = table.getTeamsPoints(scoringPolicy.getPointsPerWin(), scoringPolicy.getPointPerLoss(), scoringPolicy.getPointsPerDraw());
        Collections.sort(rankings, new Comparator<Pair<LeaguePosition, Integer>>() { @Override public int compare(Pair<LeaguePosition, Integer> s1, Pair<LeaguePosition, Integer> s2)
        { return s1.getValue() - s2.getValue(); } } );
        Collections.reverse(rankings);
        return rankings;
    }

    public boolean addWin(Team team, int goalsScored, int goalsReceived){
        if(goalsScored<=goalsReceived)
            return false;
        table.addWin(team,goalsScored,goalsReceived);
        return true;
    }
    public boolean addLoss(Team team, int goalsScored, int goalsReceived){
        if(goalsScored>=goalsReceived)
            return false;
        table.addLoss(team,goalsScored,goalsReceived);
        return true;
    }
    public boolean addDraw(Team team, int goalsScored, int goalsReceived){
        if(goalsReceived!=goalsScored)
            return false;
        table.addDraw(team,goalsScored,goalsReceived);
        return true;
    }


    public void scheduleGames(String leagueName){
        LinkedList<Team> teams = table.getAllTeams();
        for (int i=0;i<schedulingPolicy.getNumOf2TeamsGames();i++){
            LinkedList<Team> usedTeames=new LinkedList();
            while (teams.size()>0){
                Team home=teams.removeFirst();
                for (Team away:teams) {
                    FootballGame newGame=new FootballGame(this,home,away,genterateRandomDate(year));
                    addGame(newGame,leagueName);
                    AlphaSystem.getSystem().getDB().insert(newGame);
                }
                usedTeames.addFirst(home);
            }
            teams=usedTeames;
        }
    }
    public LeagueTable getTable(){
        return this.table;
    }

    public LinkedList<FootballGame> getGames() {
        return games;
    }

    public LocalDateTime genterateRandomDate(int year) {
        Random random = new Random();
        int Day = random.nextInt(29)+1;
        int month =random.nextInt(11)+1;
        int hour=random.nextInt(23);
        int minute=random.nextInt(59);

        LocalDateTime randomDate = LocalDateTime.of(year,month,Day,hour,minute,0);
        return randomDate;
    }

    public SchedulingPolicy getSchedulingPolicy() {
        return schedulingPolicy;
    }

    public ScoringPolicy getScoringPolicy() {
        return scoringPolicy;
    }

    public void setTable(LinkedList<LeaguePosition> table) {
        this.table.leagueTable=table;
    }

    public void addGame(FootballGame currGame,String leagueName) {
        games.add(currGame);
    }
}

