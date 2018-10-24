package Service.User;

import Dao.Entity.User;
import Dao.Token.TokenDAOMapper;
import Dao.User.UserDAO;
import Domain.Login.ResponseLogin;
import IdentityMappers.UserIdentityMapper;

import javax.inject.Inject;

public class UserService implements  IUserService {

    @Inject
    private UserIdentityMapper userIdentityMapper;


    @Override
    public ResponseLogin authenticate(String username, String password){
       return userIdentityMapper.authenticate(username, password);
    }

    @Override
    public boolean rightToken(String token){
        return userIdentityMapper.rightToken(token);
    }

}
