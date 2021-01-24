package Domain.System;

import Domain.Association.AssociationMember;
import Domain.DBAccess.*;
import Domain.Events.*;
import Domain.Game.*;
import Domain.Jobs.*;
import Domain.User.Member;
import Exceptions.DomainException;
import javafx.util.Pair;

import java.time.LocalDateTime;

public class InsertToDB {
    SQLServerDBAccess DBAccess;
    public InsertToDB(String connectionURL){
        DBAccess=new SQLServerDBAccess(connectionURL);
    }

    private void addLeagueToDB(League league){
        if(league==null||league.getName()==null)
            return;
        DBAccess.insertLeagueToDB(league.getName(),
                league.getSchedulingPolicy().getNumOf2TeamsGames(),
                league.getScoringPolicy().getPointsPerWin(),
                league.getScoringPolicy().getPointPerLoss(),
                league.getScoringPolicy().getPointsPerDraw());
    }

    private void addAssociationMemberToDB(AssociationMember member){
        if(member==null)
            return;
        String[] values={member.getUser_name()};
        DBAccess.insertToDB(4,values);
    }

    public void addSeasonToDB(Season season,String league) {
        if(season==null||league==null)
            return;
        DBAccess.insertSeasonToDB(league,
                season.getYear(),
                season.getSchedulingPolicy().getNumOf2TeamsGames(),
                season.getScoringPolicy().getPointsPerWin(),
                season.getScoringPolicy().getPointPerLoss(),
                season.getScoringPolicy().getPointsPerDraw());
    }

    public void addGameToDB(FootballGame game,String SeasonYear,String LeageName) {
        if(game==null||SeasonYear==null||LeageName==null)
            return;
        String stadium=game.getStadium().getStadiumName();
        String date=game.getDate().toString();
        String mainRef=game.getMainReferee().getMember().getUser_name();
        String varRef=game.getVarReferee().getMember().getUser_name();
        String line1=game.getLinesManLeft().getMember().getUser_name();
        String line2=game.getLinesManRight().getMember().getUser_name();
        DBAccess.insertGameToDB(LeageName,SeasonYear,game.getHomeTeamName(),game.getAwayTeamName(),date,
                game.getHomeGoals(),game.getAwayGoals(),stadium,mainRef,varRef,line1,line2);
    }


    public void addLeaguePositionToDB(LeaguePosition leaguePosition,String year,String leagueName){
        if(leaguePosition==null||year==null||leagueName==null)
            return;
        String gamesWon=leaguePosition.getGamesWon()+"";
        String gamesLoss=leaguePosition.getGamesLoss()+"";
        String gamesDraw=leaguePosition.getGamesDraw()+"";
        String goalsScored=leaguePosition.getGoalsScored()+"";
        String goalsRec=leaguePosition.getGoalsReceive()+"";
        DBAccess.insertLeaguePositionToDB(leagueName,year,leaguePosition.getTeam().getTeamName(),
                gamesWon,gamesLoss,gamesDraw,goalsScored,goalsRec);
    }

    public void addMainRefereeToLeagueInDB(MainReferee ref,League league) {
        if(ref==null||league==null)
            return;
        DBAccess.insertMainRefereeToLeagueToDB(ref.getMember().getUser_name(),league.getName());
    }

    public void addLineRefereeToLeagueInDB(LinesManReferee ref,League league) {
        if(ref==null||league==null)
            return;
        DBAccess.insertLineRefereeToLeagueToDB(ref.getMember().getUser_name(),league.getName());
    }

    public void addVarRefereeToLeagueInDB(VarReferee ref,League league) {
        if(ref==null||league==null)
            return;
        DBAccess.insertVarRefereeToLeagueToDB(ref.getMember().getUser_name(),league.getName());
    }

    public void addFanToTeamInDB(Team team,Member observer){
        if(team==null||observer==null)
            return;
        DBAccess.insertFanToTeamToDB(team.getTeamName(),(observer).getUser_name());
    }

    public void addBudgetTransactionInDB(Team team, Pair<LocalDateTime,Pair<Double,String>> transaction) {
            String time=transaction.getKey().toString();
            String money=((Pair)transaction.getValue()).getKey()+"";
            String description=((Pair)transaction.getValue()).getValue()+"";
            String[] secValues={team.getTeamName(),time,money,description};
            DBAccess.insertBudgetTransactionToDB(team.getTeamName(),time,money,description);
    }

