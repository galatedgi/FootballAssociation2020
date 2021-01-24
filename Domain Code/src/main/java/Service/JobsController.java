package Service;

import Domain.Game.FootballGame;
import Domain.Game.Season;
import Domain.Game.Team;
import Domain.Jobs.*;
import Domain.System.AlphaSystem;
import Domain.User.Member;
import Exceptions.DomainException;
import Exceptions.notFoundException;
import Server.core.Logger;
import http.Parser;
import http.Response;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JobsController {
    private JobsResponses jobsResponses;

    public JobsController() {
        jobsResponses=new JobsResponses();
    }

    public void routing(String path, ArrayList<Parser.StringPair> body, Response response) throws notFoundException, DomainException{
        try {
            String input1, input2, input3, input4, input5,input6,input7;
            switch (path) {
                case "getCoachTeam":
                    input1 = Parser.getElement("userName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    getCoachTeam(input1);
                    break;
                case "addCoachTweet":
                    input1 = Parser.getElement("userName", body);
                    input2 = Parser.getElement("tweet", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    addCoachTweet(input1, input2);
                    break;
                case "deleteCoachTweet":
                    input1 = Parser.getElement("userName", body);
                    input2 = Parser.getElement("index", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    deleteCoachTweet(input1, Integer.parseInt(input2));
                    break;
                case "registerToCoach":
                    input1 = Parser.getElement("userName", body);
                    input2 = Parser.getElement("coachName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    registerToCoach(input1, input2);
                    break;
                case "unregisterFromCoach":
                    input1 = Parser.getElement("userName", body);
                    input2 = Parser.getElement("coachName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    unregisterFromCoach(input1, input2);
                    break;
                case "getCoachCertification":
                    input1 = Parser.getElement("coachName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    getCoachCertification(input1);
                    break;
                case "setCertification":
                    input1 = Parser.getElement("coachName", body);
                    input2 = Parser.getElement("certification", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    setCertification(input1, Integer.parseInt(input2));
                    break;
                case "getCoachJobInTheTeam":
                    input1 = Parser.getElement("coachName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    getCoachJobInTheTeam(input1);
                    break;
                case "setCoachJobInTheTeam":
                    input1 = Parser.getElement("coachName", body);
                    input2 = Parser.getElement("job", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    setCoachJobInTheTeam(input1, input2);
                    break;
                case "getCoachTweets":
                    input1 = Parser.getElement("coachName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    getCoachTweets(input1);
                    break;
                case "editFullCoachName":
                    input1 = Parser.getElement("coachName", body);
                    input2 = Parser.getElement("newName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    editFullCoachName(input1, input2);
                    break;
                case "getPlayerTeam":
                    input1 = Parser.getElement("playerName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    getPlayerTeam(input1);
                    break;
                case "getStringPlayerBirthDate":
                    input1 = Parser.getElement("playerName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    getStringPlayerBirthDate(input1);
                    break;
                case "getPlayerPositionName":
                    input1 = Parser.getElement("playerName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    getPlayerPositionName(input1);
                    break;
                case "addPlayerTweet":
                    input1 = Parser.getElement("playerName", body);
                    input2 = Parser.getElement("tweet", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    addPlayerTweet(input1, input2);
                    break;
                case "deletePlayerTweet":
                    input1 = Parser.getElement("playerName", body);
                    input2 = Parser.getElement("index", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    deletePlayerTweet(input1, Integer.parseInt(input2));
                    break;
                case "registerToPlayer":
                    input1 = Parser.getElement("userName", body);
                    input2 = Parser.getElement("playerName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    registerToPlayer(input1, input2);
                    break;
                case "unregisterFromPlayer":
                    input1 = Parser.getElement("userName", body);
                    input2 = Parser.getElement("playerName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    unregisterFromPlayer(input1, input2);
                    break;
                case "addPlayerToTeam":
                    input1 = Parser.getElement("playerName", body);
                    input2 = Parser.getElement("teamName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    addPlayerToTeam(input1, input2);
                    break;
                case "removePlayerFromTeam":
                    input1 = Parser.getElement("playerName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    removePlayerFromTeam(input1);
                    break;
                case "editPlayerFullName":
                    input1 = Parser.getElement("playerName", body);
                    input2 = Parser.getElement("newName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    editPlayerFullName(input1, input2);
                    break;
                case "editPlayerPosition":
                    input1 = Parser.getElement("playerName", body);
                    input2 = Parser.getElement("newPosition", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    editPlayerPosition(input1, input2);
                    break;
                case "editPlayerBirthDay":
                    input1 = Parser.getElement("playerName", body);
                    input2 = Parser.getElement("year", body);
                    input3 = Parser.getElement("month", body);
                    input4 = Parser.getElement("day", body);
                    if (input1 == null || input2 == null || input3 == null || input4 == null)
                        throw new DomainException("invalid input");
                    editPlayerBirthDay(input1, Integer.parseInt(input2), Integer.parseInt(input3), Integer.parseInt(input4));
                    break;
                case "getPlayerTweets":
                    input1 = Parser.getElement("playerName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    getPlayerTweets(input1);
                    break;
                case "editRefereeFullName":
                    input1 = Parser.getElement("refereeName", body);
                    input2 = Parser.getElement("newName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    editRefereeFullName(input1, input2);
                    break;
                case "editRefereeTraining":
                    input1 = Parser.getElement("refereeName", body);
                    input2 = Parser.getElement("training", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    editRefereeTraining(input1, input2);
                    break;
                case "CanMainRef":
                    input1 = Parser.getElement("refereeName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    CanMainRef(input1);
                    break;
                case "CanVarRef":
                    input1 = Parser.getElement("refereeName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    CanVarRef(input1);
                    break;
                case "CanLineRef":
                    input1 = Parser.getElement("refereeName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    CanLineRef(input1);
                    break;
                case "GetRefereeGames":
                    input1 = Parser.getElement("refereeName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    jobsResponses.GetRefereeGamesResponse(GetRefereeGames(input1),response);
                    break;
                case "DelistAsRef":
                    input1 = Parser.getElement("refereeName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    DelistAsRef(input1);
                    break;
                case "getManagerTeam":
                    input1 = Parser.getElement("managerName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    getManagerTeam(input1);
                case "setManagerTeam":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("tameName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    setManagerTeam(input1, input2);
                    break;
                case "removeAllManagerPermissions":
                    input1 = Parser.getElement("managerName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    removeAllManagerPermissions(input1);
                    break;
                case "managerOpenTeam":
                    input1 = Parser.getElement("managerName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    managerOpenTeam(input1);
                    break;
                case "managerCloseTeam":
                    input1 = Parser.getElement("managerName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    managerCloseTeam(input1);
                    break;
                case "managerAddWithdraw":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("sum", body);
                    input3 = Parser.getElement("description", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    managerAddWithdraw(input1, Double.parseDouble(input2), input3);
                    break;
                case "managerAddDeposit":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("sum", body);
                    input3 = Parser.getElement("description", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    managerAddDeposit(input1, Double.parseDouble(input2), input3);
                    break;
                case "managerAddTweet":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("tweet", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    managerAddTweet(input1, input2);
                    break;
                case "managerDeleteTweet":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("index", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    managerDeleteTweet(input1, Integer.parseInt(input2));
                    break;
                case "managerAddNewPlayer":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("userName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    managerAddNewPlayer(input1, input2);
                    break;
                case "managerRemoveExistingPlayer":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("userName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    managerRemoveExistingPlayer(input1, input2);
                    break;
                case "managerEditExistingPlayerName":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("userName", body);
                    input3 = Parser.getElement("name", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    managerEditExistingPlayerName(input1, input2, input3);
                    break;
                case "managerEditExistingPlayerPosition":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("userName", body);
                    input3 = Parser.getElement("position", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    managerEditExistingPlayerPosition(input1, input2, input3);
                    break;
                case "managerEditExistingPlayerBirthday":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("userName", body);
                    input3 = Parser.getElement("year", body);
                    input4 = Parser.getElement("month", body);
                    input5 = Parser.getElement("day", body);
                    if (input1 == null || input2 == null || input3 == null || input4 == null || input5 == null)
                        throw new DomainException("invalid input");
                    managerEditExistingPlayerBirthday(input1, input2, Integer.parseInt(input3), Integer.parseInt(input4), Integer.parseInt(input5));
                    break;
                case "managerAddNewCoach":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("userName", body);
                    input3 = Parser.getElement("job", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    managerAddNewCoach(input1, input2, input3);
                    break;
                case "managerRemoveExistingCoach":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("userName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    managerRemoveExistingCoach(input1, input2);
                    break;
                case "managerEditExistingCoachName":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("userName", body);
                    input3 = Parser.getElement("newName", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    managerEditExistingCoachName(input1, input2, input3);
                    break;
                case "managerEditExistingCoachCertification":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("userName", body);
                    input3 = Parser.getElement("certificationId", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    managerEditExistingCoachCertification(input1, input2, Integer.parseInt(input3));
                    break;
                case "managerEditExistingCoachJobInTeam":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("userName", body);
                    input3 = Parser.getElement("Job", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    managerEditExistingCoachJobInTeam(input1, input2, input3);
                    break;
                case "managerEditExistingManagerName":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("userName", body);
                    input3 = Parser.getElement("newName", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    managerEditExistingManagerName(input1, input2, input3);
                    break;
                case "managerEditExistingStadiumName":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("newName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    managerEditExistingStadiumName(input1, input2);
                    break;
                case "managerSetNewStadium":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("stadiumName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    managerSetNewStadium(input1, input2);
                    break;
                case "getManagerPermissions":
                    input1 = Parser.getElement("managerName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    getManagerPermissions(input1);
                    break;
                case "editManagerFullName":
                    input1 = Parser.getElement("managerName", body);
                    input2 = Parser.getElement("newName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    editManagerFullName(input1, input2);
                    break;
                case "ownerAddOwner":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("userName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    ownerAddOwner(input1, input2);
                    break;
                case "ownerRemoveOwner":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("userName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    ownerRemoveOwner(input1, input2);
                    break;
                case "ownerOpenTeam":
                    input1 = Parser.getElement("ownerName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    ownerOpenTeam(input1);
                    break;
                case "ownerCloseTeam":
                    input1 = Parser.getElement("ownerName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    ownerCloseTeam(input1);
                    break;
                case "ownerRemoveManger":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("userName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    ownerRemoveManger(input1, input2);
                    break;
                case "getOwnerAppointmentList":
                    input1 = Parser.getElement("ownerName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    getOwnerAppointmentList(input1);
                    break;
                case "ownerAddWithdraw":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("sum", body);
                    input3 = Parser.getElement("description", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    ownerAddWithdraw(input1, Double.parseDouble(input2), input3);
                    break;
                case "ownerAddDeposit":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("sum", body);
                    input3 = Parser.getElement("description", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    ownerAddDeposit(input1, Double.parseDouble(input2), input3);
                    break;
                case "editOwnerFullName":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("newName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    editOwnerFullName(input1, input2);
                    break;
                case "ownerAddTweet":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("tweet", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    ownerAddTweet(input1, input2);
                    break;
                case "ownerDeleteTweet":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("index", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    ownerDeleteTweet(input1, Integer.parseInt(input2));
                    break;
                case "setOwnerTeam":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("teamName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    setOwnerTeam(input1, input2);
                    break;
                case "getOwnerTeam":
                    input1 = Parser.getElement("ownerName", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    getOwnerTeam(input1);
                    break;
                case "ownerAddNewPlayer":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("userName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    ownerAddNewPlayer(input1, input2);
                    break;
                case "ownerRemoveExistingPlayer":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("userName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    ownerRemoveExistingPlayer(input1, input2);
                    break;
                case "ownerEditExistingPlayerName":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("userName", body);
                    input3 = Parser.getElement("newName", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    ownerEditExistingPlayerName(input1, input2, input3);
                    break;
                case "ownerEditExistingPlayerPosition":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("userName", body);
                    input3 = Parser.getElement("position", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    ownerEditExistingPlayerPosition(input1, input2, input3);
                    break;
                case "ownerEditExistingPlayerBirthday":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("userName", body);
                    input3 = Parser.getElement("year", body);
                    input4 = Parser.getElement("month", body);
                    input5 = Parser.getElement("day", body);
                    if (input1 == null || input2 == null || input3 == null || input4 == null || input5 == null)
                        throw new DomainException("invalid input");
                    ownerEditExistingPlayerBirthday(input1, input2, Integer.parseInt(input3), Integer.parseInt(input4), Integer.parseInt(input5));
                    break;
                case "ownerAddNewCoach":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("userName", body);
                    input3 = Parser.getElement("job", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    ownerAddNewCoach(input1, input2, input3);
                    break;
                case "ownerRemoveExistingCoach":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("userName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    ownerRemoveExistingCoach(input1, input2);
                    break;
                case "ownerEditExistingCoachName":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("userName", body);
                    input3 = Parser.getElement("newName", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    ownerEditExistingCoachName(input1, input2, input3);
                    break;
                case "ownerEditExistingCoachCertification":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("userName", body);
                    input3 = Parser.getElement("certificationId", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    ownerEditExistingCoachCertification(input1, input2, Integer.parseInt(input3));
                    break;
                case "ownerEditExistingCoachJobInTeam":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("userName", body);
                    input3 = Parser.getElement("Job", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    ownerEditExistingCoachJobInTeam(input1, input2, input3);
                    break;
                case "ownerEditExistingManagerName":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("userName", body);
                    input3 = Parser.getElement("newName", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    ownerEditExistingManagerName(input1, input2, input3);
                    break;
                case "ownerEditExistingStadiumName":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("newName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    ownerEditExistingStadiumName(input1, input2);
                    break;
                case "setNewStadium":
                    input1 = Parser.getElement("ownerName", body);
                    input2 = Parser.getElement("stadiumName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    setNewStadium(input1, input2);
                    break;
                case "refereeAddGoalEvent":
                    input1 = Parser.getElement("refereeName", body);
                    input2 = Parser.getElement("index", body);
                    input3 = Parser.getElement("time", body).replace("#",":");
                    input4 = Parser.getElement("teamName", body);
                    input5 = Parser.getElement("playerName", body);
                    if (input1 == null || input2 == null || input3 == null || input4 == null || input5 == null )
                        throw new DomainException("invalid input");
                    refereeAddGoalEvent(input1, input2,input3,input4,input5);
                    response.addToBody("message","The event added in successfully");
                    break;
                case "refereeAddFoulEvent":
                    input1 = Parser.getElement("refereeName", body);
                    input2 = Parser.getElement("index", body);
                    input3 = Parser.getElement("time", body).replace("#",":");
                    input4 = Parser.getElement("teamName", body);
                    input5 = Parser.getElement("playerName", body);
                    if (input1 == null || input2 == null || input3 == null || input4 == null || input5 == null )
                        throw new DomainException("invalid input");
                    refereeAddFoulEvent(input1, input2,input3,input4,input5);
                    response.addToBody("message","The event added in successfully");
                    break;
                case "refereeAddInjuryEvent":
                    input1 = Parser.getElement("refereeName", body);
                    input2 = Parser.getElement("index", body);
                    input3 = Parser.getElement("time", body).replace("#",":");
                    input4 = Parser.getElement("teamName", body);
                    input5 = Parser.getElement("playerName", body);
                    if (input1 == null || input2 == null || input3 == null || input4 == null || input5 == null )
                        throw new DomainException("invalid input");
                    refereeAddInjuryEvent(input1, input2,input3,input4,input5);
                    response.addToBody("message","The event added in successfully");
                    break;
                case "refereeAddOffsideEvent":
                    input1 = Parser.getElement("refereeName", body);
                    input2 = Parser.getElement("index", body);
                    input3 = Parser.getElement("time", body).replace("#",":");
                    input4 = Parser.getElement("teamName", body);
                    input5 = Parser.getElement("playerName", body);
                    if (input1 == null || input2 == null || input3 == null || input4 == null || input5 == null )
                        throw new DomainException("invalid input");
                    refereeAddOffsideEvent(input1, input2,input3,input4,input5);
                    response.addToBody("message","The event added in successfully");
                    break;
                case "refereeAddRedCardEvent":
                    input1 = Parser.getElement("refereeName", body);
                    input2 = Parser.getElement("index", body);
                    input3 = Parser.getElement("time", body).replace("#",":");
                    input4 = Parser.getElement("teamName", body);
                    input5 = Parser.getElement("playerName", body);
                    if (input1 == null || input2 == null || input3 == null || input4 == null || input5 == null )
                        throw new DomainException("invalid input");
                    refereeAddRedCardEvent(input1, input2,input3,input4,input5);
                    response.addToBody("message","The event added in successfully");
                    break;
                case "refereeAddYellowCardEvent":
                    input1 = Parser.getElement("refereeName", body);
                    input2 = Parser.getElement("index", body);
                    input3 = Parser.getElement("time", body).replace("#",":");
                    input4 = Parser.getElement("teamName", body);
                    input5 = Parser.getElement("playerName", body);
                    if (input1 == null || input2 == null || input3 == null || input4 == null || input5 == null )
                        throw new DomainException("invalid input");
                    refereeAddYellowCardEvent(input1, input2,input3,input4,input5);
                    response.addToBody("message","The event added in successfully");
                    break;
                case "refereeAddSubstitutionEvent":
                    input1 = Parser.getElement("refereeName", body);
                    input2 = Parser.getElement("index", body);
                    input3 = Parser.getElement("time", body).replace("#",":");;
                    input4 = Parser.getElement("teamName", body);
                    input5 = Parser.getElement("playerInName", body);
                    input6 = Parser.getElement("playerOutName", body);
                    if (input1 == null || input2 == null || input3 == null || input4 == null || input5 == null || input6 == null )
                        throw new DomainException("invalid input");
                    refereeAddSubstitutionEvent(input1, input2,input3,input4,input5,input6);
                    response.addToBody("message","The event added in successfully");
                    break;
                case "refereeCreateReport":
                    input1 = Parser.getElement("refereeName", body);
                    input2 = Parser.getElement("index", body);
                    if (input1 == null || input2 == null )
                        throw new DomainException("invalid input");
                    response.addToBody("report",refereeCreateReport(input1, input2));
                    break;
                case "refereeAddEndGameEvent":
                    input1 = Parser.getElement("refereeName", body);
                    input2 = Parser.getElement("index", body);
                    if (input1 == null || input2 == null )
                        throw new DomainException("invalid input");
                    refereeAddEndGameEvent(input1,input2);
                    response.addToBody("message","The event added in successfully");
                    break;
                default:
                    throw new notFoundException();
            }
        }catch (Exception e){
            if(e.getClass().equals(DomainException.class))
                throw (DomainException)e;
            if(e.getClass().equals(NumberFormatException.class))
                throw (NumberFormatException)e;
            throw new notFoundException();
        }

    }

    public Team getCoachTeam(String userName) throws DomainException {
        Coach coach= (Coach) AlphaSystem.getSystem().GetSpecificFromMemory(3,userName);
        if(coach==null)
            throw new DomainException("this user name is not a coach");
        return coach.getTeam();
    }

    public void addCoachTweet(String userName,String tweet) throws DomainException {
        Coach coach= (Coach) AlphaSystem.getSystem().GetSpecificFromMemory(3,userName);
        if(coach==null)
            throw new DomainException("this user name is not a coach");
        coach.addTweet(tweet);
    }

    public void deleteCoachTweet(String userName,int index) throws DomainException {
        Coach coach= (Coach) AlphaSystem.getSystem().GetSpecificFromMemory(3,userName);
        if(coach==null)
            throw new DomainException("this user name is not a coach");
        coach.deleteTweet(index);
    }

    public void registerToCoach(String userName,String coachName) throws DomainException {
        Coach coach= (Coach) AlphaSystem.getSystem().GetSpecificFromMemory(3,coachName);
        if(coach==null)
            throw new DomainException("this name is not a coach name");
        Member member= (Member) AlphaSystem.getSystem().GetSpecificFromMemory(2,userName);
        if(member==null)
            throw new DomainException("this user name is invalid");
        coach.register(member);
    }

    public void unregisterFromCoach(String userName,String coachName) throws DomainException {
        Coach coach= (Coach) AlphaSystem.getSystem().GetSpecificFromMemory(3,coachName);
        if(coach==null)
            throw new DomainException("this name is not a coach name");
        Member member= (Member) AlphaSystem.getSystem().GetSpecificFromMemory(2,userName);
        if(member==null)
            throw new DomainException("this user name is invalid");
        coach.unregister(member);
    }

    public Coach.Certification getCoachCertification(String coachName) throws DomainException {
        Coach coach= (Coach) AlphaSystem.getSystem().GetSpecificFromMemory(3,coachName);
        if(coach==null)
            throw new DomainException("this name is not a coach name");
        return coach.getCertification();
    }

    public boolean setCertification(String coachName,int certification) throws DomainException {
        Coach coach= (Coach) AlphaSystem.getSystem().GetSpecificFromMemory(3,coachName);
        if(coach==null)
            throw new DomainException("this name is not a coach name");
        return coach.setCertification(certification);
    }

    public String getCoachJobInTheTeam(String coachName) throws DomainException {
        Coach coach= (Coach) AlphaSystem.getSystem().GetSpecificFromMemory(3,coachName);
        if(coach==null)
            throw new DomainException("this name is not a coach name");
        return coach.getJobInTheTeam();
    }

    public void setCoachJobInTheTeam(String coachName,String job) throws DomainException {
        Coach coach= (Coach) AlphaSystem.getSystem().GetSpecificFromMemory(3,coachName);
        if(coach==null)
            throw new DomainException("this name is not a coach name");
        coach.setJobInTheTeam(job);
    }

    public List<String> getCoachTweets(String coachName) throws DomainException {
        Coach coach= (Coach) AlphaSystem.getSystem().GetSpecificFromMemory(3,coachName);
        if(coach==null)
            throw new DomainException("this name is not a coach name");
        return coach.getTweets();
    }

    public void editFullCoachName(String coachName,String newName) throws DomainException {
        Coach coach= (Coach) AlphaSystem.getSystem().GetSpecificFromMemory(3,coachName);
        if(coach==null)
            throw new DomainException("this name is not a coach name");
        coach.editFullName(newName);
    }

    public Team getPlayerTeam(String playerName) throws DomainException {
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        if(player==null)
            throw new DomainException("this user name is not a player");
        return player.getTeam();
    }

    public String getStringPlayerBirthDate(String playerName) throws DomainException {
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        if(player==null)
            throw new DomainException("this user name is not a player");
        return player.getStringBirthDate();
    }

    public String getPlayerPositionName(String playerName) throws DomainException {
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        if(player==null)
            throw new DomainException("this user name is not a player");
        return player.getPositionName();
    }

    public void addPlayerTweet(String playerName,String tweet) throws DomainException {
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        if(player==null)
            throw new DomainException("this user name is not a player");
        player.addTweet(tweet);
    }

    public void deletePlayerTweet(String playerName,int index) throws DomainException {
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        if(player==null)
            throw new DomainException("this user name is not a player");
        player.deleteTweet(index);
    }

    public void registerToPlayer(String userName,String playerName) throws DomainException {
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        if(player==null)
            throw new DomainException("this name is not a player name");
        Member member= (Member) AlphaSystem.getSystem().GetSpecificFromMemory(2,userName);
        if(member==null)
            throw new DomainException("this user name is invalid");
        player.register(member);
    }

    public void unregisterFromPlayer(String userName,String playerName) throws DomainException {
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        if(player==null)
            throw new DomainException("this name is not a player name");
        Member member= (Member) AlphaSystem.getSystem().GetSpecificFromMemory(2,userName);
        if(member==null)
            throw new DomainException("this user name is invalid");
        player.unregister(member);
    }

    public boolean addPlayerToTeam(String playerName,String teamName) throws DomainException {
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        if(player==null)
            throw new DomainException("this name is not a player name");
        Team team= (Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,teamName);
        if(player==null)
            throw new DomainException("this name is not a team name");
        return  player.addToTeam(team);
    }

    public boolean removePlayerFromTeam(String playerName) throws DomainException {
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        if(player==null)
            throw new DomainException("this name is not a player name");
        return  player.removeFromTeam();
    }

    public void editPlayerFullName(String playerName,String newName) throws DomainException {
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        if(player==null)
            throw new DomainException("this name is not a player name");
        player.editFullName(newName);
    }

    public boolean editPlayerPosition(String playerName,String newPosition) throws DomainException {
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        if(player==null)
            throw new DomainException("this name is not a player name");
        return player.editPosition(newPosition);
    }

    public void editPlayerBirthDay(String playerName,int year,int month,int day) throws DomainException {
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        if(player==null)
            throw new DomainException("this name is not a player name");
        player.editBirthDay(year,month,day);
    }

    public List<String> getPlayerTweets(String playerName) throws DomainException {
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        if(player==null)
            throw new DomainException("this name is not a coach name");
        return player.getTweets();
    }

    public void editRefereeFullName(String refereeName,String newName) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
        referee.editFullName(newName);
    }

    public void editRefereeTraining(String refereeName,String training) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
        referee.editTraining(training);
    }

    public boolean CanMainRef(String refereeName) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
       return referee.CanMainRef();
    }

    public boolean CanVarRef(String refereeName) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
        return referee.CanVarRef();    }

    public boolean CanLineRef(String refereeName) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
        return referee.CanLineRef();
    }

    public List<FootballGame> GetRefereeGames(String refereeName) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
        return referee.GetGames();
    }

    public void DelistAsRef(String refereeName) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
         referee.DelistAsRef();
    }

//להשלים
    public void AddGameToRef(String refereeName,FootballGame ToRef) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
         referee.AddGameToRef(ToRef);    }

    public void AddSeasonToRef(String refereeName,Season SeasonToRef) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
        referee.AddSeasonToRef(SeasonToRef);    }

    //כרגע לא עובד
//    private void AddEvent(FootballGame Game, GameEvent ChosenEvent){
//        //Game.AddEvent();
//    }

//    private void EditEvents(FootballGame Game, GameEvent ChosenEvent){
//        //if game ended in less than 5 hours
//        //Game.RemoveEvent();
//        //Game.AddEvent();
//    }

    public Team getManagerTeam(String managerName) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        return manager.getTeam();
    }

    public void setManagerTeam(String managerName,String tameName) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        Team team= (Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,tameName);
        if(manager==null)
            throw new DomainException("this name is not a team name");
        manager.setTeam(team);
    }

    public void removeAllManagerPermissions(String managerName) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        manager.removeAllPermissions();
    }

    public void managerOpenTeam(String managerName) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        manager.openTeam();
    }

    public void managerCloseTeam(String managerName) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        manager.closeTeam();
    }

    public void managerAddWithdraw(String managerName,Double sum,String description) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        manager.addWithdraw(sum,description);
    }

    public void managerAddDeposit(String managerName,Double sum,String description) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        manager.addDeposit(sum,description);
    }

    public void managerAddTweet(String managerName,String tweet) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        manager.addTweet(tweet);
    }

    public void managerDeleteTweet(String managerName,int index) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        manager.deleteTweet(index);
    }

    public void setManagerPermissions(String managerName,ArrayList<TeamManager.Permissions> permissions) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        manager.setPermissions(permissions);
    }

    public boolean managerAddNewPlayer(String managerName,String userName) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        return manager.addNewPlayer(userName);
    }

    public boolean managerRemoveExistingPlayer(String managerName,String userName) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        return manager.removeExistingPlayer(userName);
    }

    public boolean managerEditExistingPlayerName(String managerName,String userName,String name) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
       return manager.editExistingPlayerName(userName,name);
    }

    public boolean managerEditExistingPlayerPosition(String managerName,String userName,String position) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        return manager.editExistingPlayerPosition(userName,position);
    }

    public boolean managerEditExistingPlayerBirthday(String managerName,String userName,int year,int month,int day) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        return manager.editExistingPlayerBirthday(userName,year,month,day);
    }

    public boolean managerAddNewCoach(String managerName,String userName,String job) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        return manager.addNewCoach(userName,job);
    }

    public boolean managerRemoveExistingCoach(String managerName,String userName) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        return manager.removeExistingCoach(userName);
    }

    public boolean managerEditExistingCoachName(String managerName,String userName,String newName) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        return manager.editExistingCoachName(userName,newName);
    }

    public boolean managerEditExistingCoachCertification(String managerName,String userName,int certificationId) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        return manager.editExistingCoachCertification(userName,certificationId);
    }

    public boolean managerEditExistingCoachJobInTeam(String managerName,String userName,String Job) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        return manager.editExistingCoachJobInTeam(userName,Job);
    }

    public boolean managerEditExistingManagerName(String managerName,String userName,String newName) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        return manager.editExistingManagerName(userName,newName);
    }

    public boolean managerEditExistingStadiumName(String managerName,String newName) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        return manager.editExistingStadiumName(newName);
    }

    public boolean managerSetNewStadium(String managerName,String stadiumName) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        return manager.setNewStadium(stadiumName);
    }

    public ArrayList<TeamManager.Permissions> getManagerPermissions(String managerName) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        return manager.getPermissions();
    }

    public void editManagerFullName(String managerName,String newName) throws DomainException {
        TeamManager manager= (TeamManager) AlphaSystem.getSystem().GetSpecificFromMemory(5,managerName);
        if(manager==null)
            throw new DomainException("this name is not a manager name");
        manager.editFullName(newName);    }

    public void ownerAddOwner(String ownerName,String userName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        owner.addOwner(userName);
    }

    public void ownerRemoveOwner(String ownerName,String userName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        owner.removeOwner(userName);
    }

    public void ownerOpenTeam(String ownerName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        owner.openTeam();
    }

    public void ownerCloseTeam(String ownerName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        owner.closeTeam();
    }

    public void ownerAddManager(String ownerName,String userName,boolean[] permissionsList) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        owner.addManager(userName,permissionsList);
    }

    public void ownerRemoveManger(String ownerName,String userName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        owner.removeOwner(userName);
    }

    public List<Job> getOwnerAppointmentList(String ownerName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        return owner.getAppointmentList();
    }

    public void ownerAddWithdraw(String ownerName,Double sum,String description) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
         owner.addWithdraw(sum,description);
    }

    public void ownerAddDeposit(String ownerName,Double sum,String description) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        owner.addDeposit(sum,description);
    }

    public void editOwnerFullName(String ownerName,String newName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        owner.editFullName(newName);      }

    public void ownerAddTweet(String ownerName,String tweet) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        owner.addTweet(tweet);

    }

    public void ownerDeleteTweet(String ownerName,int index) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        owner.deleteTweet(index);
    }

    public boolean ownerSetPermissionsToManager(String ownerName,String manager,boolean[] permissionsList) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        return owner.setPermissionsToManager(manager,permissionsList);
    }

    public void setOwnerTeam(String ownerName,String teamName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        Team team= (Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,teamName);
        if(team==null)
            throw new DomainException("this team is not exist");
        owner.setTeam(team);
    }

    public Team getOwnerTeam(String ownerName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        return owner.getTeam();
    }

    public boolean ownerAddNewPlayer(String ownerName,String userName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        return owner.addNewPlayer(userName);
    }

    public boolean ownerRemoveExistingPlayer(String ownerName,String userName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        return owner.removeExistingPlayer(userName);
    }

    public boolean ownerEditExistingPlayerName(String ownerName,String userName,String newName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        return owner.editExistingPlayerName( userName, newName);
    }

    public boolean ownerEditExistingPlayerPosition(String ownerName,String userName,String position) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        return owner.editExistingPlayerPosition( userName, position);
    }

    public boolean ownerEditExistingPlayerBirthday(String ownerName,String userName,int year,int month,int day) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        return owner.editExistingPlayerBirthday( userName, year, month, day);
    }

    public boolean ownerAddNewCoach(String ownerName,String userName,String job) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        return owner.addNewCoach( userName, job);
    }

    public boolean ownerRemoveExistingCoach(String ownerName,String userName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        return owner.removeExistingCoach( userName);
    }

    public boolean ownerEditExistingCoachName(String ownerName,String userName,String newName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        return owner.editExistingCoachName( userName, newName);
    }

    public boolean ownerEditExistingCoachCertification(String ownerName,String userName,int certificationId) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        return owner.editExistingCoachCertification( userName, certificationId);
    }

    public boolean ownerEditExistingCoachJobInTeam(String ownerName,String userName,String Job) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        return owner.editExistingCoachJobInTeam( userName, Job);
    }

    public boolean ownerEditExistingManagerName(String ownerName,String userName,String newName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        return owner.editExistingManagerName( userName, newName);
    }

    public boolean ownerEditExistingStadiumName(String ownerName,String newName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        return owner.editExistingStadiumName( newName);
    }

    public boolean setNewStadium(String ownerName,String stadiumName) throws DomainException {
        TeamOwner owner= (TeamOwner) AlphaSystem.getSystem().GetSpecificFromMemory(6,ownerName);
        if(owner==null)
            throw new DomainException("this name is not a owner name");
        return owner.setNewStadium( stadiumName);
    }

    public void refereeAddGoalEvent(String refereeName,String index, String time, String teamName, String playerName) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        Team team= (Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,teamName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
        if(player==null)
            throw new DomainException("this name is not a player name");
        if(team==null)
            throw new DomainException("this name is not a team name");
        referee.addGoalEvent(Integer.parseInt(index),Time.valueOf(time),team,player);
        Logger.getLogger().writeToEventLogger(LocalDateTime.now(),referee.getMemberUserName(),"add new goal event");
    }

    public void refereeAddFoulEvent(String refereeName,String index, String time, String teamName, String playerName) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        Team team= (Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,teamName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
        if(player==null)
            throw new DomainException("this name is not a player name");
        if(team==null)
            throw new DomainException("this name is not a team name");
        referee.addFoulEvent(Integer.parseInt(index),Time.valueOf(time),team,player);
        Logger.getLogger().writeToEventLogger(LocalDateTime.now(),referee.getMemberUserName(),"add new foul event");
    }

    public void refereeAddInjuryEvent(String refereeName,String index, String time, String teamName, String playerName) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        Team team= (Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,teamName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
        if(player==null)
            throw new DomainException("this name is not a player name");
        if(team==null)
            throw new DomainException("this name is not a team name");
        referee.addInjuryEvent(Integer.parseInt(index),Time.valueOf(time),team,player);
        Logger.getLogger().writeToEventLogger(LocalDateTime.now(),referee.getMemberUserName(),"add new injury event");
    }

    public void refereeAddOffsideEvent(String refereeName,String index, String time, String teamName, String playerName) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        Team team= (Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,teamName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
        if(player==null)
            throw new DomainException("this name is not a player name");
        if(team==null)
            throw new DomainException("this name is not a team name");
        referee.addOffsideEvent(Integer.parseInt(index),Time.valueOf(time),team,player);
        Logger.getLogger().writeToEventLogger(LocalDateTime.now(),referee.getMemberUserName(),"add new offside event");
    }

    public void refereeAddRedCardEvent(String refereeName,String index, String time, String teamName, String playerName) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        Team team= (Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,teamName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
        if(player==null)
            throw new DomainException("this name is not a player name");
        if(team==null)
            throw new DomainException("this name is not a team name");
        referee.addRedCardEvent(Integer.parseInt(index),Time.valueOf(time),team,player);
        Logger.getLogger().writeToEventLogger(LocalDateTime.now(),referee.getMemberUserName(),"add new red card event");

    }

    public void refereeAddYellowCardEvent(String refereeName,String index, String time, String teamName, String playerName) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        Player player= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerName);
        Team team= (Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,teamName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
        if(player==null)
            throw new DomainException("this name is not a player name");
        if(team==null)
            throw new DomainException("this name is not a team name");
        referee.addYellowCardEvent(Integer.parseInt(index),Time.valueOf(time),team,player);
        Logger.getLogger().writeToEventLogger(LocalDateTime.now(),referee.getMemberUserName(),"add new yellow event");
    }

    public void refereeAddSubstitutionEvent(String refereeName,String index, String time, String teamName, String playerInName,String playerOutName) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        Player in= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerInName);
        Player out= (Player) AlphaSystem.getSystem().GetSpecificFromMemory(7,playerOutName);
        Team team= (Team) AlphaSystem.getSystem().GetSpecificFromMemory(4,teamName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
        if(in==null)
            throw new DomainException("this name is not a player name");
        if(out==null)
            throw new DomainException("this name is not a player name");
        if(team==null)
            throw new DomainException("this name is not a team name");
        referee.addSubstitutionEvent(Integer.parseInt(index),Time.valueOf(time),team,in,out);
        Logger.getLogger().writeToEventLogger(LocalDateTime.now(),referee.getMemberUserName(),"add new substitution event");
    }

    public String refereeCreateReport(String refereeName,String index) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
        Logger.getLogger().writeToEventLogger(LocalDateTime.now(),referee.getMemberUserName(),"create new game report event");
        return referee.crateReport(Integer.parseInt(index));
    }

    public void refereeAddEndGameEvent(String refereeName,String index) throws DomainException {
        Referee referee= (Referee) AlphaSystem.getSystem().GetSpecificFromMemory(9,refereeName);
        if(referee==null)
            throw new DomainException("this name is not a referee name");
        referee.addEndGameEvent(Integer.parseInt(index));
        Logger.getLogger().writeToEventLogger(LocalDateTime.now(),referee.getMemberUserName(),"add new end game event");
    }


}
