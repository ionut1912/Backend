package licenta.backend.repository;

import licenta.backend.helpers.RoomDetails;
import licenta.backend.model.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation,Long> {





}
