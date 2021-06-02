package licenta.backend.repository;

import licenta.backend.helpers.RoomDetails;
import licenta.backend.helpers.TotalPrice;
import licenta.backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query(value = "select distinct  name,roomtype,roomdetails,rooms.roomid,roomprice,pricecurency from rooms inner join roomreservations  on roomreservations.roomid=rooms.roomid where ((checkin<?1 and checkout<?1) or (checkin >?2 and checkout>?2))", nativeQuery = true)
    List<RoomDetails> getRoomInfo(Date checkin, Date checkout);
@Query(value = "select  name,roomtype,roomdetails,roomid,roomprice,pricecurency from rooms  where rooms.roomid not in(select roomid from roomreservations)",nativeQuery = true)
List<RoomDetails> getRommInfoNotRezerved();
@Query(value = "select datediff(?2,?1)*roomprice as finalprice,pricecurency from rooms where roomid=?3",nativeQuery = true)
    TotalPrice getPrice(Date checkin,Date checkout,Long roomid);
}

