package licenta.backend.service;

import licenta.backend.helpers.RoomDetails;
import licenta.backend.model.Room;
import licenta.backend.repository.RoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.util.Collection;
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
    public List<Room> findAll(){
        return roomRepository.findAll();

    }
    public  List<RoomDetails> getInfo(Date checkin, Date checkout) {
        return roomRepository.getRoomInfo(checkin, checkout);
    }

    public Room save(Room room){

        return roomRepository.save(room);
    }
    public <T> Optional<Room> findById(Long id){
        return  roomRepository.findById(id);
    }
    public  void delete(Room room){
        roomRepository.delete(room);
    }


    }





