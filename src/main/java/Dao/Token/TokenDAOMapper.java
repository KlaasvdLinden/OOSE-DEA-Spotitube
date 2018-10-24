package Dao.Token;

import Domain.Login.ResponseLogin;

public interface TokenDAOMapper {
    ResponseLogin updateToken(int userID, String username);
}
