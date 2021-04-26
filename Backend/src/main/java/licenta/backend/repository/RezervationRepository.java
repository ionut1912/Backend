package licenta.backend.repository;

import licenta.backend.helpers.ReservationHelper;
import licenta.backend.model.Rezervation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.GeneratedValue;

@Repository
public interface RezervationRepository extends JpaRepository<Rezervation, Long> {
@Query(value = "insert into reservations VALUES(?1)",nativeQuery = true)
    ReservationHelper saveHelper(ReservationHelper helper);
}