    public void addTeamOwnerToTeamInDB(TeamOwner owner, Team team) {
        String[] values={owner.getMember().getUser_name(),team.toString()};
        DBAccess.insertTeamOwnerToTeamToDB(owner.getMember().getUser_name(),team.getTeamName());
    }

    public void addAppointmentToOwnerInDB(TeamOwner owner,Job appointment){
        String[] secValues={owner.getMember().getUser_name(),appointment.getMember().getUser_name()};
        DBAccess.insertAppointmentToOwnerToDB(owner.getMember().getUser_name(),appointment.getMember().getUser_name());
    }

    public void addPlayerToTeamInDB(Player player, Team team) {
        String[] values={team.getTeamName(),player.getMember().getUser_name()};
        DBAccess.insertPlayerToTeamToDB(team.getTeamName(),player.getMember().getUser_name());
    }

    public void addCoachToTeamInDB(Coach coach, Team team) {
        String[] values={team.getTeamName(),coach.getMember().getUser_name()};
        DBAccess.insertCoachToTeamToDB(team.getTeamName(),coach.getMember().getUser_name());
    }

    public void addManagerToTeamInDB(TeamManager coach, Team team) {
        String[] values={team.getTeamName(),coach.getMember().getUser_name()};
        DBAccess.insertManagerToTeamToDB(team.getTeamName(),coach.getMember().getUser_name());
    }

    public void addPlayerTweetToDB(Player player,String tweet){
        String[] secValues={player.getMember().getUser_name(),tweet};
        DBAccess.insertPlayerTweetToDB(player.getMember().getUser_name(),tweet);
    }

    public void addPlayerObserverToDB(Player player,Observer observer){
        String[] secValues={player.getMember().getUser_name(),((Member)observer).getUser_name()+""};
        DBAccess.insertPlayerObserverToDB(player.getMember().getUser_name(),((Member)observer).getUser_name() +"");
    }

    public void addManagerPermissionToDB(TeamManager manager,TeamManager.Permissions permission){
        String[] secValues={manager.getMember().getUser_name(),permission.ordinal()+""};
        DBAccess.insertManagerPermissionToDB(manager.getMember().getUser_name(),permission.ordinal()+"");
    }

    private void addTeamToDB(Team team){
        String[] values={team.getTeamName(),team.getStatus().ordinal()+"",team.getHomeStadium().getStadiumName()};
        DBAccess.insertTeamToDB(team.getTeamName(),team.getStatus().ordinal()+"",team.getHomeStadium().getStadiumName());
    }

    private void addTeamManagerToDB(TeamManager manager){
        String[] values={manager.getMember().getUser_name(),manager.getJobName(),manager.getTeam().getTeamName()};
        DBAccess.insertTeamManagerToDB(manager.getMember().getUser_name(),manager.getJobName(),manager.getTeam().getTeamName());
    }

    private void addPlayerToDB(Player player){
        String[] values={player.getMember().getUser_name()+"",player.getPosition().ordinal()+"",player.getDateOfBirth().toString(),player.getTeam().getTeamName()};
        DBAccess.insertPlayerToDB(player.getMember().getUser_name()+"",player.getPosition().ordinal()+"",
                player.getDateOfBirth().toString(),player.getTeam().getTeamName());
    }

    private void addTicketToDB(Ticket ticket){
        String[] values={ticket.getTicketID(),ticket.getWrittenByID(),ticket.getAnswer(),ticket.getComplaint(),ticket.getIfAnswered()+""};
        DBAccess.insertTicketToDB(ticket.getTicketID(),ticket.getWrittenByID(),ticket.getAnswer()
                ,ticket.getComplaint(),ticket.getIfAnswered()+"");
    }

    private void addStadiumToDB(Stadium stadium){
        String[] values={stadium.getStadiumName(),stadium.getCity()};
        DBAccess.insertStadiumToDB(stadium.getStadiumName(),stadium.getCity());
    }

    private void addCoachToDB(Coach coach){
        String[] values={coach.getMember().getUser_name(),coach.getTeam().getTeamName(),coach.getCertification().ordinal()+"",coach.getJobInTheTeam()};
        DBAccess.insertCoachToDB(coach.getMember().getUser_name(),coach.getTeam().getTeamName(),
                coach.getCertification().ordinal()+"",coach.getJobInTheTeam());
    }

