package licenta.backend.repository;

import licenta.backend.model.RoomImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomImageRepository extends JpaRepository<RoomImages, Long> {
    @Query(value = "select * from roomimages where roomid=?1", nativeQuery = true)
    List<RoomImages> getRoomImage(int id);
}
