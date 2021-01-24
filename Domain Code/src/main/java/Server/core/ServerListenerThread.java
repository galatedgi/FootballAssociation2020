package Server.core;

import Server.httpServer;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread{

    private int port;
    private String Webroot;
    private ServerSocket serverSocket;
//    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);


    public ServerListenerThread(int port,String webroot) throws IOException {
        this.port = port;
        this.Webroot = webroot;
        serverSocket = new ServerSocket(port,0, InetAddress.getLocalHost());
    }

    @Override
    public void run() {
        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println(" * Connection Established - " + socket.getInetAddress());
                HttpConnectionWorkerThread workerThread = new HttpConnectionWorkerThread(socket);
                workerThread.start();
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
