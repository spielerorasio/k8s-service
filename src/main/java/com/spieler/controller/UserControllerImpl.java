package com.spieler.controller;

import com.spieler.cassandra.User;
import com.spieler.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by spielerl on 05/03/2017.
 */
@RestController
public class UserControllerImpl implements UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerImpl.class);


    @Value("${some.config.name:World}")
    private String configName;

    @Autowired
    private UserService userService;

    @Override
    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello "+configName+"!";
    }


    @Override
    @RequestMapping(value="/user/email/{email}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> findByEmail(@PathVariable final String email) {
        return userService.findByEmail(email);
    }

    @Override
    @RequestMapping(value="/user/name/{name}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User findByName(@PathVariable final String name) {
        return userService.findByName(name);
    }

    @Override
    @RequestMapping(value="/user/id/{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User findById(@PathVariable  final String id) {
        return userService.findById(id);
    }

    @Override
    @RequestMapping(value="/user", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @Override
    @RequestMapping(value="/user/delete", method= RequestMethod.DELETE)
    public void deleteAll() {
        userService.deleteAll();
    }
}
