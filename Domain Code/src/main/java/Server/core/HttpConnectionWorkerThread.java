package Server.core;

import Exceptions.DomainException;
import Service.MainController;
import Exceptions.notFoundException;
import http.Request;
import http.Parser;
import http.Response;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class HttpConnectionWorkerThread extends Thread {
    private Socket socket;


    public HttpConnectionWorkerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        MainController controller=MainController.getInstance();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            System.out.println("starting");
            Parser prs = new Parser();
            try {
                Request Msg = prs.parse(inputStream);
                System.out.println(Msg);
                System.out.println("---------------------------------------------------------------");
                Response response = new Response(200);
                controller.routing(Msg.getPath(),Msg.getBody(),response);
                System.out.println(response);
               outputStream.write(response.toString().getBytes());
            } catch (notFoundException e){
                Response response = new Response(404);
                response.addToBody("error",e.toString());
                outputStream.write(response.toString().getBytes());
                Logger.getLogger().writeToErrorLogger(LocalDateTime.now(),e.toString());
            }catch (DomainException e){
                Response response = new Response(400);
                response.addToBody("error",e.getMessage());
                outputStream.write(response.toString().getBytes());
                Logger.getLogger().writeToErrorLogger(LocalDateTime.now(),e.toString());
            }catch (NumberFormatException e) {
                Response response = new Response(400);
                response.addToBody("error", "please insert a valid input number");
                outputStream.write(response.toString().getBytes());
                Logger.getLogger().writeToErrorLogger(LocalDateTime.now(),e.toString());
            }catch (Exception e) {
                Response response = new Response(500);
                response.addToBody("error", "500 Internal Server Error");
                outputStream.write(response.toString().getBytes());
                Logger.getLogger().writeToErrorLogger(LocalDateTime.now(),e.toString());
            }
            System.out.println("Connection processing finished.");
        } catch (IOException e){
            e.printStackTrace();
            Logger.getLogger().writeToErrorLogger(LocalDateTime.now(),e.toString());
        } finally {
            if(inputStream!=null){
                try{
                    inputStream.close();
                } catch (IOException e){}
            }
            if(outputStream!=null){
                try{
                    outputStream.close();
                } catch (IOException e){}
            }
            if(socket!=null){
                try{
                    socket.close();
                } catch (IOException e){}
            }

        }

    }
}
