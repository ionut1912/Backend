package licenta.backend.repository;

import licenta.backend.helpers.RoomDetails;
import licenta.backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    @Query(value ="select  name,roomtype,roomdetails,rooms.roomid,checkin,checkout from roomreservations inner join rooms on roomreservations.roomid=rooms.roomid where checkin != ?1 and checkout != ?2", nativeQuery = true)
    List<RoomDetails> getRoomInfo(Date checkin, Date checkout);


}

