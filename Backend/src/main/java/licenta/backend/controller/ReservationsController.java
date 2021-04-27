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
    RezervationService helperService;
    @Resource
    RoomReservationService roomReservationService;


    @PostMapping
    private ReservationHelper saveReservation(@RequestBody ReservationHelper helper) {
        return helperService.saveHelper(helper);
    }

    @PostMapping("/roomreservations")
    private RoomReservation createRoomRezervations(@RequestBody RoomReservation roomReservation) {
        return roomReservationService.save(roomReservation);
    }
}
