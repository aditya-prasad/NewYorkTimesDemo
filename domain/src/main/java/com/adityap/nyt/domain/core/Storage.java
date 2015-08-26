package com.adityap.nyt.domain.core;

public interface Storage
{
    void put(String key, String value);

    String get(String key);

    void put(String key, Object value);

    <T> T get(String key, Class<T> type);
}
