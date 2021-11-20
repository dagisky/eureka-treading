package com.myRestful.rest.webservices.restfulwebservices.Services;

import com.myRestful.rest.webservices.restfulwebservices.GlobalExecption.UserNotFoundException;
import com.myRestful.rest.webservices.restfulwebservices.Models.User;
import com.myRestful.rest.webservices.restfulwebservices.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User findById(int id){
        Optional<User> user = userRepo.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("id-"+id);
        return user.orElse(null);
    }

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public User save(User user){
        return userRepo.save(user);
    }

    public void delete(User user){
        userRepo.delete(user);
    }

    public void deleteById(int id){
        User user = findById(id);
        delete(user);
    }
}
