package Domain.Events;
import Domain.User.*;
import Domain.System.*;
import Domain.Jobs.*;
import Domain.Game.*;
import Domain.Association.*;
import java.sql.Time;
import java.time.LocalDateTime;

public class SubstitutionEvent extends GameEvent {
    Player ingoingPlayer;

    public SubstitutionEvent(Time eventGameTime, Team team, Player outGoingPlayer, Player ingoingPlayer) {
        super(eventGameTime,team,outGoingPlayer);
        this.ingoingPlayer = ingoingPlayer;
    }

    @Override
    public String toString() {
        return "Substitution in team " +
                eventTeam.getTeamName() +
                "The Player " +
                ingoingPlayer.getMember().getFull_name() +
                " Subtituted " +
                this.eventPlayer.getMember().getFull_name() +
                " at " + eventGameTime + ".";
    }

    public Player getIngoingPlayer() {
        return ingoingPlayer;
    }

    public void setIngoingPlayer(Player ingoingPlayer) {
        this.ingoingPlayer = ingoingPlayer;
    }
}
