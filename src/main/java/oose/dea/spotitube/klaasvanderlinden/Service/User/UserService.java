package oose.dea.spotitube.klaasvanderlinden.Service.User;

import oose.dea.spotitube.klaasvanderlinden.Domain.Login.ResponseLogin;
import oose.dea.spotitube.klaasvanderlinden.IdentityMappers.UserIdentityMapper;

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
