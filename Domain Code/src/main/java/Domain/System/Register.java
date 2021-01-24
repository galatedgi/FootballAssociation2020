package Domain.System;//roei cohen
import Domain.User.*;

public class Register {


    private String combineFullName(String full_name){
        String[] nameSplitted = full_name.split(" ");
        String full_name_fixed ="";
        for(int i = 0;i<nameSplitted.length;i++){
            if(i==0){
                full_name_fixed = nameSplitted[i];
            }
            else
                full_name_fixed = full_name_fixed+"_"+nameSplitted[i];
        }
        return  full_name_fixed;
    }

    public boolean registerToSystem(String user_name,String password,String user_id,String full_name){
        Member register_member = (Member)AlphaSystem.getSystem().GetSpecificFromMemory(2,user_name);
        if(register_member==null){
            Member new_Member = new Member(user_name,password,user_id,full_name);
            AlphaSystem.getSystem().AddtoMemory(2,new_Member);
            AlphaSystem.getSystem().getDB().insert(new_Member);
            return true;
        }
        return false;
    }
}
