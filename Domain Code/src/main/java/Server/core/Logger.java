package Server.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static Logger logger;
    private File eventLog;
    private File errorLog;

    private Logger(){
        eventLog = new File("src\\main\\resources\\Logger\\eventlogger.txt");
        errorLog = new File("src\\main\\resources\\Logger\\errorlogger.txt");
        try {
            eventLog.createNewFile();
            System.out.println("-----------------  event log created ----------------- ");
            errorLog.createNewFile();
            System.out.println("-----------------  error log created ----------------- ");

        }
        catch (IOException e){
            System.out.println("*****************  logger file failed ******************************");
            System.out.println(e);
        }
    }

    public static Logger getLogger(){
        if(logger==null) {
            logger = new Logger();
        }
        return logger;
    }

    public void writeToEventLogger(LocalDateTime time, String user_name, String action){
        try{
            FileWriter fileWriter = new FileWriter(eventLog,true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = time.format(formatter);
            bw.write("User Name: "+user_name+ ", Action: "+action+", Time: "+ formatDateTime+"\n");
            bw.close();
        }catch (IOException e){
            e.fillInStackTrace();
        }
    }

    public void writeToErrorLogger(LocalDateTime time, String error){
        try{
            FileWriter fileWriter = new FileWriter(errorLog,true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = time.format(formatter);
            bw.write("Error: "+error+", Time: "+ formatDateTime+"\n");
            bw.close();
        }catch (IOException e){
            e.fillInStackTrace();
        }
    }






}
