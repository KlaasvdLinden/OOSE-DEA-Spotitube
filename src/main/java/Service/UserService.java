package Service;

import Dao.User.UserDAO;
import Domain.Login.RequestLogin;

public class UserService {

    UserDAO userDAO = new UserDAO();

    public RequestLogin getRequestLogin(String user){
        return userDAO.getUser(user);
    }
}
