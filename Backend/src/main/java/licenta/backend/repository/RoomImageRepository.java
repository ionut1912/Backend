package licenta.backend.repository;

import licenta.backend.model.RoomImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomImageRepository extends JpaRepository<RoomImages,Long> {
    @Query(value = "select * from roomimages where roomid=?1",nativeQuery = true)
    List<RoomImages> getRoomImage(int id);
}
