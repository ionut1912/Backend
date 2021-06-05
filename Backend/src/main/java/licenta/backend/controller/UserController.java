package licenta.backend.controller;

import licenta.backend.exception.ResourceNotFoundException;
import licenta.backend.helpers.HotelReviewHelper;
import licenta.backend.helpers.UserData;
import licenta.backend.helpers.UserHelper;
import licenta.backend.model.Erole;
import licenta.backend.model.User;
import licenta.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;


@Resource
    PasswordEncoder encoder;
    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @PostMapping
    public User create(@RequestBody UserHelper userHelper)
    {
        User user=new User(userHelper.getName(),userHelper.getEmail(),userHelper.getUsername(),encoder.encode(encoder.encode(userHelper.getPassword())), Erole.ROLE_USER,true);
        return  userService.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow(() -> new ResourceNotFoundException("User-ul cu id-ul " + id + " nu exista "));
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,@RequestBody UserHelper user) {
        User user1=userService.findById(id).orElseThrow(()->new ResourceNotFoundException("User-ul cu id-ul " + id + " nu exista" ));
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setUsername(user.getUsername());
        user1.setType(user.getType());
        User modifieduser=userService.save(user1);
        return  ResponseEntity.ok(modifieduser);


    }
    @PatchMapping("/{id}/type")
    public ResponseEntity<User> updatewithoutType(@PathVariable Long id,@RequestBody UserHelper user) {
        User user1=userService.findById(id).orElseThrow(()->new ResourceNotFoundException("User-ul cu id-ul " + id + " nu exista" ));
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setUsername(user.getUsername());

        User modifieduser=userService.save(user1);
        return  ResponseEntity.ok(modifieduser);


    }
    @PatchMapping("/updatehotelreview/{id}")
    public ResponseEntity<User> updatehotelreview(@PathVariable Long id,@RequestBody UserHelper user) {
        User user1=userService.findById(id).orElseThrow(()->new ResourceNotFoundException("User-ul cu id-ul " + id + " nu exista" ));
        user1.setHotelreview(user.getHotelreview());
        User modifieduser=userService.save(user1);
        return  ResponseEntity.ok(modifieduser);


    }
@DeleteMapping("/{id}")
public  void deleteUser(@PathVariable Long id){
        this.userService.deleteUser(id);
}

    @GetMapping("/user1/{username}")
    public UserData getUserData(@PathVariable String username) {
        return userService.getData(username);
    }

@GetMapping("/hotelreview")
    public  List<HotelReviewHelper> getHotelReviews(){
        return  userService.getReviews();
}

}


