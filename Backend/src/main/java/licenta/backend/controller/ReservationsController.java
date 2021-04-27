package licenta.backend.controller;

import licenta.backend.helpers.ReservationHelper;
import licenta.backend.model.RoomReservation;
import licenta.backend.service.RezervationService;
import licenta.backend.service.RoomReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reservations")
public class ReservationsController {
    @Resource
    RezervationService helperService;
    @Resource
    RoomReservationService roomReservationService;

@PostMapping

public ReservationHelper save(@RequestBody String name, @RequestBody String email, @RequestBody String roomtype, @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkin,@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkout,@RequestBody boolean deleted ,@RequestBody Long userid ){

return  helperService.saveReservation(name,email,roomtype,checkin,checkout,deleted,userid);
}



    @PostMapping("/roomreservations")
    private RoomReservation createRoomRezervations(@RequestBody RoomReservation roomReservation) {
        return roomReservationService.save(roomReservation);
    }
}
