package Domain.Events;
import Domain.User.*;
import Domain.System.*;
import Domain.Jobs.*;
import Domain.Game.*;
import Domain.Association.*;
import java.sql.Time;
import java.time.LocalDateTime;

public class GoalEvent extends GameEvent {

    public GoalEvent(Time eventGameTime, Team team, Player player) {
        super(eventGameTime, team, player);
    }

    @Override
    public String toString() {
        return "The Player "
                + eventPlayer.getMember().getFull_name()
                + " of team " + eventTeam.getTeamName()
                + " scored a goal"
                + " at "
                + eventGameTime + ".";
    }





}
