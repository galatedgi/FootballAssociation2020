package Domain.Jobs;
import Domain.User.*;
import Domain.System.*;
import Domain.Game.*;
import Exceptions.DomainException;

import java.util.ArrayList;
import java.util.List;

public class TeamOwner extends Job{
    private Team team;
    private List<Job> appointmentList;

    public TeamOwner(Member member) {
        super(member);
        this.team = null;
        appointmentList =new ArrayList<>();
        this.jobName="owner";
        AlphaSystem alphaSystem= AlphaSystem.getSystem();
        alphaSystem.AddtoMemory(6,this);
    }

    public void addOwner(String userName) throws DomainException {
        AlphaSystem alphaSystem= AlphaSystem.getSystem();
        Member member= (Member) alphaSystem.GetSpecificFromMemory(2,userName);
        if(checker(member))
            return;
        if(member.getJob("owner")!=null){
            throw new DomainException("this member already a owner");
        }
        TeamOwner newOwner=new TeamOwner(member);
        if(team.addOwner(newOwner)) {
            member.addJob(newOwner);
            appointmentList.add(newOwner);
            //System.out.println("the member " + userName + " add to " + team.getTeamName() + " team owners");
            AlphaSystem.getSystem().getDB().addAppointmentToOwnerInDB(this,newOwner);
        }
    }

    public void removeOwner(String userName) throws DomainException {
        AlphaSystem alphaSystem= AlphaSystem.getSystem();
        Member member= (Member) alphaSystem.GetSpecificFromMemory(2,userName);
        if(checker(member))
            return;
        TeamOwner teamOwner=(TeamOwner)member.getJob("owner");
        if(teamOwner==null){
            throw new DomainException("this member is not a owner");
        }
        if(!appointmentList.contains(teamOwner)){
            throw new DomainException("you cant' remove this owner");
        }
        if(team.removeOwner(teamOwner)) {
            appointmentList.remove(teamOwner);
            System.out.println("the member " + userName + " remove from " + team.getTeamName() + " team owners");
        }
    }

    public void openTeam() throws DomainException {
        team.setStatus(Team.Status.open);
    }

    public void closeTeam() throws DomainException {
        team.setStatus(Team.Status.close);
    }

    public void addManager(String userName,boolean[] permissionsList) throws DomainException {
        AlphaSystem alphaSystem= AlphaSystem.getSystem();
        Member member= (Member) alphaSystem.GetSpecificFromMemory(2,userName);
        if(checker(member))
            return;
        if(member.getJob("manager")!=null){
            throw new DomainException("this member already a manager");
        }
        if(member.getJob("owner")!=null){
            throw new DomainException("this member already a owner");
        }
        ArrayList<TeamManager.Permissions> permissions=choosePermissions(permissionsList);
        TeamManager teamManager=new TeamManager(member,team,permissions);
        if(team.addManager(teamManager)) {
            member.addJob(teamManager);
            appointmentList.add(teamManager);
            //System.out.println("the member " + userName + " add to " + team.getTeamName() + " team managers");
            AlphaSystem.getSystem().getDB().addAppointmentToOwnerInDB(this,teamManager);
            for (TeamManager.Permissions per:permissions) {
                AlphaSystem.getSystem().getDB().addManagerPermissionToDB(teamManager,per);
            }
        }
    }

    public void removeManger(String userName) throws DomainException {
        AlphaSystem alphaSystem= AlphaSystem.getSystem();
        Member member= (Member) alphaSystem.GetSpecificFromMemory(2,userName);
        if(checker(member))
            return;
        TeamManager teamManager=(TeamManager) member.getJob("manager");
        if(teamManager==null){
            throw new DomainException("this member is not a manager");
        }
        if(!appointmentList.contains(teamManager)){
            throw new DomainException("you cant' remove this manager");
        }
        if(team.removeManager(teamManager)) {
            appointmentList.remove(teamManager);
            System.out.println("the member " + userName + " remove from " + team.getTeamName() + " team managers");
        }
    }

    private boolean checker(Member member) throws DomainException {
        if(member==null){
            throw new DomainException("the user name not exist");
        }
        return false;
    }

    private ArrayList<TeamManager.Permissions> choosePermissions(boolean[] permissionsList){
        ArrayList<TeamManager.Permissions> permissions=new ArrayList<>();
        if(permissionsList[0])
            permissions.add(TeamManager.Permissions.SET_TEAM_STATUS);
        if(permissionsList[1])
            permissions.add(TeamManager.Permissions.EDIT_PROPERTIES);
        if(permissionsList[2])
            permissions.add(TeamManager.Permissions.ADD_FINANCIAL_REPORT);
        if(permissionsList[3])
            permissions.add(TeamManager.Permissions.EDIT_PERSONAL_PAGE);
        return permissions;
    }

    public List<Job> getAppointmentList() {
        return appointmentList;
    }

    public void addWithdraw(Double sum,String description) throws DomainException {
        team.addWithdraw(sum,description);

    }

    public void addDeposit(Double sum,String description) throws DomainException {
        team.addDeposit(sum,description);
    }

    public void editFullName(String newName){
        getMember().setFull_name(newName);
    }

    public void addTweet(String tweet) throws DomainException {
      team.addTweet(tweet);
    }

    public void deleteTweet(int index) throws DomainException {
       team.deleteTweet(index);
    }

    public boolean setPermissionsToManager(String manager,boolean[] permissionsList) throws DomainException {
       return team.setPermissionsToManager(manager,choosePermissions(permissionsList));
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public boolean addNewPlayer(String userName) throws DomainException {
        return team.addNewPlayer(userName);
    }

    public boolean removeExistingPlayer(String userName) throws DomainException {
        return team.removeExistingPlayer(userName);
    }

    public boolean editExistingPlayerName(String userName,String name) throws DomainException {
        return team.editExistingPlayerName( userName, name);
    }

    public boolean editExistingPlayerPosition(String userName,String position) throws DomainException {
        return team.editExistingPlayerPosition( userName, position);
    }

    public boolean editExistingPlayerBirthday(String userName,int year,int month,int day) throws DomainException {
        return team.editExistingPlayerBirthday( userName, year, month, day);
    }

    public boolean addNewCoach(String userName,String job) throws DomainException {
        return team.addNewCoach( userName, job);
    }

    public boolean removeExistingCoach(String userName) throws DomainException {
        return team.removeExistingCoach( userName);
    }

    public boolean editExistingCoachName(String user,String newName) throws DomainException {
        return team.editExistingCoachName( user, newName);
    }

    public boolean editExistingCoachCertification(String user,int certificationId) throws DomainException {
        return team.editExistingCoachCertification( user, certificationId);
    }

    public boolean editExistingCoachJobInTeam(String user,String Job) throws DomainException {
        return team.editExistingCoachJobInTeam( user, Job);
    }

    public boolean editExistingManagerName(String user,String newName) throws DomainException {
        return team.editExistingManagerName( user, newName);
    }

    public boolean editExistingStadiumName(String newName) throws DomainException {
        return team.editExistingStadiumName( newName);
    }

    public boolean setNewStadium(String stadiumName) throws DomainException {
        return team.setNewStadium( stadiumName);
    }



}
