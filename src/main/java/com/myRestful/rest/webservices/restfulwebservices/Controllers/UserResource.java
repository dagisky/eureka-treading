package com.myRestful.rest.webservices.restfulwebservices.Controllers;
import com.myRestful.rest.webservices.restfulwebservices.Models.User;
import com.myRestful.rest.webservices.restfulwebservices.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> retriveAllUsers(){
        return userService.findAll();
    }


    @GetMapping("/user/{id}")
    public EntityModel<User> retriveUser(@PathVariable int id){
        User user = userService.findById(id);
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkBuilder =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retriveAllUsers());
        model.add(linkBuilder.withRel("all-users"));
        return model;
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteById(id);
    }

    @PostMapping("/user")
    public ResponseEntity.BodyBuilder createUser(@Valid @RequestBody User user){
        User savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location);
    }



}
