package Service;

import Dao.Token.TokenDAO;
import Dao.User.UserDAO;
import Domain.Login.RequestLogin;
import Domain.Login.ResponseLogin;

public class UserService {

    private  static UserService instance  = null;

    private static String userToken;
    private static String userName;

    private UserService(){}

    public static UserService getInstance () {
        if (instance == null){
            instance = new UserService();
        }
        return instance;
    }

    public ResponseLogin authenticate(String user, String password){
        RequestLogin requestLogin = new UserDAO().getUser(user, password);

        if(requestLogin != null){
            ResponseLogin responseLogin = new TokenDAO().generateOrGetToken(requestLogin.getUser());
            userToken = responseLogin.getToken();
            userName =  responseLogin.getUser();
            return responseLogin;
        }

        return null;
    }

    public boolean rightToken(String token) {
        return token.equals(userToken);
    }

    public String getUserName(){
        return userName;
    }
}
