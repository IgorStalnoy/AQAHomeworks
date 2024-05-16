package com.aqa.homeworks.db;

import java.util.List;

public interface Dao<T> {

    void create(T t);
    List<T> getAll();
}
