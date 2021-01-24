package Connector;

import javafx.util.Pair;
import HttpProtocol.responseParser;
import java.sql.Ref;
import java.util.ArrayList;

public class Permissions {
    private String Username;
    private boolean Fan;
    private boolean TeamOwner;
    private boolean Coach;
    private boolean TeamManager;
    private boolean Player;
    private boolean Admin;
    private boolean Referee;
    private boolean Association;
    private static Permissions permissions;

    public static Permissions getInstance(){
        if(permissions==null)
            permissions = new Permissions();
        return permissions;
    }

    private Permissions(){
        Username = "";
        Fan = false;
        TeamManager = false;
        TeamOwner = false;
        Coach = false;
        Player = false;
        Admin = false;
        Referee = false;
        Association = false;
    }

    public void reset() {
        permissions = new Permissions();
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUsername() {
        return Username;
    }

    private Permissions(String Username, ArrayList<Pair<String,String>> Body){
        this.Username = Username;
//        Fan = responseParser.getElement("Fan",Body);
        TeamManager = false;
        TeamOwner = false;
        Coach = false;
        Player = false;
        Admin = false;
        Referee = false;
        Association = false;
    }

    public boolean isFan() {
        return Fan;
    }

    public void setFan(boolean fan) {
        Fan = fan;
    }

    public boolean isTeamOwner() {
        return TeamOwner;
    }

    public void setTeamOwner(boolean teamOwner) {
        TeamOwner = teamOwner;
    }

    public boolean isCoach() {
        return Coach;
    }

    public void setCoach(boolean coach) {
        Coach = coach;
    }

    public boolean isTeamManager() {
        return TeamManager;
    }

    public void setTeamManager(boolean teamManager) {
        TeamManager = teamManager;
    }

    public boolean isPlayer() {
        return Player;
    }

    public void setPlayer(boolean player) {
        Player = player;
    }

    public boolean isAdmin() {
        return Admin;
    }

    public void setAdmin(boolean admin) {
        Admin = admin;
    }

    public boolean isReferee() {
        return Referee;
    }

    public void setReferee(boolean referee) {
        Referee = referee;
    }

    public boolean isAssociation() {
        return Association;
    }

    public void setAssociation(boolean association) {
        Association = association;
    }
}
