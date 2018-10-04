package Manager;

import Dao.User.UserDAO;
import Dao.util.DatabaseProperties;
import Domain.Login.RequestLogin;

import javax.inject.Inject;

public class UserManager {

    UserDAO userDAO = new UserDAO();

    public RequestLogin getRequestLogin(String user){
        return userDAO.getUser(user);
    }
}
