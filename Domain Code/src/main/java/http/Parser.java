package http;


import java.io.*;
import java.util.ArrayList;

public class Parser {
    public Parser() {
    }

    public static class StringPair {
        private String key;
        private String value;

        public StringPair(String k,String v){ key = k; value = v;}

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key + ":"+ value;
        }

        // standard getters and setters
    }

    public Request parse(InputStream inputStream) throws IOException {
        ArrayList<StringPair> body = new ArrayList<>();
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
                            StringPair pair = new StringPair(key, value);
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
        Request message = new Request(request,path,protocol, host, port, connection, content_Type,content_Length,body);
        return message;
    }

    public static String getElement(String key,ArrayList<Parser.StringPair> body){
        for(Parser.StringPair s:body){
             String keyVal=s.getKey().replace("\"","");
            keyVal=keyVal.replace("\t","");
            keyVal=keyVal.replace(" ","");
            if(keyVal.equals(key)) {
                String  value=s.getValue().substring(s.getValue().indexOf("\"")+1);
                value=value.substring(0,value.indexOf("\""));
                return value;
            }
        }
        return null;
    }
}
