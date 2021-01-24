package Server.Config;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {
    private static ConfigurationManager configurationManager;
    private static Configuration myCurrConfiguration;

    public ConfigurationManager(){

    }

    public static ConfigurationManager getInstance(){
        if(configurationManager == null)
            configurationManager = new ConfigurationManager();
        if(myCurrConfiguration==null)
            myCurrConfiguration=new Configuration();
        return configurationManager;
    }

    public void loadConfigurationFile(String filepath) {
        FileReader filereader = null;
        try {
            filereader = new FileReader(filepath);
        } catch (FileNotFoundException e){
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while ((i = filereader.read()) != -1){
                sb.append((char)i);
            }
        } catch (IOException e){
            throw new HttpConfigurationException(e);
        }
    }

    public Configuration getCurrConfiguration(){
        if(myCurrConfiguration == null){
            throw new HttpConfigurationException("No current configuration set.");
        }
        return myCurrConfiguration;
    }
}
