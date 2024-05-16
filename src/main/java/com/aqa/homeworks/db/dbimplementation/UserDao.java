package com.aqa.homeworks.db.dbimplementation;

import com.aqa.homeworks.buisness.User;
import com.aqa.homeworks.db.Dao;

public interface UserDao extends Dao<User> {

    User getUserByName(String name);

}
