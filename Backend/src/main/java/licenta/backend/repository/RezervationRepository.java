package licenta.backend.repository;

import licenta.backend.model.Rezervation;
import licenta.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RezervationRepository extends JpaRepository<Rezervation, Long> {
@Query(value = "SELECT * FROM licenta.reservations where userid=?1",nativeQuery = true)
    List<Rezervation> findRezervationByuserid(Long userId);

}
