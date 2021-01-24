package HttpProtocol;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Message {
    private String protocol;
    private String path;
    private ArrayList< Pair<String,String>> body;
    private String request;
    private String host;
    private int port;
    private String connection;
    private String content_Type;
    private int content_Length;


    public Message( String request,String path,String protocol, String host, int port, String connection, String content_Type,int content_Length,ArrayList<Pair<String,String>> body) {
        this.body = body;
        this.request = request;
        this.host = host;
        this.port = port;
        this.connection = connection;
        this.content_Type = content_Type;
        this.content_Length=content_Length;
        this.path=path;
        this.protocol=protocol;
    }

    public Message(String Path, String body) throws UnknownHostException {
        this.body = Parser.parseBody(body);
        this.protocol="HTTP/1.1";
        this.request = "POST";
        this.host = InetAddress.getLocalHost().getHostAddress();
        this.port = 8080;
        this.path=Path;
        this.connection = "keep-alive";
        this.content_Type = "application/json";
        this.content_Length= body.length()+4;
    }

    public Message(String Path) throws UnknownHostException {
        this.protocol="HTTP/1.1";
        this.request = "GET";
        this.host = InetAddress.getLocalHost().getHostAddress();
        this.port = 8080;
        this.path=Path;
        this.connection = "keep-alive";
    }

    public  ArrayList< Pair<String,String>> getBody() {
        return body;
    }

    public String getRequest() {
        return request;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getConnection() {
        return connection;
    }

    public String getContent_Type() {
        return content_Type;
    }

    @Override
    public String toString() {
        if (request.equals("POST")){
            String body="";
            for(Pair<String,String> p: getBody()){
                body+= "\t"+p.getKey()+":"+p.getValue()+"\n";
            }
            return request +" "+path+" "+protocol+"\n"
                    +"Host: "+ host +":" + port +"\n"
                    +"Connection:"+ connection + "\n"
                    +"Content-Type: "+ content_Type + "\n"
                    +"Content-Length: "+content_Length+"\n"
                    +"{\n" + body+"}";
        } else {
        return request +" "+path+" "+protocol+"\n"
                +"Host: "+ host +":" + port +"\n"
                +"Connection:"+ connection + "\n"+"\n";
                }
    }

}

