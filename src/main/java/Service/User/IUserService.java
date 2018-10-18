package Service.User;

import Domain.Login.ResponseLogin;

public interface IUserService {
    ResponseLogin authenticate(String userName, String password);

    boolean rightToken(String token);

}
