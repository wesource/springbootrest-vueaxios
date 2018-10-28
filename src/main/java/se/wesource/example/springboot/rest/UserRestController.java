package se.wesource.example.springboot.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.wesource.example.springboot.rest.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class UserRestController {

    private Logger logger = LoggerFactory.getLogger(UserRestController.class);
    private List<User> usersOnServer = new ArrayList<>();

    private UserRestController() {
        User user1 = new User();
        user1.id = 1;

        user1.firstname = "Henric";
        user1.lastname = "Hedin";
        user1.email = "hhedin@foo.com";
        usersOnServer.add(user1);

        User user2 = new User();
        user2.id = 2;
        user2.firstname = "John";
        user2.lastname = "Doe";
        user2.email = "jdoe@foo.com";
        usersOnServer.add(user2);
    }

    @CrossOrigin
    @RequestMapping("/users")
    public List<User> listUsers() {
        logger.debug("list users");
        return usersOnServer;
    }

    @PostMapping(path = "/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        logger.debug("create user");
        usersOnServer.add(user);
        user.id = new Random().nextInt();
        logger.debug("Created user with name {} and id {}", user.firstname + user.lastname, user.id);
        return new ResponseEntity<Integer>(user.id, HttpStatus.OK);
    }
}