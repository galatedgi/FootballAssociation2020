package Service;

import Domain.System.*;
import Domain.User.Member;
import Exceptions.DomainException;
import Exceptions.notFoundException;
import Server.core.Logger;
import Server.core.alertThread;
import http.Parser;
import http.Response;

import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SystemController {
    private SystemResponses systemResponses;

    public SystemController() {
        systemResponses=new SystemResponses();
    }

    public void routing(String path, ArrayList<Parser.StringPair> body, Response response) throws notFoundException, DomainException {
        try {
            String input1, input2, input3, input4;
            switch (path) {
                case "Login":
                    input1 = Parser.getElement("user_name", body);
                    input2 = Parser.getElement("password", body);
                    input3=Parser.getElement("ip",body);
                    input4=Parser.getElement("port",body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    Member member=Login(input1, input2);
                    systemResponses.LoginResponse(member,response);
                    try {
                        member.setAlertThread(new alertThread(input3, Integer.parseInt(input4),member));
                        member.update();
                    }catch (Exception e){
                        System.out.println(e);
                    }
                    Logger.getLogger().writeToEventLogger(LocalDateTime.now(),member.getUser_name(),"login");
                    break;
                case "Register":
                    input1 = Parser.getElement("user_name", body);
                    input2 = Parser.getElement("password", body);
                    input3 = Parser.getElement("user_id", body);
                    input4 = Parser.getElement("full_name", body);
                    if (input1 == null || input2 == null || input3 == null || input4 == null)
                        throw new DomainException("invalid input");
                    Register(input1, input2, input3, input4);
                    Logger.getLogger().writeToEventLogger(LocalDateTime.now(),input1,"Register");
                    break;
                case "Logout":
                    input1 = Parser.getElement("user_name", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    Logout(input1);
                    Logger.getLogger().writeToEventLogger(LocalDateTime.now(),input1,"Logout");
                    break;
                default:
                    throw new notFoundException();

            }
        }catch (Exception e){
            if(e.getClass().equals(DomainException.class))
                throw (DomainException)e;
            if(e.getClass().equals(NumberFormatException.class))
                throw (NumberFormatException)e;
            throw new notFoundException();
        }

    }

    private Member Login(String user_name, String password) throws DomainException {
        return AlphaSystem.getSystem().Login(user_name,password);
    }

    private boolean Register(String user_name,String password,String user_id,String full_name) throws DomainException {
        return AlphaSystem.getSystem().Register(user_name, password, user_id,full_name);
    }

    private void Logout(String Username) throws DomainException {
        Member member = (Member)AlphaSystem.getSystem().GetSpecificFromMemory(2,Username);
        if(member==null){
            throw new DomainException("No such username exists");
        }
        AlphaSystem.getSystem().Logout(member);
    }

    //-----------------------------------------------------------------------------------------


}
