package licenta.backend.repository;

import licenta.backend.helpers.*;
import licenta.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);
      User findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query(value = "select name,email,userid,username from users where username=?1", nativeQuery = true)
    UserData getUserDetails(String username);

    @Query(value = "select hotelreview,name from users where hotelreview is not null ;", nativeQuery = true)
    List<HotelReviewHelper> getHotelReview();

    @Query(value = "select distinct roomid from reservations inner join roomreservations where userid=?1", nativeQuery = true)
    List<UserRoomHelper> getUserRooms(Long userid);

    @Query(value = "select count(userid) as nrofusers from users", nativeQuery = true)
    NrOfUsersHelpers getNrOfUsers();

    @Query(value = "select type, count(userid) as nrofusersbytype from users  group by type", nativeQuery = true)
    List<UsersByTypeHelper> getUsersByType();

    @Query(value = "select username,count(users.userid) as nrofuserreservations from users inner join reservations on reservations.userid=users.userid group by username", nativeQuery = true)
    List<NrOfUserReservation> getUsersReservations();
@Query(value = "select userid from users where email=?1",nativeQuery = true)
UserCodeHelper findUserByEmail(String  email);
@Query(value = "select usercode from users  where userid=?1",nativeQuery = true)
    FindCodeHelper findUserCode(Long userid);
}
