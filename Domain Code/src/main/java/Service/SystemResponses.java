package Service;

import Domain.Association.AssociationMember;
import Domain.System.SystemAdmin;
import Domain.User.Member;
import http.Response;

public class SystemResponses {
    public void LoginResponse(Member member, Response response){
        response.addToBody("Fan","true");
        if(member.getJob("coach")!=null)
            response.addToBody("Coach","true");
        else
            response.addToBody("Coach","false");

        if(member.getJob("player")!=null)
            response.addToBody("Player","true");
        else
            response.addToBody("Player","false");

        if(member.getJob("referee")!=null)
            response.addToBody("Referee","true");
        else
            response.addToBody("Referee","false");

        if(member.getJob("manager")!=null)
            response.addToBody("TeamManager","true");
        else
            response.addToBody("TeamManager","false");

        if(member.getJob("owner")!=null)
            response.addToBody("TeamOwner","true");
        else
            response.addToBody("TeamOwner","false");

        if(member instanceof AssociationMember)
            response.addToBody("Association","true");
        else
            response.addToBody("Association","false");

        if(member instanceof SystemAdmin)
            response.addToBody("Admin","true");
        else
            response.addToBody("Admin","false");
    }
}
