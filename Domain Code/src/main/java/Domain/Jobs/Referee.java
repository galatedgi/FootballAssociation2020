package Domain.Jobs;
import Domain.Events.*;
import Domain.User.*;
import Domain.System.*;
import Domain.Game.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Referee extends Job{
    List<FootballGame> GamesToRef;
    List<Season> ActiveSeasons;
    boolean ActiveStatus;
    Member member;
    boolean Var;
    boolean Line;
    boolean Main;



    public Referee(Member member) {
        super(member);
        this.member = member;
        this.jobName="referee";
        GamesToRef = new ArrayList<FootballGame>();
        ActiveSeasons = new ArrayList<Season>();
        ActiveStatus = true;
        AlphaSystem system = AlphaSystem.getSystem();
        system.AddtoMemory(9, this);
        Var = false;
        Line = false;
        Main = false;
    }


    public void editFullName(String newName){
        getMember().setFull_name(newName);
    }

    public void editTraining(String training){
        SetTraining(training);
    }

    private void SetTraining(String training){//1.Main  2.Var  3.Line
            switch (training) {
                case "1":
                    if(Main)
                        Main = false;
                    else
                        Main = true;
                    break;
                case "2":
                    if(Var)
                        Var = false;
                    else
                        Var = true;
                    break;
                case "3":
                    if(Line)
                        Line = false;
                    else
                        Line = true;
                    break;
                default:
                    System.out.println("invalid action");
                    break;
        }
    }

    public boolean CanMainRef(){
        return Main;
    }

    public boolean CanVarRef(){
        return Var;
    }

    public boolean CanLineRef(){
        return Line;
    }

    public List<FootballGame> GetGames(){
        return GamesToRef;
    }

    public void DelistAsRef(){
        ActiveStatus = false;
        //Seasons.removeRef(this);
    }


    public void AddGameToRef(FootballGame ToRef){
        GamesToRef.add(ToRef);
    }

    public void AddSeasonToRef(Season SeasonToRef){
        ActiveSeasons.add(SeasonToRef);
    }

    //כרגע לא עובד
    private void AddEvent(FootballGame Game, GameEvent ChosenEvent){
        //Game.AddEvent();
    }

    private void EditEvents(FootballGame Game, GameEvent ChosenEvent){
        //if game ended in less than 5 hours
        //Game.RemoveEvent();
        //Game.AddEvent();
    }

    public boolean isActiveStatus() {
        return ActiveStatus;
    }

    public void addGoalEvent(int index, Time time, Team team, Player player){
        FootballGame game=GamesToRef.get(index);
        GoalEvent event=game.addGoalEvent(time,team,player);
        AlphaSystem.getSystem().getDB().addGoalEventToDB(event,game);
    }

    public void addFoulEvent(int index,Time time, Team team, Player player){
        FootballGame game=GamesToRef.get(index);
        FoulEvent event=game.addFoulEvent(time,team,player);
        AlphaSystem.getSystem().getDB().addFoulEventToDB(event,game);
    }

    public void addInjuryEvent(int index,Time time,Team team,Player player){
        FootballGame game=GamesToRef.get(index);
        InjuryEvent event=game.addInjuryEvent(time,team,player);
        AlphaSystem.getSystem().getDB().addInjuryEventToDB(event,game);
    }

    public void addOffsideEvent(int index,Time time,Team team,Player player){
        FootballGame game=GamesToRef.get(index);
        OffsideEvent event=game.addOffsideEvent(time,team,player);
        AlphaSystem.getSystem().getDB().addOffsideEventToDB(event,game);
    }

    public void addRedCardEvent(int index,Time time,Team team,Player player){
        FootballGame game=GamesToRef.get(index);
        RedCardEvent event=game.addRedCardEvent(time,team,player);
        AlphaSystem.getSystem().getDB().addRedCardEventToDB(event,game);
    }

    public void addYellowCardEvent(int index,Time time,Team team,Player player){
        FootballGame game=GamesToRef.get(index);
        YellowCardEvent event=game.addYellowCardEvent(time,team,player);
        AlphaSystem.getSystem().getDB().addYellowCardEventToDB(event,game);
    }

    public void addSubstitutionEvent(int index,Time time, Team team, Player in, Player out){
        FootballGame game=GamesToRef.get(index);
        SubstitutionEvent event=game.addSubstitutionEvent(time,team,in,out);
        AlphaSystem.getSystem().getDB().addSubstituteEventToDB(event,game);
    }

    public String crateReport(int index){
        List<Event> gameEvents=GamesToRef.get(index).getEvents();
        String report="";
        for(Event e:gameEvents)
            report+=e.toString()+"$";
        return report;
    }

    public void addEndGameEvent(int index){
        FootballGame game=GamesToRef.get(index);
        EndGameEvent event=game.addEndGameEvent(LocalDateTime.now());
        AlphaSystem.getSystem().getDB().addGameEndEvent(event,game);
    }
}
