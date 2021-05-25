package licenta.backend.controller;

import licenta.backend.exception.ResourceNotFoundException;
import licenta.backend.helpers.ReservationHelper;
import licenta.backend.model.Rezervation;
import licenta.backend.model.Room;
import licenta.backend.model.RoomReservation;
import licenta.backend.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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


    @PostMapping
    public void saveReservation(@RequestBody ReservationHelper helper) {

        Rezervation rezervation = new Rezervation(helper.getName(), helper.getEmail(), helper.getRoomtype(), helper.getCheckin(), helper.getCheckout(), helper.isDeleted(), service.getOneById(helper.getUserid()));
        RoomReservation roomReservation=new RoomReservation(roomService.getOneById(helper.getRoomid()),helper.getCheckin(),helper.getCheckout(),helper.getNoofrooms(),helper.getNoofadults(),helper.getNoofchildrens());
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
    @GetMapping("/all")
    public List<Rezervation> getAllRezervations(){
        return rezervationService.findAll();
    }
   @GetMapping("/{id}")
    public  List<Rezervation> findUserById(@PathVariable  Long id){
        return  rezervationService.findByUserId(id);
}

@PatchMapping("/{id}")
    public ResponseEntity<Rezervation> updateRezervation(@PathVariable Long id ,@RequestBody Rezervation rezervation){
    Rezervation rezervation1=rezervationService.findById(id).orElseThrow(()->new ResourceNotFoundException("Rezervarea cu id-ul " + id + " nu exista" ));
    rezervation1.setName(rezervation.getName());
    rezervation1.setEmail(rezervation.getEmail());
    rezervation1.setRoomtype(rezervation.getRoomtype());
rezervation1.setCheckin(rezervation.getCheckin());
rezervation1.setCheckout(rezervation.getCheckout());
    Rezervation modifiedrezervation=rezervationService.save(rezervation1);
    return  ResponseEntity.ok(modifiedrezervation);
}
@PatchMapping("/delete/{id}")
    public  ResponseEntity<Rezervation> deleteRezervation(@PathVariable Long id,@RequestBody ReservationHelper rezervation){
    Rezervation rezervation1=rezervationService.findById(id).orElseThrow(()->new ResourceNotFoundException("Rezervarea cu id-ul " + id + " nu exista" ));
    rezervation1.setDeleted(true);
    Rezervation modifiedrezervation=rezervationService.save(rezervation1);
    return  ResponseEntity.ok(modifiedrezervation);
}





}
