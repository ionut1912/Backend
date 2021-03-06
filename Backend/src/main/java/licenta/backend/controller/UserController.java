package licenta.backend.controller;

import licenta.backend.exception.ResourceNotFoundException;
import licenta.backend.helpers.*;
import licenta.backend.model.Erole;
import licenta.backend.model.User;
import licenta.backend.payload.response.MessageResponse;
import licenta.backend.service.EmailService;
import licenta.backend.service.RezervationService;
import licenta.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    EmailService emailService;
@Resource
    RezervationService service;

    BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,@RequestBody UserHelper user) {
        User user1=userService.findById(id).orElseThrow(()->new ResourceNotFoundException("User-ul cu id-ul " + id + " nu exista" ));

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
    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping("/updatehotelreview/{id}")
    public ResponseEntity<User> updatehotelreview(@PathVariable Long id,@RequestBody UserHelper user) {
        User user1=userService.findById(id).orElseThrow(()->new ResourceNotFoundException("User-ul cu id-ul " + id + " nu exista" ));
        user1.setHotelreview(user.getHotelreview());
        User modifieduser=userService.save(user1);
        return  ResponseEntity.ok(modifieduser);


    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user1/{username}")
    public UserData getUserData(@PathVariable String username) {
        return userService.getData(username);
    }

@GetMapping("/hotelreview")
    public  List<HotelReviewHelper> getHotelReviews(){
        return  userService.getReviews();
}

@GetMapping("/rooms/{id}")
    public  List<UserRoomHelper> getUserRooms(@PathVariable Long id){
      return   this.userService.getRooms(id);
}
@PreAuthorize("hasRole('ROLE_ADMIN')")
@GetMapping("/nrofusers")
    public NrOfUsersHelpers getNrOfUsers(){
        return userService.getNrOfUsers();
}
@PreAuthorize("hasRole('ROLE_ADMIN')")
@GetMapping("/usersbytype")
    public  List<UsersByTypeHelper> getUsersByType(){
        return  userService.getUsersByType();
}
    @PreAuthorize("hasRole('ROLE_ADMIN')")
@GetMapping("/userreservations")
    public  List<NrOfUserReservation> getUserReservations(){
        return  userService.getNrOfUsersReservations();
}
@GetMapping("/emails/{email}")
    public UserEmailHelper findUserByEmail(@PathVariable String email){
        return  userService.getUser(email);
}
@PatchMapping("/usercode/{id}")
    public ResponseEntity<?> saveCode(@PathVariable Long id,@RequestBody UserHelper helper)
{
    if(userService.existsByEmail(helper.getEmail()))
    {  User user1=userService.findById(id).orElseThrow(()->new ResourceNotFoundException("User-ul cu id-ul " + id + " nu exista" ));
        user1.setUsercode(helper.getUsercode());
        emailService.sendMail(helper.getEmail(),"Schimbare parola!" , "Buna ziua! Cererea pentru schimbarea parolei a fost inregistrata,iar codul de resetare este:   " +  user1.getUsercode());
        userService.save(user1);
        return ResponseEntity.ok(new MessageResponse("Codul a fost introdus cu succes!"));

    }
    return  ResponseEntity.badRequest().body(new MessageResponse("Email-ul " + helper.getEmail()+ " nu exista"));
}
    @PatchMapping("/password/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable Long id,@RequestBody UserHelper helper)
    {

        User user1=userService.findById(id).orElseThrow(()->new ResourceNotFoundException("User-ul cu id-ul " + id + " nu exista" ));


      if(!encoder.matches(helper.getPassword(),user1.getPassword()))

        {

            user1.setPassword(encoder.encode(helper.getPassword()));

            emailService.sendMail(helper.getEmail(),"Parola  schimbata cu succes!" , "Buna ziua! Parola a fost schimbata cu succes!");
            userService.save(user1);
            return ResponseEntity.ok(new MessageResponse("Parola a fost schimbata cu succes!"));
        }

        return  ResponseEntity.badRequest().body(new MessageResponse("Parola introdusa este aceeasi cu cea existenta"));
    }
@GetMapping("/usercode/{id}")
    public  FindCodeHelper findCode(@PathVariable Long id){
        return  userService.findUserCode(id);
}

}




