package Server;

import Domain.Association.AssociationMember;
import Domain.Game.*;
import Domain.Jobs.MainReferee;
import Domain.Jobs.Player;
import Domain.Jobs.Referee;
import Domain.Jobs.TeamOwner;
import Domain.System.AlphaSystem;
import Domain.User.Member;
import Domain.System.*;
import Server.Config.Configuration;
import Server.Config.ConfigurationManager;
import Server.core.ServerListenerThread;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDate;

public class httpServer {
//    private final static Logger LOGGER = LoggerFactory.getLogger(httpServer.class);

    public static void main(String[] args) throws IOException {
        try {/*
            AssociationMember associationMember=new AssociationMember("amit","123","456","amit");
            Stadium stadium=new Stadium("ter","beer sheve");
            AlphaSystem.getSystem().Register("alona","123","1","alona");
            AlphaSystem.getSystem().Register("zino","123","2","zino");
            AlphaSystem.getSystem().Register("meir","123","3","meir");
            AlphaSystem.getSystem().Register("gal","123","4","gal atedgi");
            AlphaSystem.getSystem().Register("elia","123","5","elia barda");
            Member alona= (Member) AlphaSystem.getSystem().GetSpecificFromMemory(2,"alona");
            Member zino= (Member) AlphaSystem.getSystem().GetSpecificFromMemory(2,"zino");
            Member meir= (Member) AlphaSystem.getSystem().GetSpecificFromMemory(2,"meir");
            Member gal= (Member) AlphaSystem.getSystem().GetSpecificFromMemory(2,"gal");
            Member elia= (Member) AlphaSystem.getSystem().GetSpecificFromMemory(2,"elia");
            Player player=new Player(gal,Player.Position.ST,null);
            Player player2=new Player(elia,Player.Position.ST,null);
            TeamOwner teamOwner1=new TeamOwner(alona);
            associationMember.AddNewRef(meir);
            TeamOwner teamOwner2=new TeamOwner(zino);
            Team team2=new Team("macabi beer sheve",teamOwner2,stadium);
            Team team1=new Team("beer",teamOwner1,stadium);
            team1.register(associationMember);
            teamOwner1.addNewPlayer("gal");
          //  teamOwner1.addNewPlayer("elia");
            League league1=new League("l1",new SchedulingPolicy(),new ScoringPolicy());
            league1.addSeason(2020,new SchedulingPolicy(),new ScoringPolicy());
            associationMember.AddRefToLeague((Referee) meir.getJob("referee"),league1);
            associationMember.AddTeamToSeasonInLeague("l1",2020,team1);
            associationMember.AddTeamToSeasonInLeague("l1",2020,team2);
            league1.getSpecSeason(2020).scheduleGames("l1");
            league1.getSpecSeason(2020).scheduleMainReferees(league1.getLeagueReferees());
*/

            restoreFromDB restoreFromDB = new restoreFromDB("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
            restoreFromDB.restore();
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Server Starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrConfiguration();
        System.out.println("Using Port:"+conf.getPort());
        System.out.println("Using Webroot:"+conf.getWebroot());
        ServerListenerThread serverListenerThread = new ServerListenerThread(8080, InetAddress.getLocalHost().getHostAddress());
        serverListenerThread.start();
    }
}
