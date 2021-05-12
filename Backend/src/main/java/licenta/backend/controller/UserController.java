package licenta.backend.controller;

import licenta.backend.exception.ResourceNotFoundException;
import licenta.backend.helpers.UserData;
import licenta.backend.model.User;
import licenta.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;


    public UserController(UserService userserv) {
        userService = userserv;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow(() -> new ResourceNotFoundException("User-ul cu id-ul " + id + " nu exista "));
        return ResponseEntity.ok(user);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,@RequestBody User user) {
        User user1=userService.findById(id).orElseThrow(()->new ResourceNotFoundException("User-ul cu id-ul " + id + " nu exista" ));
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setUsername(user.getUsername());
        User modifieduser=userService.save(user1);
        return  ResponseEntity.ok(modifieduser);


    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping("/updatehotelreview/{id}")
    public ResponseEntity<User> updatehotelreview(@PathVariable Long id,@RequestBody User user) {
        User user1=userService.findById(id).orElseThrow(()->new ResourceNotFoundException("User-ul cu id-ul " + id + " nu exista" ));
        user1.setHotelreview(user.getHotelreview());
        User modifieduser=userService.save(user1);
        return  ResponseEntity.ok(modifieduser);


    }


    @GetMapping("/user1/{username}")
    public UserData getUserData(@PathVariable String username) {
        return userService.getData(username);
    }

}

