package Domain.System;//roei cohen
import Domain.User.*;

public class Login {

    public Member loginToSystem(String user_name, String password){
        Member logged_in_member = (Member)AlphaSystem.getSystem().GetSpecificFromMemory(2,user_name);
        if(logged_in_member==null){
            return null;
        }
        if(!password.equals(logged_in_member.getUser_password())) {
            return null;
        }
        logged_in_member.setOnline(true);
        return logged_in_member;
    }
}
