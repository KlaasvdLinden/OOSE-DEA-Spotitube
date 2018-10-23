package Dao.User;

import Dao.Entity.User;

public interface UserMapper {

    User getUser(String user, String password);
}
