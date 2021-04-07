package licenta.backend.service;

import licenta.backend.model.Room;
import licenta.backend.model.RoomImages;
import licenta.backend.repository.RoomImageRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoomImageService {
@Resource
    RoomImageRepository roomImageRepository;
public List<RoomImages> finAll()
{
    return  roomImageRepository.findAll();
}
public  List<RoomImages> findImageById(int id)
{
    return  roomImageRepository.getRoomImage(id);
}
}
