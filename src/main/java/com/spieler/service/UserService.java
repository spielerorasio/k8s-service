package com.spieler.service;

import com.spieler.cassandra.User;

import java.util.List;

/**
 * Created by spielerl on 05/03/2017.
 */
public interface UserService {
    List<User> findByEmail(String email);
    User findByName(String name);
    User findById(String id);
    User save(User user);
    void deleteAll();
}
