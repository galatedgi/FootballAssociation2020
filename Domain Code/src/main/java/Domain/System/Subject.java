package Domain.System;
import Domain.Events.*;
import Domain.User.*;
import Domain.Jobs.*;
import Domain.Game.*;
import Domain.Association.*;
public interface Subject {
    public void register(Observer observer) throws Exception;
    public void unregister(Observer observer) throws Exception;
    public void notifyObserver(Event newEvent);

}
