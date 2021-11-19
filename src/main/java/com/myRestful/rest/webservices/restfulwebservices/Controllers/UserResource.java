package com.myRestful.rest.webservices.restfulwebservices.Controllers;

import com.myRestful.rest.webservices.restfulwebservices.GlobalExecption.UserNotFoundException;
import com.myRestful.rest.webservices.restfulwebservices.Models.User;
import com.myRestful.rest.webservices.restfulwebservices.Services.UserDaoService;
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
    private UserDaoService service;

    @GetMapping("/user")
    public List<User> retriveAllUsers(){
        return service.findAll();
    }


    @GetMapping("/user/{id}")
    public EntityModel<User> retriveUser(@PathVariable int id){
        User user = service.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id-"+id);
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkBuilder =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retriveAllUsers());
        model.add(linkBuilder.withRel("all-users"));
        return model;
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);
        if(user == null)
            throw new UserNotFoundException("id-"+id);
    }

    @PostMapping("/user")
    public ResponseEntity.BodyBuilder createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location);
    }



}
