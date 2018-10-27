package oose.dea.spotitube.klaasvanderlinden.Service.User;

import oose.dea.spotitube.klaasvanderlinden.Domain.Login.ResponseLogin;

public interface IUserService {
    ResponseLogin authenticate(String userName, String password);

    boolean rightToken(String token);

}
