package licenta.backend.repository;

import licenta.backend.helpers.FreeRoomsByType;
import licenta.backend.helpers.NrOfReservationsHelper;
import licenta.backend.helpers.ReservationsByType;
import licenta.backend.model.RoomReservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
@Query(value = "select count(roomreservationsid) as nrofreservations from roomreservations",nativeQuery = true)
    NrOfReservationsHelper  getNrOfReservations();
@Query(value = "select roomtype,count(roomreservationsid) as nrofreservations from roomreservations inner join rooms on roomreservations.roomid=rooms.roomid group by roomtype",nativeQuery = true)
    List<ReservationsByType> getReservationsByType();
    @Query(value = "select roomtype,count(roomid) as nroffreeromsbytype  from rooms where roomid not in (select roomid from roomreservations) group by roomtype",nativeQuery = true)
    List<FreeRoomsByType> getRoomFreeByType();
}
