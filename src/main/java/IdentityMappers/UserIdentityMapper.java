package IdentityMappers;

import Dao.Entity.User;
import Dao.Token.TokenDAOMapper;
import Dao.User.UserDAOMapper;
import Domain.Login.ResponseLogin;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;

@Singleton
public class UserIdentityMapper {

    @Inject
    UserDAOMapper userDAOMapper;

    @Inject
    private TokenDAOMapper tokenDAOMapper;

    private static UserIdentityMapper instance = new UserIdentityMapper();
    private static HashMap<Integer, User> userMap = new HashMap<>();

    private static String currentUserToken;
    private static int currentUserID;
    private boolean userInMapper = false;

    private UserIdentityMapper() {
    }

    public UserIdentityMapper getInstance() {
        return instance;
    }

    public ResponseLogin authenticate(String username, String password) {
        User user = getUser(username, password);
        if (user != null) {
            ResponseLogin responseLogin = tokenDAOMapper.updateToken(user.getId(), username);
            currentUserToken = responseLogin.getToken();
            currentUserID = user.getId();
            if(!userInMapper){
                putUserInMap(user);
            }
            return responseLogin;
        }
        return null;
    }

    public User getUser(String username, String password) {
        if (userInMap(username) != null) {
            userInMapper = true;
            return userInMap(username);
        }
        userInMapper = false;
        return userDAOMapper.getUser(username, password);
    }

    public boolean rightToken(String token) {
        return token.equals(currentUserToken);
    }

    private User userInMap(String username) {
        for (User user : userMap.values()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private void putUserInMap(User user){
        userMap.put(user.getId(), user);
    }

    public static int getCurrentUserID() {
        return currentUserID;
    }


}
