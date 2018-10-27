package oose.dea.spotitube.klaasvanderlinden.Dao.User;

import oose.dea.spotitube.klaasvanderlinden.Dao.Entity.User;

public interface UserDAOMapper {

    User getUser(String user, String password);
}
