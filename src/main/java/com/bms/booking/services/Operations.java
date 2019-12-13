package com.bms.booking.services;

import java.util.List;

public interface Operations<T> {
    List<T> read(int id);
    T create(T t) ;
    void update(T t);
    void delete(int id);
}

