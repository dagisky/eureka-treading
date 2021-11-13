package com.myRestful.rest.webservices.restfulwebservices.User;

import com.myRestful.rest.webservices.restfulwebservices.GlobalExecption.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    @Autowired
    private UserDaoService service;

    @GetMapping("/user")
    public List<User> retriveAllUsers(){
        return service.findAll();
    }
    @GetMapping("/user/{id}")
    public User retriveUser(@PathVariable int id){
        User user = service.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id-"+id);
        return service.findOne(id);
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);
        if(user == null)
            throw new UserNotFoundException("id-"+id);
    }

    @PostMapping("/user")
    public ResponseEntity.BodyBuilder createUser(@RequestBody User user){
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location);
    }



}
