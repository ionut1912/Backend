package licenta.backend.service;

import licenta.backend.helpers.RoomDetails;
import licenta.backend.helpers.TotalPrice;
import licenta.backend.model.Room;
import licenta.backend.repository.RoomRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomService {
    @Resource
    public RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {
        return roomRepository.findAll();

    }

    public List<RoomDetails> getInfo(Date checkin, Date checkout) {
        return roomRepository.getRoomInfo(checkin, checkout);
    }

    public Room save(Room room) {

        return roomRepository.save(room);
    }

    public <T> Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }


public  Room getOneById(long id){
        return  roomRepository.getOne(id);
}
public  void deleteRoombyId(Long id){
    this.roomRepository.deleteById(id);
}
public  List<RoomDetails> getRoomsDetails(){
        return  roomRepository.getRommInfoNotRezerved();
}
public TotalPrice getPrice(Date checkin,Date checkout,Long roomid){
        return  roomRepository.getPrice(checkin,checkout,roomid);
}
}





