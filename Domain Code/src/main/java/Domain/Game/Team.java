package Domain.Game;
import  Domain.Events.*;
import  Domain.User.*;
import  Domain.System.*;
import  Domain.Jobs.*;
import  Domain.Association.*;
import Exceptions.DomainException;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Team implements Subject {

    public enum Status{open,close}
    private String teamName;
    private List<TeamOwner> owners;
    private  List<Player> players;
    private List<Coach> coaches;
    private List<TeamManager> managers;
    private Status status;
    private Stadium homeStadium;
    private List<Observer> fanObservers;
    private List<Observer> jobsObservers;
    private List<String> tweets;
    private Budget budget;

    public Team(String teamName, TeamOwner owner, Stadium homeStadium) {
        this.teamName=teamName;
        owners=new ArrayList<>();
        players=new ArrayList<>();
        coaches=new ArrayList<>();
        managers=new ArrayList<>();
        this.status=Status.open;
        this.homeStadium=homeStadium;
        fanObservers=new ArrayList<>();
        jobsObservers=new ArrayList<>();
        tweets=new ArrayList<>();
        budget=new Budget(this);
        /*owner.setTeam(this);
        owners.add(owner);
        jobsObservers.add(owner.getMember());*/
        AlphaSystem alphaSystem= AlphaSystem.getSystem();
        alphaSystem.AddtoMemory(4,this);
    }

    public List<Observer> getJobsObservers() {
        return jobsObservers;
    }

    public String getTeamName() {
        return teamName;
    }

    public boolean setTeamName(String teamName) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");
        }
        this.teamName = teamName;
        return true;
    }

    public List<TeamOwner> getOwners() {
        return owners;
    }

    public Budget getBudget() {
        return budget;
    }

    public List<String> getTweets() {
        return tweets;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public List<TeamManager> getManagers() {
        return managers;
    }

    public boolean addOwner(TeamOwner teamOwner) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");
        }
        teamOwner.setTeam(this);
        jobsObservers.add(teamOwner.getMember());
        owners.add(teamOwner);
        notifyObserver(new NewNominationEvent(this,teamOwner.getMember(),"Team owner"));
        try {
            AlphaSystem.getSystem().getDB().addTeamOwnerToTeamInDB(teamOwner,this);
        }catch (Exception e){
        }
        return true;
    }

    public boolean removeOwner(TeamOwner teamOwner) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");
        }
        if (owners.size() == 1) {
            throw new DomainException("you cant' remove the owner");

        }
        Member member=teamOwner.getMember();
        removeAllAppointment(teamOwner);
        member.removeJob("owner");
        owners.remove(teamOwner);
        jobsObservers.remove(member);
        RemoveNominationEvent event=new RemoveNominationEvent(this,member,"Team owner");
        member.update(event);
        notifyObserver(event);
        return true;
    }

    public boolean addManager(TeamManager teamManager) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");
        }
        jobsObservers.add(teamManager.getMember());
        managers.add(teamManager);
        notifyObserver(new NewNominationEvent(this,teamManager.getMember(),"Team manager"));
        AlphaSystem.getSystem().getDB().addManagerToTeamInDB(teamManager,this);
        return true;
    }

    public boolean removeManager(TeamManager teamManager) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");

        }
        Member member=teamManager.getMember();
        member.removeJob("manager");
        managers.remove(teamManager);
        jobsObservers.remove(teamManager);
        RemoveNominationEvent event=new RemoveNominationEvent(this,member,"Team manager");
        member.update(event);
        notifyObserver(event);
        return true;
    }

    public Status getStatus() {
        return status;
    }

    public boolean setStatus(Status status) throws DomainException {
        if(status == this.status){
            throw new DomainException("the team already "+this.status);
        }
        this.status = status;
        if(this.status== Status.open) {
            notifyObserver(new TeamReOpenEvent(LocalDateTime.now(),this));
        }
        else if(this.status==Status.close){
            removeAllTeamPermissions();
            notifyObserver(new TeamCloseEvent(LocalDateTime.now(),this));
        }
        return true;
    }

    public Stadium getHomeStadium() {

        return homeStadium;
    }

    public boolean setHomeStadium(Stadium homeStadium) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");

        }
        this.homeStadium = homeStadium;
        return true;
    }

    public List<Observer> getFanObservers(){
        return fanObservers;
    }

    private void removeAllTeamPermissions(){
        for(TeamManager teamManager:managers)
            teamManager.removeAllPermissions();
    }

    public boolean addTweet(String tweet) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");
        }
        tweets.add(tweet);
        notifyObserver(new TewwtEvent(tweet));
        return true;
    }

    public boolean deleteTweet(int index) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");
        }
        if(index>=tweets.size())
            return false;
        tweets.remove(index);
        return true;
    }

    @Override
    public void register(Observer newObserver) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");
        }
        fanObservers.add(newObserver);
        AlphaSystem.getSystem().getDB().addFanToTeamInDB(this,(Member) newObserver);
    }

    @Override
    public void unregister(Observer deleteObserver) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");
        }
        int observerIndex = fanObservers.indexOf(deleteObserver);
        fanObservers.remove(observerIndex);
    }

    @Override
    public void notifyObserver(Event newEvent) {
        if(newEvent instanceof TeamReOpenEvent){
            for (Observer observer:jobsObservers) {
                observer.update(newEvent);
            }
        }
        if(newEvent instanceof TeamCloseEvent){
            for (Observer observer:jobsObservers) {
                observer.update(newEvent);
            }
        }
        for (Observer observer:fanObservers) {
            observer.update(newEvent);
        }
    }

    public boolean registerSystemAdmin(SystemAdmin systemAdmin) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");
        }
        jobsObservers.add(systemAdmin);
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Team))
            return false;
        Team secTeam=(Team)obj;
        if(this.getTeamName().equals(secTeam.getTeamName()))
            return true;
        return false;
    }

    private void removeAllAppointment(TeamOwner teamOwner) throws DomainException {
        ArrayList<String> owners=new ArrayList<>();
        ArrayList<String> managers=new ArrayList<>();
        for(Job job : teamOwner.getAppointmentList()){
            if(job instanceof TeamOwner)
                owners.add(job.getMemberUserName());
            else if(job instanceof TeamManager)
                managers.add(job.getMemberUserName());
        }
        for(String userName:owners)
            teamOwner.removeOwner(userName);
        for(String userName:managers)
            teamOwner.removeOwner(userName);
    }

    public void setBudget(Budget budget) {
        this.budget=budget;
    }

    public boolean addWithdraw(Double sum,String description) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");
        }
        budget.addWithdraw(sum,description);
        return true;
    }

    public boolean addDeposit(Double sum,String description) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");

        }
        budget.addDeposit(sum,description);
        return true;
    }

    private Player getPlayer(String userName){
        for(Player player:players) {
            if (player.getMemberUserName().equals(userName))
                return player;
        }
        return null;
    }

    private Coach getCoach(String userName){
        for(Coach coach:coaches) {
            if (coach.getMemberUserName().equals(userName))
                return coach;
        }
        return null;
    }

    private TeamManager getManager(String userName){
        for(TeamManager manager:managers) {
            if (manager.getMemberUserName().equals(userName))
                return manager;
        }
        return null;
    }

    public boolean addNewPlayer(String userName) throws DomainException { //searching by name and not by username!
        if(status==Status.close){
            throw new DomainException("the team is close");

        }
        AlphaSystem alphaSystem= AlphaSystem.getSystem();
        Player player = (Player) alphaSystem.GetSpecificFromMemory(7, userName);
        if(player==null){
            return false;
        }
        if(player.addToTeam(this)) {
            players.add(player);
            notifyObserver(new AddPlayerToTeamEvent(player,this));
            AlphaSystem.getSystem().getDB().addPlayerToTeamInDB(player,this);
            return true;
        }else return false;
    }

    public boolean removeExistingPlayer(String userName) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");

        }
        Player player = getPlayer(userName);
        if(player==null){
            return false;
        }
        if(player.removeFromTeam()){
            players.remove(player);
            notifyObserver(new RemovePlayerFromTeamEvent(player,this));
            return true;
        }
        return false;
    }

    public boolean editExistingPlayerName(String userName,String name) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");

        }
        Player player = getPlayer(userName);
        if(player==null)
            return false;
        player.editFullName(name);
        return true;
    }

    public boolean editExistingPlayerPosition(String userName,String position) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");

        }
        Player player = getPlayer(userName);
        if(player==null)
            return false;
        if(player.editPosition(position))
            return true;
        return false;
    }

    public boolean editExistingPlayerBirthday(String userName,int year,int month,int day) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");
        }
        Player player = getPlayer(userName);
        if(player==null)
            return false;
        player.editBirthDay(year,month,day);
        return true;
    }

    public boolean addNewCoach(String userName,String job) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");

        }
        AlphaSystem alphaSystem= AlphaSystem.getSystem();
        Coach coach = (Coach) alphaSystem.GetSpecificFromMemory(3, userName);
        if(coach==null){
            return false;
        }
        if(coach.addToTeam(this,job)) {
            coaches.add(coach);
            notifyObserver(new AddCoachToTeamEvent(coach,this));
            AlphaSystem.getSystem().getDB().addCoachToTeamInDB(coach,this);
            return true;
        }else return false;
    }

    public boolean removeExistingCoach(String userName) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");
        }
        Coach coach= getCoach(userName);
        if(coach==null){
            return false;
        }
        if(coach.removeFromTeam()){
            coaches.remove(coach);
            notifyObserver(new RemoveCoachFromTeamEvent(coach,this));
            return true;
        }
        return false;
    }

    public boolean editExistingCoachName(String user,String newName) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");

        }
        Coach coach=getCoach(user);
        if(coach==null)
            return false;
        coach.editFullName(newName);
        return true;
    }

    public boolean editExistingCoachCertification(String user,int certificationId) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");

        }
        Coach coach=getCoach(user);
        if(coach==null)
            return false;
        if(coach.setCertification(certificationId))
            return true;
        return false;
    }

    public boolean editExistingCoachJobInTeam(String user,String Job) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");
        }
        Coach coach=getCoach(user);
        if(coach==null)
            return false;
        coach.setJobInTheTeam(Job);
        return true;
    }

    public boolean editExistingManagerName(String user,String newName) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");

        }
        TeamManager manager = getManager(user);
        if(manager==null){
            return false;
        }
        manager.editFullName(newName);
        return true;
    }

    public boolean editExistingStadiumName(String newName) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");

        }
        homeStadium.setStadiumName(newName);
        return true;
    }

    public boolean setNewStadium(String stadiumName) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");

        }
        AlphaSystem alphaSystem= AlphaSystem.getSystem();
        Stadium stadium= (Stadium) alphaSystem.GetSpecificFromMemory(11,stadiumName);
        if(stadium==null)
            return false;
        setHomeStadium(stadium);
        return true;
    }

    public boolean setPermissionsToManager(String manager, ArrayList<TeamManager.Permissions> permissionsList) throws DomainException {
        if(status==Status.close){
            throw new DomainException("the team is close");
        }
        TeamManager teamManager=getManager(manager);
        teamManager.setPermissions(permissionsList);
        return true;
    }

}




