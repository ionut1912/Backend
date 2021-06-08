package licenta.backend.repository;

import licenta.backend.helpers.HotelReviewHelper;
import licenta.backend.helpers.UserData;
import licenta.backend.helpers.UserRoomHelper;
import licenta.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(value = "select name,email,userid,username from users where username=?1", nativeQuery = true)
    UserData getUserDetails(String username);
    @Query(value = "select hotelreview,name from users where hotelreview is not null ;",nativeQuery = true)
    List<HotelReviewHelper> getHotelReview();
    @Query(value = "select distinct roomid from reservations inner join roomreservations where userid=?1",nativeQuery = true)
      List<UserRoomHelper> getUserRooms(Long userid);
}
