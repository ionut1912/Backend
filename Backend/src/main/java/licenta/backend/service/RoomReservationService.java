package licenta.backend.service;

import licenta.backend.helpers.FreeRoomsByType;
import licenta.backend.helpers.NrOfReservationsHelper;
import licenta.backend.helpers.ReservationsByType;
import licenta.backend.model.RoomReservation;
import licenta.backend.repository.RoomReservationRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

;

@Service
@Transactional
public class RoomReservationService {
    @Resource
    private RoomReservationRepository roomReservationRepository;

    public RoomReservationService(RoomReservationRepository roomReservationRepository) {
        this.roomReservationRepository = roomReservationRepository;
    }


    public List<RoomReservation> findAll() {
        return roomReservationRepository.findAll();

    }

    public RoomReservation save(RoomReservation roomReservation) {

        return roomReservationRepository.save(roomReservation);
    }

    public <T> Optional<RoomReservation> findById(Long id) {
        return roomReservationRepository.findById(id);
    }

    public void delete(RoomReservation roomReservation) {
        roomReservationRepository.delete(roomReservation);
    }
    public  NrOfReservationsHelper getNrOfReservations()
    {
        return  roomReservationRepository.getNrOfReservations();
    }
    public  List<ReservationsByType> getNrOfReservationsByType(){
       return   roomReservationRepository.getReservationsByType();
    }
    public  List<FreeRoomsByType> getNrOfFreeReservationsByType(){
        return  roomReservationRepository.getRoomFreeByType();
    }
    }





