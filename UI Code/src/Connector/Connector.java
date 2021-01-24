package Connector;

import HttpProtocol.Message;
import HttpProtocol.Response;
import HttpProtocol.responseParser;

import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Connector {
    private int port;
    private Socket socket;
    private String Webroot;
    private static Connector con;
    private ServerSocket serverSocket;
    private responseParser Parser;
//    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);
    public static Permissions permissions;

    public Connector(){

    }

    public static Connector getInstance(){
        if (con == null) { con = new Connector();}
        return con;
    }

    public void sendtest() {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            final String CRLF = "\n\r";
            String Body = "" +
                    "{\n" +
                    "\"Add\" : \"Player\"\n"+
                    "\"end\" : \"true\"\n"+
                    "}";
            String response = "HTTP/1.1 200 OK" + CRLF + "Content-Length: " + Body.getBytes().length + CRLF + CRLF + Body + CRLF + CRLF;
            dOut.write(response.getBytes());
            dOut.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Response sendLoginAttempt(String Username, String Password) {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"user_name\" : \""+Username+"\"\n"+
                    "\"password\" : \""+Password+"\"\n"+
                    "\"port\" : \"7070\"\n"+
                    "\"ip\" : \""+InetAddress.getLocalHost().getHostAddress()+"\"\n";;
            String Path ="/System/Login";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response Response = Parser.parse(socket.getInputStream());
            System.out.println(Response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            if(Response.getResponse().equals("200 OK")){
                permissions = Permissions.getInstance();
            }
            return Response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendlogout() {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"user_name\" : \""+permissions.getUsername()+"\"\n";
            String Path ="/System/Logout";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Response GetTeams() {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Path ="/Association/getTeams";
            String OutMessage = new Message(Path).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response Response = Parser.parse(socket.getInputStream());
            System.out.println(Response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            return Response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response getPolicies() {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Path ="/Association/getPolicies";
            String OutMessage = new Message(Path).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response Response = Parser.parse(socket.getInputStream());
            System.out.println(Response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            return Response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response getRefGames() {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"refereeName\" : \""+Permissions.getInstance().getUsername()+"\"\n";
            String Path ="/Jobs/GetRefereeGames";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response Response = Parser.parse(socket.getInputStream());
            System.out.println(Response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            if(Response.getResponse().equals("200 OK")){
                permissions = Permissions.getInstance();
            }
            return Response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Response sendRegister(String Username, String Password,String ID,String FullName) {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"user_name\" : \""+Username+"\"\n"+
                    "\"password\" : \""+Password+"\"\n"+
                    "\"user_id\" : \""+ID+"\"\n"+
                    "\"full_name\" : \""+FullName+"\"\n";
            String Path ="/System/Register";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response Response = Parser.parse(socket.getInputStream());
            System.out.println(Response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            return Response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response AddnewTeam(String TeamName, String TeamOwner, String HomeStadium) {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"Username\" : \""+Permissions.getInstance().getUsername()+"\"\n"+
                    "\"TeamName\" : \""+TeamName+"\"\n"+
                    "\"ownerUserName\" : \""+TeamOwner+"\"\n"+
                    "\"stadiumName\" : \""+HomeStadium+"\"\n";
            String Path ="/Association/AddNewTeam";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response response = Parser.parse(socket.getInputStream());
            System.out.println(response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response AddnewGoalEvent(String index, String Time, String TeamName, String Player) {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"refereeName\" : \""+Permissions.getInstance().getUsername()+"\"\n"+
                    "\"index\" : \""+index+"\"\n"+
                    "\"time\" : \""+Time+"\"\n"+
                    "\"teamName\" : \""+TeamName+"\"\n"+
                    "\"playerName\" : \""+Player+"\"\n";
            String Path ="/Jobs/refereeAddGoalEvent";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response response = Parser.parse(socket.getInputStream());
            System.out.println(response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response AddnewInjuryEvent(String index, String Time, String TeamName, String Player) {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"refereeName\" : \""+Permissions.getInstance().getUsername()+"\"\n"+
                    "\"index\" : \""+index+"\"\n"+
                    "\"time\" : \""+Time+"\"\n"+
                    "\"teamName\" : \""+TeamName+"\"\n"+
                    "\"playerName\" : \""+Player+"\"\n";
            String Path ="/Jobs/refereeAddInjuryEvent";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response response = Parser.parse(socket.getInputStream());
            System.out.println(response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response AddnewOffsideEvent(String index, String Time, String TeamName, String Player) {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"refereeName\" : \""+Permissions.getInstance().getUsername()+"\"\n"+
                    "\"index\" : \""+index+"\"\n"+
                    "\"time\" : \""+Time+"\"\n"+
                    "\"teamName\" : \""+TeamName+"\"\n"+
                    "\"playerName\" : \""+Player+"\"\n";
            String Path ="/Jobs/refereeAddOffsideEvent";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response response = Parser.parse(socket.getInputStream());
            System.out.println(response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response AddnewRedCardEvent(String index, String Time, String TeamName, String Player) {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"refereeName\" : \""+Permissions.getInstance().getUsername()+"\"\n"+
                    "\"index\" : \""+index+"\"\n"+
                    "\"time\" : \""+Time+"\"\n"+
                    "\"teamName\" : \""+TeamName+"\"\n"+
                    "\"playerName\" : \""+Player+"\"\n";
            String Path ="/Jobs/refereeAddRedCardEvent";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response response = Parser.parse(socket.getInputStream());
            System.out.println(response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response AddnewYellowCardEvent(String index, String Time, String TeamName, String Player) {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"refereeName\" : \""+Permissions.getInstance().getUsername()+"\"\n"+
                    "\"index\" : \""+index+"\"\n"+
                    "\"time\" : \""+Time+"\"\n"+
                    "\"teamName\" : \""+TeamName+"\"\n"+
                    "\"playerName\" : \""+Player+"\"\n";
            String Path ="/Jobs/refereeAddYellowCardEvent";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response response = Parser.parse(socket.getInputStream());
            System.out.println(response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response AddnewSubEvent(String index, String Time, String TeamName, String ReplacedPlayer, String NewPlayer) {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"refereeName\" : \""+Permissions.getInstance().getUsername()+"\"\n"+
                            "\"index\" : \""+index+"\"\n"+
                            "\"time\" : \""+Time+"\"\n"+
                            "\"teamName\" : \""+TeamName+"\"\n"+
                            "\"playerInName\" : \""+NewPlayer+"\"\n"+
                            "\"playerOutName\" : \""+ReplacedPlayer+"\"\n";
            String Path ="/Jobs/refereeAddSubstitutionEvent";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response response = Parser.parse(socket.getInputStream());
            System.out.println(response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response AddnewFoulEvent(String index, String Time, String TeamName, String FoulPlayer, String FouledPlayer) {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"refereeName\" : \""+Permissions.getInstance().getUsername()+"\"\n"+
                    "\"index\" : \""+index+"\"\n"+
                    "\"time\" : \""+Time+"\"\n"+
                    "\"teamName\" : \""+TeamName+"\"\n"+
                    "\"playerName\" : \""+FoulPlayer+"\"\n"+
                    "\"fouledPlayerName\" : \""+FouledPlayer+"\"\n";
            String Path ="/Jobs/refereeAddFoulEvent";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response response = Parser.parse(socket.getInputStream());
            System.out.println(response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response ChangeScoringPolicy(String LeagueName, String W, String T, String L) {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"Username\" : \""+Permissions.getInstance().getUsername()+"\"\n"+
                    "\"LeagueToChange\" : \""+LeagueName+"\"\n"+
                    "\"pPerWin\" : \""+W+"\"\n"+
                    "\"pPerLoss\" : \""+L+"\"\n"+
                    "\"pPerDraw\" : \""+T+"\"\n";
            String Path ="/Association/ChangeScoringPolicyForLeague";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response response = Parser.parse(socket.getInputStream());
            System.out.println(response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response ChangeSchedulingPolicy(String LeagueName, String NumofGames) {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"Username\" : \""+Permissions.getInstance().getUsername()+"\"\n"+
                    "\"LeagueToChange\" : \""+LeagueName+"\"\n"+
                    "\"numOfMatches\" : \""+NumofGames+"\"\n";
            String Path ="/Association/ChangeSchedulingPolicyForLeague";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response response = Parser.parse(socket.getInputStream());
            System.out.println(response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response getPlayers(String TeamName) {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"teamName\" : \""+TeamName+"\"\n";
            String Path ="/Game/getTeamPlayers";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response response = Parser.parse(socket.getInputStream());
            System.out.println(response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response GetReport(int index) {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"refereeName\" : \""+Permissions.getInstance().getUsername()+"\"\n"+
                    "\"index\" : \""+index+"\"\n";
            String Path ="/Jobs/refereeCreateReport";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response response = Parser.parse(socket.getInputStream());
            System.out.println(response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void EndGame(int index) {
        try {
            Socket socket = new Socket("132.72.65.134",8080); // Create and connect the socket
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            String Body =
                    "\"refereeName\" : \""+Permissions.getInstance().getUsername()+"\"\n"+
                    "\"index\" : \""+index+"\"\n";
            String Path ="/Jobs/refereeAddEndGameEvent";
            String OutMessage = new Message(Path, Body).toString();
            System.out.println(OutMessage);
            System.out.println("-------------------------------------------------------------");
            dOut.write(OutMessage.getBytes());
            Parser = new responseParser();
            Response response = Parser.parse(socket.getInputStream());
            System.out.println(response);
            System.out.println("-------------------------------------------------------------");
            dOut.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
