package com.spieler.cassandra;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by spielerl on 05/03/2017.
 */
public interface UserRepository  extends CrudRepository<User, String> {
    @Query("Select * from user where name=?0")
    public User findByName(String name);

    @Query("Select * from user where email=?0")
    public List<User> findByEmail(String email);
}