    public void addCoachTweetToDB(Coach coach,String tweet){
        String[] values={coach.getMember().getUser_name(),tweet};
        DBAccess.insertCoachTweetToDB(coach.getMember().getUser_name(),tweet);
    }

    private void addMemberToDB(Member member){
        String[] values={member.getUser_id(),member.getFull_name(),member.getUser_name(),member.getUser_password(),member.isBlocked()+""};
        DBAccess.insertMemberToDB(member.getUser_id(),member.getFull_name(),member.getUser_name()
                ,member.getUser_password(),member.isBlocked()+"");
    }

    public void addMemberTicketToDB(Member member,Ticket ticket){
        String[] secValues={member.getUser_name(),ticket.getTicketID()};
        DBAccess.insertMemberTicketToDB(member.getUser_name(),ticket.getTicketID());
    }

    public void addMemberSearchToDB(Member member,String search){
        String[] secValues={member.getUser_name(),search};
        DBAccess.insertMemberSearchToDB(member.getUser_name(),search);
    }

    public void addMemberTeamFollowed(Member member,Team team){
        String[] secValues={member.getUser_name(),team.getTeamName()};
        DBAccess.insertMemberTeamToDB(member.getUser_name(),team.getTeamName());
    }

    public void addMemberPlayerFollowed(Member member,Player player){
        String[] secValues={member.getUser_name(),player.getMember().getUser_name()};
        DBAccess.insertMemberPlayerFollowedToDB(member.getUser_name(),player.getMember().getUser_name());
    }

    public void addMemberCoachFollowed(Member member,Coach coach){
        String[] secValues={member.getUser_name(),coach.getMember().getUser_name()};
        DBAccess.insertMemberCoachFollowedToDB(member.getUser_name(),coach.getMember().getUser_name());
    }



    private void addMainRefereeToDB(MainReferee referee){
        String[] values={referee.getMember().getUser_name(),referee.isActiveStatus()+""};
        DBAccess.insertMainRefereeToDB(referee.getMember().getUser_name(),referee.isActiveStatus()+"");
    }

    private void addLineRefereeToDB(LinesManReferee referee){
        String[] values={referee.getMember().getUser_name(),referee.isActiveStatus()+""};
        DBAccess.insertLineRefereeToDB(referee.getMember().getUser_name(),referee.isActiveStatus()+"");
    }

    private void addVarRefereeToDB(VarReferee referee){
        String[] values={referee.getMember().getUser_name(),referee.isActiveStatus()+""};
        DBAccess.insertVarRefereeToDB(referee.getMember().getUser_name(),referee.isActiveStatus()+"");
    }

    private void addTeamOwnerToDB(TeamOwner owner){
        String[] values={owner.getMember().getUser_name(),owner.getTeam().getTeamName()};
        DBAccess.insertTeamOwnerToDB(owner.getMember().getUser_name(),owner.getTeam().getTeamName());
    }

    public void insert(Object o) {
        if(o instanceof League)
            addLeagueToDB((League)o);
        if(o instanceof Member)
            addMemberToDB((Member)o);
        if(o instanceof AssociationMember)
            addAssociationMemberToDB((AssociationMember) o);
        if(o instanceof Coach)
            addCoachToDB((Coach) o);
        if(o instanceof Team)
            addTeamToDB((Team) o);
        if(o instanceof TeamManager)
            addTeamManagerToDB((TeamManager) o);
        if(o instanceof TeamOwner)
            addTeamOwnerToDB((TeamOwner) o);
        if(o instanceof Player)
            addPlayerToDB((Player) o);
        if(o instanceof MainReferee)
            addMainRefereeToDB((MainReferee) o);
        if(o instanceof LinesManReferee)
            addLineRefereeToDB((LinesManReferee)o);
        if(o instanceof VarReferee)
            addVarRefereeToDB((VarReferee)o);
        if(o instanceof Ticket)
            addTicketToDB((Ticket) o);
        if(o instanceof Stadium)
            addStadiumToDB((Stadium) o);
    }


