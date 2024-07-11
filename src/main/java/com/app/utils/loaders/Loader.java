package com.app.utils;

public interface Loader<T> {
    T load(String path);
}
