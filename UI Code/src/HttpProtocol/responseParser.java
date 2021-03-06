package HttpProtocol;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class responseParser {

    public Response parse(InputStream inputStream) throws IOException {
        ArrayList<Pair<String,String>> body = new ArrayList<>();
        String response = null;
        String protocol=null;
        String server = null;
        String date = null;
        String content_Type = null;
        int code = 0;
        int content_Length=0;
        boolean json = false;
        boolean end=false;
        InputStreamReader isr = new InputStreamReader(inputStream,"UTF-8");
        BufferedReader br = new BufferedReader(isr);

        try {
            String line = br.readLine();
            String[] split=line.split(" ");
            protocol = split[0];
            code=Integer.parseInt(split[1]);
            response=split[2];
            while (line != null) {
                line = br.readLine();
                if (line.contains("Server:")) {
                    server = line.substring(line.indexOf(':') + 1);
                    continue;
                }
                if (line.contains("Date:")) {
                     date= line.substring(line.indexOf(':') + 1);
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
                    for(int i=1;i<content_Length-4;i+=line.length()) {
                        try {
                            line = br.readLine();
                            if(line.equals("}"))
                                break;
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
        Response message = new Response(body,code,content_Type, protocol,server, content_Length);
        return message;
    }

    public static String getElement(String key,ArrayList<Pair<String,String>> body){
        for(Pair<String,String> s:body){
            String keyval = s.getKey().replace("\t","");
            keyval = keyval.replace("\"","");
            keyval = keyval.replace(" ","");
            if(keyval.equals(key))
                return s.getValue().replace("\"","");
        }
        return null;
    }
}
