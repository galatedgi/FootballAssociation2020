package DBAccess;

import static org.junit.Assert.*;
import org.junit.Test;
import Domain.DBAccess.SQLServerDBAccess;
import org.junit.jupiter.api.DynamicTest;


public class SQLServerDBAccessTest {

    @Test
    public void getConnection() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.getConnection();
    }

    @Test
    public void getAllLeagues() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllLeagues();
        assertEquals(5,ret.length);
    }

    @Test
    public void getAllLeagueSeasons() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllLeagueSeasons("Bundes League");
        assertEquals(2,ret.length);
    }

    @Test
    public void getSeasonPositions() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getSeasonPositions("japanika","1999");
        assertEquals(4,ret.length);
    }

    @Test
    public void getFootballGames() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getFootballGames("japanika","1999");
        assertEquals(2,ret.length);
    }
    @Test
    public void getLeagueLinesReferees(){
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getLeagueLinesReferees("japanika");
        assertEquals(3,ret.length);
    }

    @Test
    public void getLeagueVarReferees(){
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getLeagueVarReferees("japanika");
        assertEquals(2,ret.length);
    }

    @Test
    public void getLeagueMainReferees(){
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getLeagueMainReferees("japanika");
        assertEquals(2,ret.length);
    }

    @Test
    public void getAllStadiums() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllStadiums();
        assertEquals(5,ret.length);
    }

    @Test
    public void getAllTeams() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllStadiums();
        assertEquals(5,ret.length);
    }

    @Test
    public void getTeamBudget() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getTeamBudget("hbs");
        assertEquals(3,ret.length);
    }

    @Test
    public void getTeamOwners() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getTeamOwners("hbs");
        assertEquals(3,ret.length);
        ret = sqlServerDBAccess.getTeamOwners("beitar");
        assertEquals(2,ret.length);
    }

    @Test
    public void getTeamPlayers() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getTeamPlayers("hbs");
        assertEquals(5,ret.length);
        ret = sqlServerDBAccess.getTeamPlayers("tel aviv");
        assertEquals(3,ret.length);
    }

    @Test
    public void getOwnersAppointments() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getOwnersAppointments("12");
        assertEquals(2,ret.length);
        ret = sqlServerDBAccess.getOwnersAppointments("9");
        assertEquals(1,ret.length);
    }

    @Test
    public void getTeamCoaches() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getTeamCoaches("hbs");
        assertEquals(2,ret.length);
        ret = sqlServerDBAccess.getTeamCoaches("tel aviv");
        assertEquals(1,ret.length);
    }

    @Test
    public void getTeamsFans() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getTeamsFans("hbs");
        assertEquals(3,ret.length);
    }

    @Test
    public void getTeamJobObservers() {
    }

    @Test
    public void getAllPlayers() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllPlayers();
        assertEquals(8,ret.length);
    }

    @Test
    public void getAllCoaches() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllCoaches();
        assertEquals(4,ret.length);
    }

    @Test
    public void getAllManagers() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllManagers();
        assertEquals(2,ret.length);
    }

    @Test
    public void getManagersPermissions() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getManagersPermissions("25");
        assertEquals(2,ret.length);
        ret = sqlServerDBAccess.getManagersPermissions("26");
        assertEquals(1,ret.length);
    }

    @Test
    public void getAllMainReferees() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllMainReferees();
        assertEquals(3,ret.length);
    }

    @Test
    public void getAllLinesReferees() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllLinesReferees();
        assertEquals(4,ret.length);
    }

    @Test
    public void getAllVarReferees() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllVarReferees();
        assertEquals(2,ret.length);
    }

    @Test
    public void getAllMembers() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllMembers();
        assertEquals(36,ret.length);
    }

    @Test
    public void getAllOwners() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllOwners();
        assertEquals(7,ret.length);
    }

    @Test
    public void getAllTickets() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllTickets();
        assertEquals(3,ret.length);
    }

    @Test
    public void getGamesFouls() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getGamesFouls("1990-12-12T11:30","hbs","tel aviv");
        assertEquals(1,ret.length);
    }

    @Test
    public void getGameGoals() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getGameGoals("1990-12-12T11:30","hbs","tel aviv");
        assertEquals(1,ret.length);
    }

    @Test
    public void getGameInjurys() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getGameInjurys("1990-12-12T11:30","hbs","tel aviv");
        assertEquals(1,ret.length);
    }

    @Test
    public void getGameoffsides() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getGameoffsides("1990-12-12T11:30","hbs","tel aviv");
        assertEquals(1,ret.length);
    }

    @Test
    public void getGameRedCardEvents() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getGameRedCardEvents("1990-12-12T11:30","hbs","tel aviv");
        assertEquals(1,ret.length);
    }

    @Test
    public void getGameYellowCardEvents() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getGameYellowCardEvents("1990-12-12T11:30","hbs","tel aviv");
        assertEquals(1,ret.length);
    }

    @Test
    public void getGameStartEvent() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getGameStartEvent("1990-12-12T11:30","hbs","tel aviv");
        assertEquals(1,ret.length);
    }

    @Test
    public void getGameSubstitutionEvent() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getGameSubstitutionEvent("1990-12-12T11:30","hbs","tel aviv");
        assertEquals(1,ret.length);
    }

    @Test
    public void getGamerelocationEvent() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getGamerelocationEvent("1990-12-12T11:30","hbs","tel aviv");
        assertEquals(1,ret.length);
    }

    @Test
    public void getGameEndEvent() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getGamerelocationEvent("1990-12-12T11:30","hbs","tel aviv");
        assertEquals(1,ret.length);
    }

    @Test
    public void insertLeagueToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertLeagueToDB("al",2,3,0,1);
        String[][] league = sqlServerDBAccess.getAllLeagues();
        boolean isFound = false;
        for(int i = 0; i< league.length;i++){
            if(league[i][0] == "al")
                isFound = true;
        }
        assertTrue(isFound);
    }

    @Test
    public void insertAssociationMemberToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertAssociationMemberToDB("12");
    }

    @Test
    public void insertSeasonToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertSeasonToDB("al",2008,2,3,0,1);
        String[][] season = sqlServerDBAccess.getASeason("2008");
        assertTrue(season.length > 0);
    }

    @Test
    public void insertGameToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertGameToDB("al","2008","maccabi","hapoel","2015-05-02 18:00",
                2,1,"terner","5","3","1","2");
    }

    @Test
    public void insertLeaguePositionToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertLeaguePositionToDB("al","2008","maccabi","20","6","6","80","50");
    }

    @Test
    public void insertMainRefereeToLeagueToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertMainRefereeToLeagueToDB("20","al");
    }

    @Test
    public void insertLineRefereeToLeagueToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertLineRefereeToLeagueToDB("19","al");
    }

    @Test
    public void insertVarRefereeToLeagueToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertLineRefereeToLeagueToDB("18","al");
    }

    @Test
    public void insertFanToTeamToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertFanToTeamToDB("maccabi","2");
    }

    @Test
    public void insertBudgetTransactionToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertBudgetTransactionToDB("maccabi","2015-05-02 18:00","20000","paycheck");
    }

    @Test
    public void insertTeamOwnerToTeamToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertTeamOwnerToTeamToDB("6","maccabi");
    }

    @Test
    public void insertAppointmentToOwnerToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertTeamOwnerToTeamToDB("6","maccabi");
    }

    @Test
    public void insertPlayerToTeamToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertPlayerToTeamToDB("maccabi","5");
    }

    @Test
    public void insertCoachToTeamToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertCoachToTeamToDB("maccabi","8");
    }

    @Test
    public void insertManagerToTeamToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertManagerToTeamToDB("maccabi","7");
    }

    @Test
    public void insertPlayerTweetToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertPlayerTweetToDB("5","wow");
    }

    @Test
    public void insertPlayerObserverToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertPlayerObserverToDB("5","15");
    }

    @Test
    public void insertManagerPermissionToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertManagerPermissionToDB("7","1");
    }

    @Test
    public void insertTeamToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertTeamToDB("maccabi","1","terner");
    }

    @Test
    public void insertTeamManagerToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertTeamManagerToDB("16","","maccabi");
    }

    @Test
    public void insertPlayerToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertPlayerToDB("5","3","2015-05-02 18:00","maccabi");
    }

    @Test
    public void insertTicketToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertTicketToDB("1","5","no","ddd","1");
    }

    @Test
    public void insertStadiumToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertStadiumToDB("terner","beersheba");
    }

    @Test
    public void insertCoachToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertCoachToDB("8","maccabi","2","blblb");
    }

    @Test
    public void insertCoachTweetToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertCoachTweetToDB("8","sub");
    }

    @Test
    public void insertMemberToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertMemberToDB("30","barak bahar","bh","12","0");
    }

    @Test
    public void insertMemberTicketToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertMemberTicketToDB("30","sub");
    }

    @Test
    public void insertMemberSearchToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertMemberSearchToDB("30","whatt");
    }

    @Test
    public void insertMemberTeamToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertMemberTeamToDB("30","macabbi");
    }

    @Test
    public void insertMemberPlayerFollowedToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertMemberPlayerFollowedToDB("30","5");
    }

    @Test
    public void insertMemberCoachFollowedToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertMemberCoachFollowedToDB("30","8");
    }

    @Test
    public void insertMainRefereeToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertMainRefereeToDB("20","1");
    }

    @Test
    public void insertLineRefereeToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertLineRefereeToDB("19","1");
    }

    @Test
    public void insertVarRefereeToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertVarRefereeToDB("18","1");
    }

    @Test
    public void insertTeamOwnerToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertTeamOwnerToDB("13","maccabi");
    }

    @Test
    public void insertFoulEventToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertFoulEventToDB("2015-05-02 18:00","maccabi","hapoel","16:15","maccabi","5");
    }

    @Test
    public void insertGoalEventToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertGoalEventToDB("2015-05-02 18:00","maccabi","hapoel","16:15","maccabi","5");
    }

    @Test
    public void insertInjuryEventToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertInjuryEventToDB("2015-05-02 18:00","maccabi","hapoel","16:15","maccabi","5");
    }

    @Test
    public void insertOffsideEventToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertOffsideEventToDB("2015-05-02 18:00","maccabi","hapoel","16:15","maccabi","5");
    }

    @Test
    public void insertRedCardEventToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertRedCardEventToDB("2015-05-02 18:00","maccabi","hapoel","16:15","maccabi","5");
    }

    @Test
    public void insertYellowCardEventToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertYellowCardEventToDB("2015-05-02 18:00","maccabi","hapoel","16:15","maccabi","5");
    }

    @Test
    public void insertSubstituteEventToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertSubstituteEventToDB("2015-05-02 18:00","maccabi","hapoel","maccabi","5","6","16:15");
    }

    @Test
    public void insertGameDelayedEventToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertGameDelayedEventToDB("2015-05-02 18:00","maccabi","hapoel","2015-05-02 20:00","2015-05-02 18:00");
    }

    @Test
    public void insertGameRelocationEventToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertGameRelocationEventToDB("2015-05-02 18:00","maccabi","hapoel","carmel","terner");
    }

    @Test
    public void insertGameEndEventToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertGameEndEventToDB("2015-05-02 20:10","maccabi","hapoel");
    }

    @Test
    public void insertGameStartEventToDB() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.insertGameStartEventToDB("2015-05-02 18:00","maccabi","hapoel");
    }
}
