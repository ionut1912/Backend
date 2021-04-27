package licenta.backend.controller;

import licenta.backend.helpers.ReservationHelper;
import licenta.backend.model.Rezervation;
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
RezervationService rezervationService;
@PostMapping
    public Rezervation saveReservation(@RequestBody ReservationHelper helper)
{
    Rezervation rezervation=new Rezervation(helper.getName(),helper.getEmail(),helper.getRoomtype(),helper.getCheckin(),helper.getCheckout(),helper.isDeleted(),helper.getUser());
    return  rezervationService.save(rezervation);
}





}
