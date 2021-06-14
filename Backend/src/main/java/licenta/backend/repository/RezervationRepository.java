package licenta.backend.repository;

import licenta.backend.helpers.UserReservationHelper;
import licenta.backend.model.Rezervation;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;

@Repository
public interface RezervationRepository extends JpaRepository<Rezervation, Long> {

    @Query(value = "SELECT reservations.reservationid, name,email,roomtype,reservations.checkin,reservations.checkout,deleted,userid  ,roomid FROM licenta.reservations inner join roomreservations on reservations.reservationid= roomreservations.reservationid where userid=?1",nativeQuery = true)
    List<UserReservationHelper> findRezervationByuserid(Long userId);
@Query(value = "select * from reservations  where checkin=?1",nativeQuery = true)
    List<Rezervation>  findCheckin(LocalDate checkin);
}
