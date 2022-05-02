package com.fundamentos.controller;

import com.fundamentos.caseuse.CreateUser;
import com.fundamentos.caseuse.DeleteUser;
import com.fundamentos.caseuse.GetUser;
import com.fundamentos.caseuse.UpdateUser;
import com.fundamentos.entity.User;
import com.fundamentos.repository.UserRepository;
import com.fundamentos.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    //create, get, delete, update
    private GetUser getUser;
    private CreateUser createUser;
    private UpdateUser updateUser;
    private DeleteUser deleteUser;

    private UserService userService;


    public UserRestController(GetUser getUser, CreateUser createUser, UpdateUser updateUser, DeleteUser deleteUser, UserService userService) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.deleteUser = deleteUser;
        this.userService = userService;
    }

    @GetMapping
    public List<User> get(){
        return getUser.getALl();
    }

    @PostMapping
    public ResponseEntity<User> newUser(@RequestBody User newUser) {
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<User>> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return new ResponseEntity<>(updateUser.update(newUser, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        deleteUser.delete(id);
        return new ResponseEntity( HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pageable")
    public List<User> getUserPageable(@RequestParam int page, @RequestParam int size) {
        return userService.findAll(PageRequest.of(page, size));
    }
}
