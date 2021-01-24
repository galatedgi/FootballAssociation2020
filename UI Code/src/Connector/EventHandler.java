package Connector;

import HttpProtocol.Response;
import HttpProtocol.responseParser;
import javafx.application.Platform;
import sample.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EventHandler extends Thread  {
    private Socket socket;
    private responseParser Parser;
    private static String Events;

    public static String getEvents() {
        return Events;
    }

    public EventHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            System.out.println("starting");
            Parser = new responseParser();
            Response response = Parser.parse(inputStream);
            Events += "\n"+responseParser.getElement("event",response.getBody());
            Controller.NewEvent();
        } catch (IOException e){
            e.printStackTrace();
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