    public void addFoulEventToDB(FoulEvent event, FootballGame game) {
        String time=event.getEventGameTime().toString();
        String team=event.getEventTeam().getTeamName();
        String player=event.getEventPlayer().getMember().getUser_name();
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player};
        DBAccess.insertFoulEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,
                team,player);
    }


    public void addGoalEventToDB(GoalEvent event, FootballGame game) {
        String time=event.getEventGameTime().toString();
        String team=event.getEventTeam().getTeamName();
        String player=event.getEventPlayer().getMember().getUser_name();
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player};
        DBAccess.insertGoalEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player);
    }



    public void addInjuryEventToDB(InjuryEvent event, FootballGame game) {
        String time=event.getEventGameTime().toString();
        String team=event.getEventTeam().getTeamName();
        String player=event.getEventPlayer().getMember().getUser_name();
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player};
        DBAccess.insertInjuryEventToDB(game.getDate()+"",game.getHomeTeamName(),
                game.getAwayTeamName(),time,team,player);
    }

    public void addOffsideEventToDB(OffsideEvent event, FootballGame game) {
        String time=event.getEventGameTime().toString();
        String team=event.getEventTeam().getTeamName();
        String player=event.getEventPlayer().getMember().getUser_name();
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player};
        DBAccess.insertOffsideEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player);
    }

    public void addRedCardEventToDB(RedCardEvent event, FootballGame game) {
        String time=event.getEventGameTime().toString();
        String team=event.getEventTeam().getTeamName();
        String player=event.getEventPlayer().getMember().getUser_name();
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player};
        DBAccess.insertRedCardEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player);
    }

    public void addYellowCardEventToDB(YellowCardEvent event, FootballGame game) {
        String time=event.getEventGameTime().toString();
        String team=event.getEventTeam().getTeamName();
        String player=event.getEventPlayer().getMember().getUser_name();
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player};
        DBAccess.insertYellowCardEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player);
    }


    public void addStartGameEventToDB(StartGameEvent event, FootballGame game) {
        String time=event.getEventGameTime().toString();
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time};
        DBAccess.insertStartGameEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time);
    }

    public void addSubstituteEventToDB(SubstitutionEvent event, FootballGame game) {
        String time=event.getEventGameTime().toString();
        String ingoingID=event.getIngoingPlayer().getMember().getUser_name();
        String outgoingID=event.getEventPlayer().getMember().getUser_name();
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),event.getEventTeam().getTeamName(),outgoingID,ingoingID,time};
        DBAccess.insertSubstituteEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),
                event.getEventTeam().getTeamName(),outgoingID,ingoingID,time);
    }

    //this dont work in real!
    public void addGameDelayedEventToDB(GameDelayedEvent event, FootballGame game) {
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),event.getGameDelayedTime().toString(),event.getGameOriginalTime().toString()};
        DBAccess.insertGameDelayedEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),
                event.getGameDelayedTime().toString(),event.getGameOriginalTime().toString());
    }

    public void addGameRelocationEventToDB(GameReLocationEvent event, FootballGame game) {
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),event.getGameNewLocation().getStadiumName(),event.getGameOriginalLocation().getStadiumName()};
        DBAccess.insertGameRelocationEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),
                event.getGameNewLocation().getStadiumName(),event.getGameOriginalLocation().getStadiumName());
    }

    public void addGameEndEvent(EndGameEvent event,FootballGame game){
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName()};
        DBAccess.insertGameEndEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName());
    }

    public void updateLeagueScoringP(String leagueToChange, int pPerWin, int pPerLoss, int pPerDraw) {
        DBAccess.updateLeagueScoringP(leagueToChange,pPerWin+"",pPerLoss+"",pPerDraw+"");
    }

    public void updateLeagueSchedualingP(String leagueToChange, int numOfMatches) {
        DBAccess.updateLeagueSchedualingP(leagueToChange,numOfMatches+"");
    }

    public void updateSeasonScoringP(String leagueToChange, int year, int pPerWin, int pPerLoss, int pPerDraw) {
        DBAccess.updateSeasonScoringP(leagueToChange,year+"",pPerWin+"",pPerLoss+"",pPerDraw+"");
    }

    public void updateSeasonSchedualingP(String leagueToChange, int year, int numOfMatches) {
        DBAccess.updateSeasonSchedualingP(leagueToChange,year+"",numOfMatches+"");
    }

    public void updateLeaguePosition(int year,String leagueName,String teamName,int gamesWon,int gamesLoss,int gamesDraw,int goals,int recievedGoals){
        DBAccess.updateLeaguePosition(leagueName,year+"",teamName,gamesWon+"",gamesLoss+"",gamesDraw+"",goals+"",recievedGoals+"");
    }
}
/*

 */
