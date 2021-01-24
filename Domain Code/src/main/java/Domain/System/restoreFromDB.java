package Domain.System;

import Domain.Association.*;
import Domain.DBAccess.SQLServerDBAccess;
import Domain.Events.*;
import Domain.Game.*;
import Domain.Jobs.*;
import Domain.User.*;
import Exceptions.DomainException;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class restoreFromDB {
    SQLServerDBAccess database;
    AlphaSystem system=AlphaSystem.getSystem();

    public restoreFromDB(String connectionURL) {
        this.database = new SQLServerDBAccess(connectionURL);
        //"jdbc:sqlserver://localhost:1433;databaseName=Football;user=sa;password=Warning11"
    }

    public void restore(){
        restoreMembers();
        restoreAssociation();
        restoreOwners();
        restoreStadiums();
        restoreManagers();
        restoreCoaches();
        restorePlayers();
        restoreReferees();
        restoreTeams();
        restoreLeagues();
        restoreTickets();
    }


    private void restoreAssociation(){
        String[][] associationMembers =database.getAllAssociation();
        for (String[] associationValues:associationMembers){
            createAssociation(associationValues);
        }


    }
    private void restorePlayers(){
        String[][] players=database.getAllPlayers();
        for (String[] playerValues:players){
            createPlayer(playerValues);
        }
    }

    private void restoreCoaches(){
        String[][] coaches=database.getAllCoaches();
        for(String[] coachValues:coaches)
            createCoach(coachValues);
    }

    private void restoreManagers(){
        String[][] managers=database.getAllManagers();
        for (String[] managerValues:managers){
            String[][] permissions=database.getManagersPermissions(managerValues[0]);
            ArrayList<TeamManager.Permissions> perList=new ArrayList<>();
            for (String[] permission:permissions){
                perList.add(TeamManager.Permissions.values()[Integer.parseInt(permission[1])]);
            }
            createTeamManager(managerValues,perList);
        }
    }

    private void restoreOwners(){
        String[][] owners=database.getAllOwners();
        for (String[] ownerValues:owners)
            createTeamOwner(ownerValues);
    }

    private void restoreReferees(){
        String[][] referees=database.getAllMainReferees();
        for (String[] refereeValue:referees) {
            createMainReferee(refereeValue);
        }
        referees=database.getAllLinesReferees();
        for (String[] refereeValue:referees) {
            createLineReferee(refereeValue);
        }
        referees=database.getAllVarReferees();
        for (String[] refereeValue:referees) {
            createVarReferee(refereeValue);
        }
    }

    private void restoreMembers(){
        String[][] members=database.getAllMembers();
        for (String[] memberValue:members){
            createMember(memberValue);
        }
    }

    private void restoreTickets(){
        String[][] tickets=database.getAllTickets();
        for (String[] ticketValue:tickets)
            createTicket(ticketValue);
    }

    private void restoreTeams(){
        String[][] teams=database.getAllTeams();
        for (String[] teamValues:teams){
            Team currTeam= createTeam(teamValues);
            restoreTeamBudget(currTeam,database.getTeamBudget(currTeam.getTeamName()));
            restoreTeamOwners(currTeam);
            // need to add managers
            restoreTeamPlayers(currTeam);
            restoreTeamCoaches(currTeam);
            restoreTeamsFans(currTeam);
            restoreTeamsJobObservers(currTeam);//need to fix
        }
    }

    private void restoreTeamOwners(Team team){
        String[][] owners=database.getTeamOwners(team.getTeamName());
        for (String[] ownerValues:owners) {
            TeamOwner owner=(TeamOwner) system.GetSpecificFromMemory(6,ownerValues[1]);
            owner.setTeam(team);
            try {
                team.addOwner(owner);
            } catch (DomainException e) {

            }
        }
    }

    private void restoreFoulEvent(FootballGame game){
        String[][] foulEvents=database.getGamesFouls(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName());
        for (String[] values:foulEvents){
            Time dateTime=createTime(values[3]);
            Team team=(Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,values[4]);
            Player eventPlayer=(Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,values[5]);
            FoulEvent foulEvent=new FoulEvent(dateTime,team,eventPlayer);
            game.addEventFromDB(foulEvent);
        }
    }

    private void restoreGoalEvent(FootballGame game){
        String[][] goalsEvents=database.getGameGoals(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName());
        for (String[] values:goalsEvents){
            Time dateTime=createTime(values[3]);
            Team team=(Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,values[4]);
            Player eventPlayer=(Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,values[5]);
            GoalEvent goalEvent=new GoalEvent(dateTime,team,eventPlayer);
            game.addEventFromDB(goalEvent);
        }
    }

    private void restoreInjuryEvent(FootballGame game){
        String[][] injuryEvents=database.getGameInjurys(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName());
        for (String[] values:injuryEvents){
            Time dateTime=createTime(values[3]);
            Team team=(Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,values[4]);
            Player eventPlayer=(Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,values[5]);
            InjuryEvent injuryEvent=new InjuryEvent(dateTime,team,eventPlayer);
            game.addEventFromDB(injuryEvent);
        }
    }

    private void restoreOffsideEvent(FootballGame game){
        String[][] offsideEvents=database.getGameoffsides(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName());
        for (String[] values:offsideEvents){
            Time dateTime=createTime(values[3]);
            Team team=(Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,values[4]);
            Player eventPlayer=(Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,values[5]);
            OffsideEvent offsideEvent=new OffsideEvent(dateTime,team,eventPlayer);
            game.addEventFromDB(offsideEvent);
        }
    }

    private void restoreredCardEvent(FootballGame game){
        String[][] cardEvents=database.getGameRedCardEvents(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName());
        for (String[] values:cardEvents){
            Time dateTime=createTime(values[3]);
            Team team=(Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,values[4]);
            Player eventPlayer=(Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,values[5]);
            RedCardEvent cardEvent=new RedCardEvent(dateTime,team,eventPlayer);
            game.addEventFromDB(cardEvent);
        }
    }

    private void restoreYellowCardEvent(FootballGame game){
        String[][] cardEvents=database.getGameYellowCardEvents(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName());
        for (String[] values:cardEvents){
            Time dateTime=createTime(values[3]);
            Team team=(Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,values[4]);
            Player eventPlayer=(Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,values[5]);
            YellowCardEvent cardEvent=new YellowCardEvent(dateTime,team,eventPlayer);
            game.addEventFromDB(cardEvent);
        }
    }

    private void restoreStartGameEvent(FootballGame game){
        String[][] gameStartEvent=database.getGameStartEvent(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName());
        for (String[] values:gameStartEvent){
            LocalDateTime dateTime=createLocalDateTime(values[2]);
            Team home=(Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,game.getHomeTeamName());
            Team away=(Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,game.getHomeTeamName());
            StartGameEvent event=new StartGameEvent(dateTime,home,away);
            game.addEventFromDB(event);
        }
    }

    private void restoreSubtitutionEvent(FootballGame game){
        String[][] gameStartEvent=database.getGameSubstitutionEvent(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName());
        for (String[] values:gameStartEvent){
            Time dateTime=createTime(values[6]);
            Team team=(Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,values[4]);
            Player in=(Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,values[6]);
            Player out=(Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,values[5]);
            SubstitutionEvent event=new SubstitutionEvent(dateTime,team,in,out);
            game.addEventFromDB(event);
        }
    }

    public void restoreGameRelocationEvent(FootballGame game){
        String[][] gamerelocationEvent=database.getGamerelocationEvent(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName());
        for (String[] values:gamerelocationEvent){
            Stadium newStad=(Stadium)AlphaSystem.getSystem().GetSpecificFromMemory(11,values[3]);
            Stadium oldStad=(Stadium)AlphaSystem.getSystem().GetSpecificFromMemory(11,values[4]);
            Team home=(Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,game.getHomeTeamName());
            Team away=(Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,game.getAwayTeamName());
            GameReLocationEvent event=new GameReLocationEvent(oldStad,newStad,home,away);
            game.addEventFromDB(event);
        }
    }

    private void restoreGameEndEvent(FootballGame currGame) {
        String[][] gameEndEvent=database.getGameEndEvent(currGame.getDate()+"",currGame.getHomeTeamName(),currGame.getAwayTeamName());
        for (String[] values:gameEndEvent){
            Team home=(Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,currGame.getHomeTeamName());
            Team away=(Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,currGame.getAwayTeamName());
            EndGameEvent endGameEvent=new EndGameEvent(currGame.getDate(),home,away);
            currGame.addEventFromDB(endGameEvent);
        }
    }

    private void restoreTeamPlayers(Team team) {
        String[][] players=database.getTeamPlayers(team.getTeamName());
        for (String[] playerValues:players) {
            Player player=(Player)system.GetSpecificFromMemory(7,playerValues[1]);
            try {
                team.addNewPlayer(player.getMember().getUser_id());
            } catch (DomainException e) {
                e.printStackTrace();
            }
        }
    }

    private void restoreTeamCoaches(Team team){
        String[][] coaches=database.getTeamCoaches(team.getTeamName());
        for (String[] coachValues:coaches) {
            Coach coach=(Coach) system.GetSpecificFromMemory(3,coachValues[1]);
            coach.addToTeam(team,coach.getJobInTheTeam());
            try {
                team.addNewCoach(coach.getMember().getUser_id(),coach.getJobInTheTeam());
            } catch (DomainException e) {
                e.printStackTrace();
            }
        }
    }

    private void restoreTeamsFans(Team team){
        String[][] fans=database.getTeamsFans(team.getTeamName());
        for (String[] fanValue:fans){
            Member member=(Member)system.GetSpecificFromMemory(2,fanValue[1]);
            try {
                team.register(member);
            } catch (DomainException e) {
                e.printStackTrace();
            }
            member.followTeam(team);
        }
    }

    private void restoreTeamsJobObservers(Team team){
        String[][] wanted=database.getTeamJobObservers(team.getTeamName());
        /*for (String[] observerValues:wanted){
            Member member=(Member)system.GetSpecificFromMemory(2,observerValues[1]);
            //member.followTeam(team);//need fix
        }*/
    }


    private void restoreLeagues(){
        String[][] leagues=database.getAllLeagues();
        for (String[] leagueValues :leagues){
            League currLeage= createLeague(leagueValues);
            String[][] seasons=database.getAllLeagueSeasons(currLeage.getName());
            for (String[] seasonValues:seasons){
                Season currSeason= createSeason(seasonValues);
                try {
                    currLeage.addSeasonFromDB(currSeason);
                } catch (DomainException e) {
                    e.printStackTrace();
                }
                String[][] positions=database.getSeasonPositions(currLeage.getName(),currSeason.getYear()+"");
                LinkedList<LeaguePosition> table=new LinkedList<>();
                for (String[] position:positions) {
                    LeaguePosition currLeaguePosition= createLeaguePosition(position);
                    table.add(currLeaguePosition);
                }
                currSeason.setTable(table);
                String[][] games=database.getFootballGames(currLeage.getName(),currSeason.getYear()+"");
                for (String[] game:games){
                    FootballGame currGame= createFootballGame(game);
                    restorGameEvents(currGame);
                    currSeason.addGame(currGame,currLeage.getName());
                }
            }
        }
    }

    private void restorGameEvents(FootballGame currGame) {
        restoreFoulEvent(currGame);
        restoreGoalEvent(currGame);
        restoreInjuryEvent(currGame);
        restoreGameRelocationEvent(currGame);
        restoreOffsideEvent(currGame);
        restoreredCardEvent(currGame);
        restoreYellowCardEvent(currGame);
        restoreStartGameEvent(currGame);
        restoreSubtitutionEvent(currGame);
        restoreGameEndEvent(currGame);
    }

    private void restoreStadiums(){
        String[][] stadiums=database.getAllStadiums();
        for (String[] stadiumValues:stadiums){
            createStadium(stadiumValues);
        }
    }

    private void restoreTeamBudget(Team team,String[][] transactions){
        Budget budget=new Budget(team);
        for(String[] transaction:transactions){
            double cash=Double.parseDouble(transaction[2]);
            if (cash>0)
                budget.addDeposit(cash,transaction[3],createLocalDateTime(transaction[1]));
            else budget.addWithdraw(cash,transaction[3],createLocalDateTime(transaction[1]));
        }
        team.setBudget(budget);
    }


    //need fix!!!!!!!!!!!!!!!!!!!!!!!!
    private Ticket createTicket(String[] values) {
        //String[] values={ticket.getTicketID(),ticket.getWrittenByID(),ticket.getAnswer(),ticket.getComplaint(),ticket.getIfAnswered()+""};
        Member writtenBy=(Member)system.GetSpecificFromMemory(2,values[1]);
        Ticket ticket=new Ticket(writtenBy,values[3]);
        //ticket.
        return null;
    }

    private League createLeague(String[] values){
        SchedulingPolicy schedulingPolicy=new SchedulingPolicy(Integer.parseInt(values[1]));
        ScoringPolicy scoringPolicy=new ScoringPolicy((int)Double.parseDouble(values[2]),(int)Double.parseDouble(values[4]),(int)Double.parseDouble(values[3]));
        League league=new League(values[0],schedulingPolicy,scoringPolicy);
        return league;
    }


    private Season createSeason(String[] values){
        SchedulingPolicy schedulingPolicy=new SchedulingPolicy(Integer.parseInt(values[2]));
        ScoringPolicy scoringPolicy=new ScoringPolicy((int)Double.parseDouble(values[3]),(int)Double.parseDouble(values[5]),(int)Double.parseDouble(values[4]));
        Season season=new Season(Integer.parseInt(values[0]),schedulingPolicy,scoringPolicy);
        return season;
    }

    private Stadium createStadium(String[] values){
        Stadium stadium=new Stadium(values[0],values[1]);
        return stadium;
    }

    private Team createTeam(String[] values){
        Stadium stadium =(Stadium) system.GetSpecificFromMemory(11,values[2]);
        Team team=new Team(values[0],null,stadium);
        try {
            if(Team.Status.values()[Integer.parseInt(values[1])]== Team.Status.close)
                team.setStatus(Team.Status.close);
        } catch (DomainException e) {
            e.printStackTrace();
        }
        return team;
    }

    private Member createMember(String[] values){
        //String[] values={member.getUser_id(),member.getFull_name(),member.getUser_name(),member.getUser_password(),member.isBlocked()+""};
       // Member member=new Member(values[2],values[3],values[0],values[1]);
        //member.setBlocked(Boolean.parseBoolean(values[4]));
        Register register=new Register();
        register.registerToSystem(values[1], values[2], values[0], values[3]);
        Member member=(Member) AlphaSystem.getSystem().GetSpecificFromMemory(2,values[0]);
        return member;
    }

    private Coach createCoach(String[] values){
        Member member=(Member) system.GetSpecificFromMemory(2,values[0]);
        Coach coach=new Coach(member,Coach.Certification.values()[Integer.parseInt(values[1])]);
        coach.setJobInTheTeam(values[3]);
        return coach;
    }

    private Player createPlayer(String[] values){
        Member member=(Member) system.GetSpecificFromMemory(2,values[0]);
        Player player=new Player(member,Player.Position.values()[Integer.parseInt(values[1])],createLocalDate(values[3]));
        return player;
    }

    private AssociationMember createAssociation(String[] values){
        Member member=(Member) system.GetSpecificFromMemory(2,values[0]);
        system.RemoveMember(member);
        AssociationMember associationMember=new AssociationMember(member);
        system.AddtoMemory(2,associationMember);
        return associationMember;

    }

    private MainReferee createMainReferee(String values[]){
        Member member=(Member) system.GetSpecificFromMemory(2,values[0]);
        MainReferee mainReferee=new MainReferee(member);
        member.addJob(mainReferee);
        boolean active=Boolean.parseBoolean(values[1]);
        if(!active)
            mainReferee.DelistAsRef();
        return mainReferee;
    }

    private LinesManReferee createLineReferee(String values[]){
        Member member=(Member) system.GetSpecificFromMemory(2,values[0]);
        LinesManReferee linesManReferee=new LinesManReferee(member);
        member.addJob(linesManReferee);
        boolean active=Boolean.parseBoolean(values[1]);
        if(!active)
            linesManReferee.DelistAsRef();
        return linesManReferee;
    }

    private VarReferee createVarReferee(String values[]){
        Member member=(Member) system.GetSpecificFromMemory(2,values[0]);
        VarReferee varReferee=new VarReferee(member);
        member.addJob(varReferee);
        boolean active=Boolean.parseBoolean(values[1]);
        if(!active)
            varReferee.DelistAsRef();
        return varReferee;
    }

    private TeamManager createTeamManager(String values[], ArrayList<TeamManager.Permissions> permissions){
        Member member=(Member) system.GetSpecificFromMemory(2,values[0]);
        TeamManager manager=new TeamManager(member,null,permissions);
        return manager;
    }

    private TeamOwner createTeamOwner(String[] values){
        Member member=(Member) system.GetSpecificFromMemory(2,values[0]);
        TeamOwner owner=new TeamOwner(member);
        return owner;
    }

    private LeaguePosition createLeaguePosition(String[] values){
        Team team=(Team)system.GetSpecificFromMemory(4 ,values[2]);
        LeaguePosition leaguePosition=new LeaguePosition(team,Integer.parseInt(values[3]),Integer.parseInt(values[4]),Integer.parseInt(values[5]),Integer.parseInt(values[6]),Integer.parseInt(values[7]));
        return leaguePosition;
    }

    private FootballGame createFootballGame(String[] values){
        League league=(League)system.GetSpecificFromMemory(1,values[10]);
        Season season=league.getSpecSeason(Integer.parseInt(values[9]));
        Team home=(Team) system.GetSpecificFromMemory(4,values[0]);
        Team away=(Team) system.GetSpecificFromMemory(4,values[1]);
        FootballGame game=new FootballGame(season,home,away,createLocalDateTime(values[2]));
        MainReferee mainReferee=(MainReferee)system.GetSpecificFromMemory(9,values[3]);
        mainReferee.AddGameToRef(game);
        VarReferee varReferee=(VarReferee)system.GetSpecificFromMemory(9,values[6]);
        LinesManReferee linesManReferee1=(LinesManReferee)system.GetSpecificFromMemory(9,values[4]);
        LinesManReferee linesManReferee2=(LinesManReferee)system.GetSpecificFromMemory(9,values[5]);
        game.setMainReferee(mainReferee);
        game.setLinesManLeft(linesManReferee1);
        game.setLinesManRight(linesManReferee2);
        game.setVarReferee(varReferee);
        try{
            int goals=Integer.parseInt(values[7]);
            for (int i = 0; i < goals; i++) {
                game.homeScoreGoal();
            }
            goals=Integer.parseInt(values[8]);
            for (int i = 0; i < goals; i++) {
                game.awayScoreGoal();
            }
        }catch (Exception e){

        }
        Stadium stadium=(Stadium)system.GetSpecificFromMemory(11,values[11]);
        game.setStadium(stadium);
        return game;
    }

    private LocalDateTime createLocalDateTime(String value) {
        String delimiters = "[-T:]";
        String[] c=value.split(delimiters);
        try{
            return LocalDateTime.of(Integer.parseInt(c[0]),Integer.parseInt(c[1]),Integer.parseInt(c[2]),Integer.parseInt(c[3]),Integer.parseInt(c[4]));
        }catch (Exception e){
            return LocalDateTime.of(1993,2,2,0,0);
        }
    }

    private Time createTime(String value) {
//        String delimiters = "[-T:]";
//        String[] c=value.split(delimiters);
        try{
            return Time.valueOf(value);
        }catch (Exception e){
            return  Time.valueOf("0:0:0");
        }
    }

    private LocalDate createLocalDate(String value) {
        String[] c=value.split("-");
        try{
            LocalDate date=LocalDate.of(Integer.parseInt(c[0]),Integer.parseInt(c[1]),Integer.parseInt(c[2]));
            return date;
        }catch (Exception e){
            return LocalDate.of(1993,2,2);
        }
    }
}
