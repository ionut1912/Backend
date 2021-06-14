package licenta.backend.repository;

import licenta.backend.model.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
    Boolean existsByuseremail(String email);
}
