package Domain.System;

import Domain.Events.*;

public interface Observer {
    public void update(Event newEvent);
}
