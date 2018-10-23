package Dao.Token;

import Domain.Login.ResponseLogin;

public interface TokenMapper {
    ResponseLogin updateToken(int userID, String username);
}
