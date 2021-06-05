package licenta.backend.repository;

import licenta.backend.helpers.NrOfVIewsHelper;
import licenta.backend.model.RoomViewed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomViewedRepository  extends JpaRepository<RoomViewed,Long> {
    @Query(value = "select count(*) as nrvizualizari from roomsviewed where roomid=?1",nativeQuery = true)
    NrOfVIewsHelper getNrOfViews(Long roomid);
}
