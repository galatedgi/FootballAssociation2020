package Service;

import Exceptions.DomainException;
import Exceptions.notFoundException;
import http.Parser;
import http.Response;

import java.util.ArrayList;

public class MainController {

    private static MainController controller;
    private JobsController jobsController;
    private AssociationController associationController;
    private SystemController systemController;
    private UserController userController;
    private GameController gameController;

    private MainController() {
        associationController = new AssociationController();
        jobsController = new JobsController();
        systemController = new SystemController();
        userController = new UserController();
        gameController=new GameController();
    }

    public static MainController getInstance() {
        if (controller == null) {
            controller = new MainController();
        }
        return controller;
    }

    public void routing(String path, ArrayList<Parser.StringPair> body, Response response) throws notFoundException, DomainException {
        String[] paths = path.split("/");
        try {
            switch (paths[1]) {
                case "Jobs":
                    jobsController.routing(paths[2], body, response);
                    break;
                case "Association":
                    associationController.routing(paths[2], body, response);
                    break;
                case "User":
                    userController.routing(paths[2], body, response);
                    break;
                case "System":
                    systemController.routing(paths[2], body, response);
                    break;
                case "Game":
                    gameController.routing(paths[2], body, response);
                    break;
                default:
                    throw new notFoundException();
            }
        } catch (Exception e) {
            if (e.getClass().equals(DomainException.class))
                throw (DomainException) e;
            if (e.getClass().equals(NumberFormatException.class))
                throw (NumberFormatException) e;
            throw new notFoundException();
        }

    }
}
