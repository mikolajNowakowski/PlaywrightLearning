package com.app.utils.loaders;

public interface Loader<T> {
    T load(String path);
}
