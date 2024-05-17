package com.aqa.homeworks.db.dbimplementation;

import com.aqa.homeworks.db.Dao;
import com.aqa.homeworks.entity.User;

public interface UserDao extends Dao<User> {

    User getByName(String name);

}
