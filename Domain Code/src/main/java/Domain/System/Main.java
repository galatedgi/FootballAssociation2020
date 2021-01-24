package Domain.System;
import Domain.Events.*;
import Domain.User.*;
import Domain.Jobs.*;
import Domain.Game.*;
import Domain.Association.*;

import java.time.LocalDate;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        InsertToDB ins=new InsertToDB("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        ins.updateSeasonSchedualingP("Bundes League",2005,200);
    }
}
