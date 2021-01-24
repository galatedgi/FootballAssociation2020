package Connector;

import HttpProtocol.Response;
import HttpProtocol.responseParser;
import sample.Controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EventListener extends Thread  {
    private int port;
    private ServerSocket serverSocket;


    public EventListener(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port,0, InetAddress.getLocalHost());
    }

    @Override
    public void run() {
        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                responseParser parser=new responseParser();
                Response response = parser.parse(socket.getInputStream());
                System.out.println(response);
                String msg=responseParser.getElement("event",response.getBody());
                Controller.AlertofEvent2(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem Setting Socket");
        } finally {
            if (serverSocket != null)
                try{
                    serverSocket.close();
                } catch (IOException e){}
        }
    }
}
