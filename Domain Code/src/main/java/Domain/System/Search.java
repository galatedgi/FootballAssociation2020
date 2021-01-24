package Domain.System;
import Domain.Jobs.*;
import Domain.Game.*;

import java.util.LinkedList;
import java.util.List;

public class Search {

    public List<Object> searchByName(String name, boolean leagueBool, boolean coachBool, boolean teamBool, boolean manBool, boolean ownerBool, boolean playerBool, boolean refBool, boolean stadBool){
        AlphaSystem system= AlphaSystem.getSystem();
        LinkedList<Object> found=new LinkedList<>();
        League league=(League) system.GetSpecificFromMemory(1,name);
        if(league!=null&&leagueBool)
            found.add(league);
        Coach coach=(Coach) system.GetSpecificFromMemory(3,name);
        if(coach!=null&&coachBool)
            found.add(coach);
        Team team=(Team) system.GetSpecificFromMemory(4,name);
        if(team!=null&&teamBool)
            found.add(team);
        TeamManager manager=(TeamManager)system.GetSpecificFromMemory(5,name);
        if(manager!=null&&manBool)
            found.add(manager);
        TeamOwner owner=(TeamOwner)system.GetSpecificFromMemory(6,name);
        if(owner!=null&&ownerBool)
            found.add(owner);
        Player player=(Player) system.GetSpecificFromMemory(7,name);
        if(player!=null&&playerBool)
            found.add(player);
        Referee referee=(Referee)system.GetSpecificFromMemory(9,name);
        if(referee!=null&&refBool)
            found.add(referee);
        Stadium stadium=(Stadium)system.GetSpecificFromMemory(11,name);
        if(stadium!=null&&stadBool)
            found.add(stadium);
        return found;
    }
    public List<Object> searchByCategory(int choice){
        AlphaSystem system= AlphaSystem.getSystem();
        List<Object> ret= (List<Object>) system.GetAllFromMemory(choice);
        return ret;
    }
}

