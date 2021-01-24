package HttpProtocol;

import javafx.util.Pair;
import java.io.*;
import java.util.ArrayList;

public class Parser {
    public Parser() {
    }


    public Message parse(InputStream inputStream) throws IOException {
        ArrayList<Pair<String,String>> body = new ArrayList<>();
        String request = null;
        String path=null;
        String protocol=null;
        String host = null;
        int port = 0;
        String connection = null;
        String content_Type = null;
        int content_Length=0;
        boolean json = false;
        boolean end=false;
        InputStreamReader isr = new InputStreamReader(inputStream,"UTF-8");
        BufferedReader br = new BufferedReader(isr);

        try {
            String line = br.readLine();
            String[] split=line.split(" ");
            request = split[0];
            path=split[1];
            protocol=split[2];
            while (line != null) {
                line = br.readLine();
                if (line.contains("Host:")) {
                    line = line.substring(line.indexOf(':') + 1);
                    host = line.substring(0, line.indexOf(':'));
                    port = Integer.parseInt(line.substring(line.indexOf(':') + 1));
                    continue;
                }
                if (line.contains("Connection:")) {
                    connection = line.substring(line.indexOf(':') + 1);
                    continue;
                }
                if (line.contains("Content-Type:")) {
                    content_Type = line.substring(line.indexOf(':') + 1);
                    if (content_Type.equals(" application/json"))
                        json = true;
                    continue;
                }
                if(line.contains("Content-Length:")){
                    content_Length=Integer.parseInt(line.substring(line.indexOf(":")+2));
                    continue;

                }
                if (json && line.equals("{")) {
//                    while (!line.equals("}")) {
                    for(int i=1;i<content_Length-4;i+=line.length()) {
                        try {
                            line = br.readLine();
                            String key = line.substring(0, line.indexOf(":"));
                            String value = line.substring(line.indexOf(":") + 1);
                            Pair<String,String> pair = new Pair<String,String>(key, value);
                            body.add(pair);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    break;
                }
                if(line.equals("")){
                    end=true;
                }
                if(end && !json ){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Message message = new Message(request,path,protocol, host, port, connection, content_Type,content_Length,body);
        return message;
    }
    public static ArrayList<Pair<String, String>> parseBody(String body) {
        ArrayList<Pair<String, String>> ParsedBody = new ArrayList();
        String[] lines = body.split("\n");
        for (String line : lines) {
            if (!line.equals("{")) {
                String key = line.substring(0, line.indexOf(":"));
                String value = line.substring(line.indexOf(":") + 1);
                Pair pair = new Pair(key, value);
                ParsedBody.add(pair);
            }
        }
        return ParsedBody;
    }
}
