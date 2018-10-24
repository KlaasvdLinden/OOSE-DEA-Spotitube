package Dao.User;

import Dao.Entity.User;

public interface UserDAOMapper {

    User getUser(String user, String password);
}
