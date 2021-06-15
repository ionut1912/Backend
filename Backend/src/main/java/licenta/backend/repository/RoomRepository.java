package licenta.backend.repository;

import licenta.backend.helpers.*;
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

@Query(value = "select count(roomid) as nrofrooms from rooms",nativeQuery = true)
    NrOfRoomsHelper getNrOfRooms();
@Query(value = "select roomtype,count(roomid) as nrofroomsbytype from rooms group by roomtype",nativeQuery = true)

    List<NrRoomsByType> getNrRoomsByType();
@Query(value = "select count(roomid) as nroffreerooms from rooms where (roomtype=?1 and roomid in (select roomid from roomreservations where ((checkin<?2 and checkout<?2) or (checkin >?3 and checkout>?3))))",nativeQuery = true)
FreeRoomsByTypeHelper getNrOfFreeRoomsAfterReservationByType(String roomtype, Date checkin, Date checkout);
@Query(value = "select count(roomid) as nroffreerooms from rooms where (roomtype=?1 and roomid in (select roomid from rooms where roomid not in(select roomid from roomreservations)))",nativeQuery = true)
 FreeRoomsByTypeHelper getNrOfFreeRoomsByType(String  roomtype);
}


