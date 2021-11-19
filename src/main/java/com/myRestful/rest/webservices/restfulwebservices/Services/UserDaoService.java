package com.myRestful.rest.webservices.restfulwebservices.Services;

import com.myRestful.rest.webservices.restfulwebservices.Models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCount = 3;
    static{
        users.add(new User(1, "Adem", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Jack", new Date()));
    }

    public List<User> findAll(){
        return  users;
    }

    public User save(User user){
        if(user.getId() == null){
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){
        for(User user: users){
            if(user.getId() == id)
                return user;
        }
        return null;
    }

    public User deleteById(int id){
        Iterator<User> itterator = users.iterator();
        while (itterator.hasNext()){
            User user = itterator.next();
            if(user.getId() == id){
                itterator.remove();
                return user;
            }
        }
        return null;
    }







}

