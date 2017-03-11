package com.spieler.service;

import com.spieler.amqp.MessagingProducer;
import com.spieler.cassandra.User;
import com.spieler.cassandra.UserRepository;
import com.spieler.redis.CentralCacheClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by spielerl on 05/03/2017.
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private CentralCacheClient centralCacheClient;
    @Autowired
    private MessagingProducer messagingProducer;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findByEmail(String email) {
        List<User> byEmail = userRepository.findByEmail(email);
        LOGGER.info("findByEmail {} found {}", email, byEmail.size());
        return byEmail;
    }

    @Override
    public User findById(String id) {
        User one = userRepository.findOne(id);
        LOGGER.info("findById {} found {}", id, one!=null);
        return one;
    }


    @Override
    public User findByName(String name) {
        User byName = userRepository.findByName(name);
        LOGGER.info("findByName {} found {}", name, byName!=null);
        return byName;
    }

    @Override
    public User save(User user) {
        User save = userRepository.save(user);
        LOGGER.info("save {} found {}", user, user.getId()!=null);
        return save;
    }
    @Override
    public void deleteAll() {
        LOGGER.info("deleteAll executed");
        userRepository.deleteAll();
    }



}
