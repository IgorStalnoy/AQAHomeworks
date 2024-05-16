package com.aqa.homeworks.utils;

import lombok.experimental.UtilityClass;

import java.util.function.Consumer;
import java.util.function.Supplier;

@UtilityClass
public class ExceptionManager {

    public <T> T execute(Supplier<T> method) {
        try {
            return method.get();
        } catch (Exception e) {
            return  null;
        }
    }

    public <T> boolean execute(Consumer<T> method, T object) {
        try {
            method.accept(object);
            return true;
        } catch (Exception e) {
            return  false;
        }
    }
}
