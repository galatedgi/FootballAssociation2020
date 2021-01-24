package Domain.User;//roei cohen
import Domain.Events.*;
import Domain.System.*;
import Domain.Jobs.*;
import Domain.Game.*;
import Domain.Association.*;
import java.util.HashSet;
import java.util.Random;

public class Visitor extends User {

    private String user_id_visitor;
    private static HashSet<Integer> id_numbers_visitor = new HashSet<>();

    public String getUser_id_visitor(){
        return user_id_visitor;
    }

    public Visitor(){
        generateVisitorId();
    }

    public static String generateVisitorId(){
        String name = "visitor";
        Random rand = new Random();
        int id_num = rand.nextInt(1000000);
        while(id_numbers_visitor.contains(id_num)){
            id_num=rand.nextInt(1000000);
        }
        id_numbers_visitor.add(id_num);
        String id = Integer.toString(id_num);
        id = name+id;
        return id;
    }





}
