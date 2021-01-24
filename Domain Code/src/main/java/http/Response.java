package http;

import java.net.*;
import java.util.ArrayList;
import java.util.Date;

public class Response {
    private ArrayList<Parser.StringPair> body;
    private String response;
    private final String content_Type=" application/json";
    private final String protocol="HTTP/1.1";
    private final String server="prettyBoyz";


    public Response(int code){
        setResponse(code);
        body=new ArrayList<>();
    }

    public Response(String protocol, int code, String server, String content_type, int content_length, ArrayList<Parser.StringPair> body) {

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
        body.add(new Parser.StringPair(key,value));
    }

    public Date date(){
        Date date = new Date(System.currentTimeMillis());
        return date;
    }

    @Override
    public String toString() {
        String body = "{\n";
        for (Parser.StringPair p : this.body) {
            body += "\t"+"\"" + p.getKey()+"\"" + ":"+"\"" + p.getValue()+"\"" + "\n";
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
