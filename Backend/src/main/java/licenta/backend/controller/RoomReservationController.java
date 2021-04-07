package licenta.backend.controller;
import licenta.backend.exception.ResourceNotFoundException;
import licenta.backend.model.Price;
import licenta.backend.model.Room;
import licenta.backend.model.RoomReservation;
import licenta.backend.repository.RezervationRepository;
import licenta.backend.service.PriceService;
import licenta.backend.service.RezervationService;
import licenta.backend.service.RoomReservationService;
import licenta.backend.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/roomreservation")
public class RoomReservationController {

    @Resource
    RoomReservationService roomReservationService;





    public RoomReservationController(RoomReservationService roomReservationService, RoomService roomService, PriceService priceService, RezervationService rezervationService) {
        this.roomReservationService = roomReservationService;

    }
    @GetMapping
    public List<RoomReservation> getAll() {
        return roomReservationService.findAll();
    }
    @PostMapping
    public RoomReservation create(@RequestBody RoomReservation roomReservation) {
        return roomReservationService.save(roomReservation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomReservation> getRoomReservationById(@PathVariable Long id) {
        RoomReservation roomReservation = roomReservationService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rezervarea camerei cu id-ul " + id + " nu exista "));
        return ResponseEntity.ok(roomReservation);
    }



    @PutMapping("/{id}")
    public ResponseEntity<RoomReservation> updateRoomRezervation(@PathVariable Long id, @RequestBody RoomReservation roomReservationDetails) {
        RoomReservation roomReservation = roomReservationService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rezervarea camerei cu id-ul " + id + " nu exista "));
        roomReservation.setCheckin(roomReservation.getCheckin());
        roomReservation.setCheckout(roomReservation.getCheckout());
        RoomReservation updateroomreservation = roomReservationService.save(roomReservation);
        return ResponseEntity.ok(updateroomreservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRoomRezervationById(@PathVariable Long id) {

        RoomReservation roomReservation = roomReservationService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rezervarea camerei cu id-ul " + id + " nu exista "));
        roomReservationService.delete(roomReservation);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
