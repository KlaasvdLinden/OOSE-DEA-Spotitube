package Service;

import Dao.Entity.User;
import Dao.Token.TokenDAO;
import Dao.User.UserDAO;
import Domain.Login.ResponseLogin;
import Exceptions.AccesNotAllowedException;

import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;

public class UserService {

    private  static UserService instance  = null;

    private static String userToken;
    private static int userID;
    private static String userName;

    private TokenDAO tokenDAO;

    private UserService(){}

    public static UserService getInstance () {
        if (instance == null){
            instance = new UserService();
        }
        return instance;
    }

    public static String getUserName() {
        return userName;
    }

    public static int getUserID(){
        return userID;
    }

    public ResponseLogin authenticate(String userName, String password){
        User user = new UserDAO().getUser(userName, password);

        if(user != null){
            ResponseLogin responseLogin = tokenDAO.updateToken(user.getId(), user.getUsername());
            userToken = responseLogin.getToken();
            userName = responseLogin.getUser();
            userID =  user.getId();
            return responseLogin;
        }

        return null;
    }

    public int rightToken(String token) throws AccesNotAllowedException {
        if(token.equals(token)){
            return userID;
        }
        throw new AccesNotAllowedException();
    }


    @Inject
    public void setTokenDAO(TokenDAO tokenDAO){
        this.tokenDAO = tokenDAO;
    }
}
