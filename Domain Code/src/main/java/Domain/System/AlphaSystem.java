package Domain.System;
import Domain.Events.*;
import Domain.User.*;

import java.util.ArrayList;
import java.util.List;

public class AlphaSystem {
    private static AlphaMemory memory;
    private static AlphaSystem system;
    private List<SystemAdmin> Admins;
    private Login LoginSys;
    private Register RegisterSys;
    private List<Member> LoggedInMembers;
    private String DBConnectionURL;
    private InsertToDB DB;



    private  AlphaSystem(){
        memory = new AlphaMemory();
        LoginSys = new Login();
        RegisterSys = new Register();
        LoggedInMembers = new ArrayList<Member>();
        Admins = new ArrayList<SystemAdmin>();
        DBConnectionURL="jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11";
        DB=new InsertToDB(DBConnectionURL);
    }

    public static AlphaSystem  getSystem(){
        if(system==null) {
            system = new AlphaSystem();
        }
        return system;
    }

    public InsertToDB getDB(){return DB;}
    public void ResetMemory(){
        memory.Reset();
    }

    public void AddAdmin(SystemAdmin Admin){
        Admins.add(Admin);
    }

    public List<SystemAdmin> getAdmin(){
        return Admins;
    }

//
//    public static void EnterSystem() {
//        //Login LogIn = new Login();
//        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//        System.out.println("Enter username");
//        String userName = myObj.nextLine();  // Read user input
//        System.out.println("Enter Password");
//        String Password = myObj.nextLine();  // Read user input
//        //int usertype = LogIn.login(userName,Password);
//        int usertype = 1;
//        System.out.println("Welcome " + userName+"!");
//        switch (usertype){
//            case 0:{
//                System.out.println("You are currently logged in as a guest, please register to use the system's features.");
//                break;
//            }
//            case 1:{
//                System.out.println("Main Menu: sataaaasdaa");
//                System.out.println("");
//                break;
//            }
//            case 2:{
//                System.out.println("You are currently logged in as a guest, please register to use the system's features.");
//                break;
//            }
//            case 3:{
//                System.out.println("You are currently logged in as a guest, please register to use the system's features.");
//                break;
//            }
//        }
//    }





    public void Logout(Member member){
        LoggedInMembers.remove(member);
        member.logout();
    }

    public  Object GetSpecificFromMemory(int Type, String Name)
    {
        return memory.Getspecific(Type,Name);
    }

    public  Object GetAllFromMemory(int Type)
    {
        return memory.GetAll(Type);
    }

    public void AddtoMemory(int Type, Object ToAdd)
    {
        memory.AddtoMeamory(Type,ToAdd);
    }

    public  void RemoveMember(Member member)
    {
        memory.RemoveMember(member);
    }

    public Ticket GetNextUnansweredTicket() {
        return memory.GetNextUnansweredTicket();
    }

    public EventLog getLog() {
        return memory.GetLog();
    }

    public Member Login(String user_name, String password){
        Member Loggedin = LoginSys.loginToSystem(user_name, password);
        if (Loggedin != null){
            LoggedInMembers.add(Loggedin);
            return Loggedin;
        }
        return null;
    }

    public boolean Register(String user_name,String password,String user_id,String full_name){
        return RegisterSys.registerToSystem(user_name, password, user_id,full_name );
    }


}
