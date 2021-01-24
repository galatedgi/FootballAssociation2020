package Domain.Jobs;
import Domain.Events.*;
import Domain.User.*;
import Domain.System.*;
import Domain.Game.*;
import Domain.Association.*;

public class VarReferee extends Referee {
    public VarReferee(Member member) {
        super(member);
    }
}
