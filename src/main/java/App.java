import Dao.util.DatabaseProperties;
import Manager.UserManager;

public class App {

    public static void main(String[] args){
        UserManager um = new UserManager();

        System.out.println(um.getRequestLogin("klaas").getUser());
    }
}
