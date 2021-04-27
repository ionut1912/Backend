package licenta.backend.repository;

import licenta.backend.helpers.ReservationHelper;
import licenta.backend.model.Rezervation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RezervationRepository extends JpaRepository<Rezervation, Long> {
@Modifying
@Query(value = "insert into reservations(name,email,roomtype,checkin,checkout,deleted,userid) values(?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    ReservationHelper saveReservation(String name, String email, String roomtype, Date checkin,Date checkout,boolean deleted,Long userid);
}
