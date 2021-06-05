package licenta.backend.service;

import licenta.backend.helpers.NrOfVIewsHelper;
import licenta.backend.helpers.RoomViewedHelper;
import licenta.backend.model.RoomViewed;
import licenta.backend.repository.RoomViewedRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class RoomViewedService {
@Resource
    RoomViewedRepository roomViewedRepository;
public RoomViewed save(RoomViewed roomViewed){
 return    this.roomViewedRepository.save(roomViewed);
}
public <T> Optional<RoomViewed> fiindAllById(Long id){
    return  this.roomViewedRepository.findById(id);
}
    public NrOfVIewsHelper getNrOfViews(Long id){
        return  this.roomViewedRepository.getNrOfViews(id);
    }
}

