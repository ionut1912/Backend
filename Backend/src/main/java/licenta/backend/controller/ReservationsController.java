package licenta.backend.controller;

import licenta.backend.exception.ResourceNotFoundException;
import licenta.backend.helpers.*;
import licenta.backend.model.Rezervation;
import licenta.backend.model.RoomReservation;
import licenta.backend.payload.response.MessageResponse;
import licenta.backend.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    EmailService emailService;

    @PostMapping
    public void saveReservation(@RequestBody ReservationHelper helper) {

        Rezervation rezervation = new Rezervation(helper.getName(), helper.getEmail(), helper.getRoomtype(), helper.getCheckin(), helper.getCheckout(), helper.isDeleted(), service.getOneById(helper.getUserid()));
        RoomReservation roomReservation = new RoomReservation(roomService.getOneById(helper.getRoomid()), helper.getCheckin(), helper.getCheckout(), helper.getNoofrooms(), helper.getNoofadults(), helper.getNoofchildrens());
        List<RoomReservation> reservations = new ArrayList<RoomReservation>();
        reservations.add(roomReservation);
        rezervation.setRoomReservations(reservations);

        roomReservation.setRezervation(rezervation);

        emailService.sendMail(helper.getEmail(), "Rezervarea a fost realizata", "Buna ziua! Rezervarea dumneavoastra a fost creata cu succes pe data de " + helper.getCheckin() + " . Va asteptam!");

        rezervationService.save(rezervation);
        roomReservationService.save(roomReservation);
    }

    @GetMapping
    public List<RoomReservation> getAll() {
        return roomReservationService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<Rezervation> getAllRezervations() {
        return rezervationService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public List<UserReservationHelper> findUserById(@PathVariable Long id) {
        return rezervationService.findByUserId(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRezervation(@PathVariable Long id, @RequestBody ReservationHelper rezervation) {
       List<AvailableRoomsHelper> availableRoomsHelpers=roomService.availableRooms(id);
        Rezervation rezervation1 = rezervationService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rezervarea cu id-ul " + id + " nu exista"));
       if((availableRoomsHelpers.size()>1)&&(!((rezervation1.getCheckin().compareTo(rezervation.getCheckin())<0 && rezervation1.getCheckout().compareTo(rezervation.getCheckin())<0)||(rezervation1.getCheckin().compareTo(rezervation.getCheckout())>0 && rezervation1.getCheckout().compareTo(rezervation.getCheckout())>0))))
           return ResponseEntity.badRequest().body(new MessageResponse("Camera nu este disponibila in perioada selectata"));
        rezervation1.setName(rezervation.getName());
        rezervation1.setEmail(rezervation.getEmail());
        rezervation1.setRoomtype(rezervation.getRoomtype());
        rezervation1.setCheckin(rezervation.getCheckin());
        rezervation1.setCheckout(rezervation.getCheckout());
        Rezervation modifiedrezervation = rezervationService.save(rezervation1);
        return ResponseEntity.ok(modifiedrezervation);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping("/delete/{id}")
    public ResponseEntity<Rezervation> deleteRezervation(@PathVariable Long id, @RequestBody ReservationHelper rezervation) {
        Rezervation rezervation1 = rezervationService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rezervarea cu id-ul " + id + " nu exista"));
        rezervation1.setDeleted(true);
        Rezervation modifiedrezervation = rezervationService.save(rezervation1);
        return ResponseEntity.ok(modifiedrezervation);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/nrofreservations")
    public NrOfReservationsHelper getNrOfReservations() {
        return roomReservationService.getNrOfReservations();
    }

    @GetMapping("/roomsbytype")
    public List<ReservationsByType> getReservationsByType() {
        return roomReservationService.getNrOfReservationsByType();
    }

    @GetMapping("/freeroomsbytype")
    public List<FreeRoomsByType> getfreeReservationsByType() {
        return roomReservationService.getNrOfFreeReservationsByType();
    }


}
