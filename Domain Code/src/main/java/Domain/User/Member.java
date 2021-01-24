package Domain.User;//roei cohen
import Domain.Events.*;
import Domain.System.Observer;
import Domain.Jobs.*;
import Domain.Game.*;
import Domain.System.*;
import Server.core.alertThread;


import java.util.*;

public class Member extends User implements Observer {

    private String user_name;
    private String user_password;
    private String user_id;
    private String full_name;
    private HashMap<String,Job> jobs;
    private List<Event> eventList;
    private List<Event> eventListToSend;
    private boolean online;
    private boolean blocked;
    private List<Ticket> ticketList;
    private List<String> searchHistory;
    private List<FootballGame> footballGamesFollowed;
    private List<Team> teamsFollowed;
    private List<Player> playersFollowed;
    private List<Coach> coachesFollowed;
    private Server.core.alertThread alertThread;

    public Member(String user_name,String user_password,String user_id,String full_name){
        super();
        this.user_name=user_name;
        this.user_password=user_password;
        this.user_id=user_id;
        this.full_name=full_name;
        jobs = new HashMap<>();
        eventList = new ArrayList<>();
        ticketList = new ArrayList<>();
        searchHistory = new ArrayList<>();
        footballGamesFollowed = new ArrayList<>();
        teamsFollowed = new ArrayList<>();
        playersFollowed = new ArrayList<>();
        coachesFollowed = new ArrayList<>();
        online=false;
        blocked = false;
        this.eventListToSend=new LinkedList<>();
    }

    public String getUser_password(){
        return user_password;
    }


    public void watchSearchHistory(){
        if(searchHistory.size()==0)
            System.out.println("No searches have been performed yet.");
        for(String search:searchHistory){
            System.out.println(search);
        }
    }

    public Member(Member member){
        this.user_name=member.user_name;
        this.user_id = member.user_id;
        this.user_password = member.user_password;
        this.full_name = member.full_name;
        this.jobs=member.jobs;
        this.eventList = member.eventList;
        this.online = member.online;
        this.blocked = member.blocked;
        this.ticketList = member.ticketList;
    }

    public void setFull_name(String full_name){
        this.full_name=full_name;
    }

    public void setBlocked(boolean blocked){
        this.blocked=blocked;
    }

    public void editPersonalInformation(String information_to_change,int type){//1-username,2-password,3-userid,4-fullname
        if(information_to_change.isEmpty())
            return;
        switch (type){
            case 1:
                this.user_name=information_to_change;
                break;
            case 2:
                this.user_password=information_to_change;
                break;
            case 3:
                this.user_id=information_to_change;
                break;
            case 4:
                this.full_name=information_to_change;
                break;
        }
    }

    public String getUser_id(){
        return user_id;
    }

    public String getFull_name(){
        return full_name;
    }

    public void setOnline(boolean online){
        this.online=online;
    }

    public HashMap<String,Job> getJobsList(){
        return jobs;
    }

    public Job getJob(String job_name){
        if(jobs.containsKey(job_name))
            return jobs.get(job_name);
        return null;
    }

    public boolean addJob(Job job){
        if(this.jobs.containsKey("referee")||job instanceof Referee){
            if(jobs.size()>0)
                return false;
        }
        if(!jobs.containsKey(job.getJobName())) {
            jobs.put(job.getJobName(), job);
            return true;
        }
        return false;
    }

    public void logout(){
        online=false;
        close();
    }

    public boolean removeJob(String job_name){
        if(jobs.containsKey(job_name)) {
            jobs.remove(job_name);
            return true;
        }
        return false;
    }

    public String getUser_name() {
        return user_name;
    }

    public void update() {
        alertThread.start();
    }

    @Override
    public void update(Event newEvent) {
        this.eventList.add(newEvent);
        this.eventListToSend.add(newEvent);
        if(online)
            update();
        System.out.println(newEvent.toString());
    }

    public String[] showMemberInformation(){
        String[] ret = new String[]{user_name,user_password,user_id,full_name};
        return ret;
    }

    public void newComplaint(String complaint){
        Ticket ticket = new Ticket(this,complaint);
        AlphaSystem.getSystem().AddtoMemory(10,ticket);
        this.ticketList.add(ticket);
        AlphaSystem.getSystem().getDB().addMemberTicketToDB(this,ticket);
    }

    public List<Ticket> getTicketList(){
        return ticketList;
    }

    public boolean addReferee(Job referee){
        if(this.jobs.size()>0)
            return false;
        addJob(referee);
        return true;
    }


    public boolean followFootballGame(String league_name,String home_team,String away_team){
        League chosenLeague = (League) AlphaSystem.getSystem().GetSpecificFromMemory(1,league_name);
        if(chosenLeague!=null) {
            Season currSeason = chosenLeague.getCurrentSeason();
            List<FootballGame> footballGames = currSeason.getGames();
            boolean found_game = false;
            for (FootballGame footballGame : footballGames) {
                if (footballGame.getHomeTeamName().equals(home_team) && footballGame.getAwayTeamName().equals(away_team)) {
                    footballGame.register(this);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean followPersonalPage(int type,String object_name) throws Exception {
        switch (type){
            case 1:
                Team team = (Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,object_name);
                if(team==null)
                    return false;
                team.register(this);
                return true;
            case 2:
                Player player = (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,object_name);
                if(player==null)
                    return false;
                player.register(this);
                return true;
            case 3:
                Coach coach = (Coach) AlphaSystem.getSystem().GetSpecificFromMemory(3,object_name);
                if(coach==null)
                    return false;
                coach.register(this);
                return true;
        }
        return false;
    }

    public List<Object> memberSearchByName(String name,boolean leagueBool,boolean coachBool,boolean teamBool,boolean manBool,boolean ownerBool,boolean playerBool,boolean refBool,boolean stadBool){
        searchHistory.add(name);
        AlphaSystem.getSystem().getDB().addMemberSearchToDB(this,name);
        return searchByName( name, leagueBool, coachBool, teamBool, manBool, ownerBool, playerBool, refBool, stadBool);
    }

    public List<Object> memberSearchByCategory(String category){
        int choice=0;
        if(category.equals("League"))
            choice=1;
        if(category.equals("Coach"))
            choice=3;
        if(category.equals("Team"))
            choice=4;
        if(category.equals("manager"))
            choice=5;
        if(category.equals("owner"))
            choice=6;
        if(category.equals("player"))
            choice=7;
        if(category.equals("referee"))
            choice=9;
        if(category.equals("stadium"))
            choice=11;
        searchHistory.add(category);
        return SearchByCategory(choice);
    }

    public void followTeam(Team team) {
        teamsFollowed.add(team);
        AlphaSystem.getSystem().getDB().addMemberTeamFollowed(this,team);
    }

    public boolean isBlocked() {
        return  blocked;
    }

    public List<String> getSearchHistory() {
        return searchHistory;
    }

    public List<Team> getTeamsFollowed() {
        return teamsFollowed;
    }

    public List<Player> getPlayersFollowed() {
        return playersFollowed;
    }

    public List<Coach> getCoachesFollowed() {
        return coachesFollowed;
    }

    public void setAlertThread(Server.core.alertThread alertThread) {
        this.alertThread = alertThread;
    }

    public List<Event> getEventListToSend() {
        return eventListToSend;
    }

    public void close(){
        alertThread=null;
    }
}
