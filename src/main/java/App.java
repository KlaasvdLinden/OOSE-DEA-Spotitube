import Service.UserService;

public class App {

    public static void main(String[] args){
        UserService um = new UserService();

        System.out.println(um.getRequestLogin("klaas").getUser());
    }
}