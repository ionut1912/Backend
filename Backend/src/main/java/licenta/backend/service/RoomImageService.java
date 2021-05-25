package licenta.backend.service;

import licenta.backend.model.RoomImages;
import licenta.backend.repository.RoomImageRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.Lob;
import java.util.List;
import java.util.Optional;

@Service
public class RoomImageService {
    @Resource
    RoomImageRepository roomImageRepository;

    public List<RoomImages> finAll() {
        return roomImageRepository.findAll();
    }

    public List<RoomImages> findImageById(int id) {
        return roomImageRepository.getRoomImage(id);
    }
    public  <T> Optional< RoomImages> findById(Long id) {
        return roomImageRepository.findById(id);
    }
    public  RoomImages save(RoomImages images){
        return  roomImageRepository.save(images);
    }
    public  void deleteImagebyId(Long id){
        this.roomImageRepository.deleteById(id);
    }
}
