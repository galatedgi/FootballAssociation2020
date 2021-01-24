package Domain.Jobs;
import Domain.Events.*;
import Domain.User.*;
import Domain.System.*;
import Domain.Game.*;
import Domain.Association.*;

public class MainReferee extends Referee {
    public MainReferee(Member member) {
        super(member);
    }
}
