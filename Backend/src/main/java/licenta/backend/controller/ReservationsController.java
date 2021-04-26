package licenta.backend.controller;

import licenta.backend.helpers.ReservationHelper;
import licenta.backend.model.RoomReservation;
import licenta.backend.service.RezervationService;
import licenta.backend.service.RoomReservationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reservations")
public class ReservationsController {
@Resource
    RezervationService rezervationService;
    @Resource
    RoomReservationService roomReservationService;


@PostMapping
private ReservationHelper saveReservatopm(@RequestBody ReservationHelper helper){
   return rezervationService.save(helper);
}

    @PostMapping("/roomreservations")
    private RoomReservation createRoomRezervations(@RequestBody RoomReservation roomReservation) {
        return roomReservationService.save(roomReservation);
    }
}
