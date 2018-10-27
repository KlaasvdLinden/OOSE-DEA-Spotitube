package oose.dea.spotitube.klaasvanderlinden.Dao.Token;

import oose.dea.spotitube.klaasvanderlinden.Domain.Login.ResponseLogin;

public interface TokenDAOMapper {
    ResponseLogin updateToken(int userID, String username);
}
