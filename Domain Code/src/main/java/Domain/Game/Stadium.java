package Domain.Game;
import  Domain.System.*;

public class Stadium {

    private String stadiumName;
    private String city;

    public Stadium(String stadiumName, String city){
        this.stadiumName=stadiumName;
        this.city=city;
        AlphaSystem alphaSystem= AlphaSystem.getSystem();
        alphaSystem.AddtoMemory(11,this);
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public String getCity(){
        return city;
    }


    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

}
