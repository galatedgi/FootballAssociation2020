package http;

import java.lang.*;
import java.util.ArrayList;


public class Request {


    private ArrayList<Parser.StringPair> body;
    private String request;
    private String host;
    private int port;
    private String connection;
    private String content_Type;
    private int content_Length;
    private String path;
    private String protocol;

    public Request(String request, String path, String protocol, String host, int port, String connection, String content_Type, int content_Length, ArrayList<Parser.StringPair> body) {
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

    public  ArrayList<Parser.StringPair> getBody() {
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

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        String body="";
        for(Parser.StringPair p: getBody()){
            body+= p.getKey()+":"+p.getValue()+"\n";
        }
        return request +" "+path+" "+protocol+"\n"
                +"Host: "+ host +":" + port +"\n"
                +"Connection:"+ connection + "\n"
                +"Content-Type:"+ content_Type + "\n"
                +"Content-Length: "+content_Length+"\n"
                +"{\n" + body+"}";    }
}
