package HttpProtocol;

import javafx.util.Pair;

import java.net.*;
import java.util.ArrayList;
import java.util.Date;

public class Response {
    private ArrayList<Pair<String,String>> body;
    private String response;
    private String content_Type=" application/json";
    private String protocol="HTTP/1.1";
    private String server="prettyBoyz";
    private int ContentLength;

    public ArrayList<Pair<String, String>> getBody() {
        return body;
    }

    public String getResponse() {
        return response;
    }

    public String getContent_Type() {
        return content_Type;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getServer() {
        return server;
    }

    public int getContentLength() {
        return ContentLength;
    }

    public Response(int code){
        setResponse(code);
        body=new ArrayList<>();
    }

    public Response(ArrayList<Pair<String, String>> body, int response, String content_Type, String protocol, String server,int ContentLength) {
        this.body = body;
        setResponse(response);
        this.content_Type = content_Type;
        this.protocol = protocol;
        this.server = server;
        this.ContentLength = ContentLength;
    }

    public void setResponse(int code) {
        switch (code){
            case 200:
                response="200 OK";
                break;
            case 404:
                response="404 Not Found";
                break;
            case 400:
                response="400 Bad Request";
                break;
            case 500:
                response="500 Internal Server Error";
                break;
        }
    }

    public void addToBody(String key,String value){
        body.add(new Pair<String,String>(key,value));
    }

    public Date date(){
        Date date = new Date(System.currentTimeMillis());
        return date;
    }

    @Override
    public String toString() {
        String body = "{\n";
        for (Pair<String,String> p : this.body) {
            body += p.getKey()+":"+p.getValue()+"\n";
        }
        body += "}";
        String s = protocol + " " + response + "\n"
                + "Date:" + date() + "\n"
                + "Server:" + server + "\n"
                + "Content-Type:" + content_Type + "\n"
                + "Content-Length: " + body.length() + "\n" + "\n"
                + body;
        return s;
        //  return "HTTP/1.1 200 OK\nDate:"+ date()+"\nServer:test\nContent-Type: application/json\nContent-Length: 10\n\n"+body;
    }

}
