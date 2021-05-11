package licenta.backend.controller;

import licenta.backend.helpers.ReservationHelper;
import licenta.backend.model.Rezervation;
import licenta.backend.model.Room;
import licenta.backend.model.RoomReservation;
import licenta.backend.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reservations")
public class ReservationsController {
    Logger logger = LoggerFactory.getLogger(ReservationsController.class);

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
    public void saveReservation(@RequestBody ReservationHelper helper) {

        Rezervation rezervation = new Rezervation(helper.getName(), helper.getEmail(), helper.getRoomtype(), helper.getCheckin(), helper.getCheckout(), helper.isDeleted(), service.getOneById(helper.getUserid()));
        RoomReservation roomReservation=new RoomReservation(roomService.getOneById(helper.getRoomid()),priceService.getOneById(helper.getPriceid()),helper.getCheckin(),helper.getCheckout(),helper.getNoofrooms(),helper.getNoofadults(),helper.getNoofchildrens());
        List<RoomReservation> reservations=new ArrayList<RoomReservation>();
        reservations.add(roomReservation);
        rezervation.setRoomReservations(reservations);
        roomReservation.setRezervation(rezervation);
        rezervationService.save(rezervation);
        roomReservationService.save(roomReservation);
    }
    @GetMapping
    public List<RoomReservation> getAll(){
        return  roomReservationService.findAll();
    }
   @GetMapping("/{id}")
    public  List<Rezervation> findUserById(@PathVariable  Long id){
        return  rezervationService.findByUserId(id);
}






}
