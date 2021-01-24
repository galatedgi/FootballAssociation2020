package Server.core;

import Domain.Events.Event;
import Domain.User.Member;
import http.Request;
import http.Response;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class alertThread extends Thread{
    // private Socket socket;
    private Member member;
    private String host;
    private int port;

    public alertThread(String host,int port,Member member) {
        // this.socket = socket;
        this.member=member;
        this.host=host;
        this.port=port;
    }

    @Override
    public void run() {
        try {
            try {
                this.wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LinkedList events = (LinkedList) member.getEventListToSend();
            Response response=new Response(200);
            String value="";
            if(events.isEmpty()) {
                return;
            }
            Socket socket=new Socket(host,port);
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            while (!events.isEmpty()){
                value+=events.removeFirst().toString()+"$";
            }
            response.addToBody("event",value);
            dOut.write(response.toString().getBytes());
            System.out.println("------------------------------");
            System.out.println(response);
            System.out.println("------------------------------");
            dOut.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void start() {
        run();
    }

}
