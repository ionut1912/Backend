package licenta.backend.service;

import licenta.backend.helpers.*;
import licenta.backend.model.User;
import licenta.backend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    public UserService(UserRepository u) {
        userRepository = u;
    }

    public List<User> findAll() {
        return userRepository.findAll();

    }


    public User save(User user) {

        return userRepository.save(user);
    }

    public <T> Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    public UserData getData(String username) {
        return userRepository.getUserDetails(username);

    }
public User getOneById(long id){
        return  userRepository.getOne(id);
}
public  void deleteUser(Long id){
        this.userRepository.deleteById(id);
}
public  List<HotelReviewHelper> getReviews(){
    return  userRepository.getHotelReview();

}
public  List<UserRoomHelper> getRooms(Long id){
        return  this.userRepository.getUserRooms(id);
}
public NrOfUsersHelpers getNrOfUsers(){
   return  userRepository.getNrOfUsers();
}
public  List<UsersByTypeHelper> getUsersByType(){
        return  userRepository.getUsersByType();
}
public  List<NrOfUserReservation> getNrOfUsersReservations(){
        return  userRepository.getUsersReservations();
}

public  UserEmailHelper getUser(String  email){
        return userRepository.findUserByEmail(email);
}
    public FindCodeHelper findUserCode(Long id) {
        return  userRepository.findUserCode(id);
    }

    public boolean existsByEmail(String email) {
        return  userRepository.existsByemail(email);
    }
}
