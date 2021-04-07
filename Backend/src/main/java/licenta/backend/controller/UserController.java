package licenta.backend.controller;
import licenta.backend.exception.ResourceNotFoundException;
import licenta.backend.model.User;
import licenta.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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



    public  UserController(UserService userserv){
        userService=userserv;
    }
    @GetMapping
    public List<User> getAll() {
     return userService.findAll();
    }
    @PostMapping
    public User create(@RequestBody User user)
    {
        return userService.save(user);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id)
    {
        User user=userService.findById(id).orElseThrow(()-> new ResourceNotFoundException("User-ul cu id-ul " +id+ " nu exista "));
        return  ResponseEntity.ok(user);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
        User user=userService.findById(id).orElseThrow(()-> new ResourceNotFoundException("User-ul cu id-ul " +id+ " nu exista "));
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setType(userDetails.getType());
        User updateuser=userService.save(user);
        return  ResponseEntity.ok(updateuser);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Map<String ,Boolean>> deleteUserById(@PathVariable Long id)
    { User user=userService.findById(id).orElseThrow(()-> new ResourceNotFoundException("User-ul cu id-ul " +id+ " nu exista "));
      userService.delete(user);
      Map<String,Boolean> response=new HashMap<>();
      response.put("deleted",Boolean.TRUE);
      return ResponseEntity.ok(response);
    }
}

