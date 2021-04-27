package licenta.backend.repository;

import licenta.backend.model.Rezervation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RezervationRepository extends JpaRepository<Rezervation, Long> {


}
