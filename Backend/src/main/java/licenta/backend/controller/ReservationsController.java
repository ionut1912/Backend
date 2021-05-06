package licenta.backend.controller;

import licenta.backend.helpers.ReservationHelper;
import licenta.backend.helpers.RoomReservationHelper;
import licenta.backend.model.Rezervation;
import licenta.backend.model.Room;
import licenta.backend.model.RoomReservation;
import licenta.backend.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reservations")
public class ReservationsController {
    @Resource
    RezervationService rezervationService;
    @Resource
    UserService service;
    @Resource
    RoomReservationService roomReservationService;
    @Resource
    RoomService roomService;
    @Resource
    PriceService priceService;

    @PostMapping
    public void saveReservation(@RequestBody ReservationHelper helper,@RequestBody RoomReservationHelper roomReservationHelper) {

        Rezervation rezervation = new Rezervation(helper.getName(), helper.getEmail(), helper.getRoomtype(), helper.getCheckin(), helper.getCheckout(), helper.isDeleted(), service.getOneById(helper.getUserid()));
        RoomReservation roomReservation=new RoomReservation(roomService.getOneById(roomReservationHelper.getRoomid()),priceService.getOneById(roomReservationHelper.getPriceid()),roomReservationHelper.getCheckin(),roomReservationHelper.getCheckout(),roomReservationHelper.getNoofrooms(),roomReservationHelper.getNoofadults(),roomReservationHelper.getNoofchildrens());
        roomReservation.setRezervation(rezervation);
        rezervation.setRoomReservations(Collections.singletonList(roomReservation));
        rezervationService.save(rezervation);
        roomReservationService.save(roomReservation);
    }




}
