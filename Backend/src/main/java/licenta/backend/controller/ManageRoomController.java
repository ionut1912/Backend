package licenta.backend.controller;


import licenta.backend.exception.ResourceNotFoundException;
import licenta.backend.helpers.*;
import licenta.backend.model.Room;
import licenta.backend.model.RoomImages;
import licenta.backend.service.*;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/room")
public class ManageRoomController {
    @Resource
    RoomImageService roomImageService;
    @Resource
    RoomService roomService;
    @Resource
    RoomReservationService roomReservationService;

    @Resource
    UserService userService;

    @GetMapping("/{checkin}/{checkout}")
    public List<RoomDetails> roomInfo(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkin, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkout) {
        return roomService.getInfo(checkin, checkout);
    }

    @GetMapping("/details")
    public List<RoomDetails> getRoomsInformations() {
        return roomService.getRoomsDetails();
    }

    @GetMapping("/{checkin}/{checkout}/{id}")
    public TotalPrice getPrice(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkin, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkout, @PathVariable Long id) {
        return this.roomService.getPrice(checkin, checkout, id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<Room> getAll() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Room room = roomService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Camera cu id-ul " + id + " nu exista "));
        return ResponseEntity.ok(room);
    }

    @GetMapping("/images")
    public List<RoomImages> fiindAll() {
        return roomImageService.finAll();
    }

    @GetMapping("/images/{id}")
    public List<RoomImages> findById(@PathVariable int id) {
        return roomImageService.findImageById(id);
    }

    @PostMapping
    public void createRoom(@RequestBody RoomHelper roomHelper) {
        Room room = new Room(roomHelper.getName(), roomHelper.getRoomtype(), roomHelper.getRoomdetails(), roomHelper.getRoomprice(), roomHelper.getPricecurency());
        for (int i = 0; i < roomHelper.getImagepath().length; i++) {
            RoomImages roomImages = new RoomImages(roomHelper.getImagepath()[i]);
            List<RoomImages> images = new ArrayList<RoomImages>();
            images.add(roomImages);
            room.setImages(images);
            roomImages.setRoomforimage(room);

            roomImageService.save(roomImages);

        }

        roomService.save(room);


    }

    @PatchMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody RoomHelper room) {
        Room room1 = roomService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Camera cu id-ul " + id + " nu exista"));

        room1.setName(room.getName());
        room1.setRoomtype(room.getRoomtype());
        room1.setRoomdetails(room.getRoomdetails());
        room1.setRoomprice(room.getRoomprice());
        room1.setPricecurency(room.getPricecurency());
        Room modifiedroom = roomService.save(room1);
        return ResponseEntity.ok(modifiedroom);


    }

    @PatchMapping("/images/{id}")
    public ResponseEntity<RoomImages> updateImage(@PathVariable Long id, @RequestBody ImageHelper images) {
        RoomImages roomImages1 = roomImageService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Imaginea cu id-ul" + id + " nu exista"));
        for (int i = 0; i < images.getImagepath().length; i++) {
            roomImages1.setImagepath(images.getImagepath()[i]);


        }
        RoomImages modifiedImage = roomImageService.save(roomImages1);
        return ResponseEntity.ok(modifiedImage);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        roomService.deleteRoombyId(id);
    }

@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/nrofrooms")
    public NrOfRoomsHelper getNrOfRooms() {
        return roomService.getNrOfRooms();

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/nrroomsbytype")
    public List<NrRoomsByType> getNrOfRoomsByType() {
        return roomService.getNrRoomsByType();

    }

    @GetMapping("/roomsbytype/{roomtype}/{checkin}/{checkout}")
    public FreeRoomsByTypeHelper getFreeRoomsByTypeAfterReservation(@PathVariable String roomtype, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkin, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkout) {
        return roomService.getNrOfFreeRoomsAfterRezervationByType(roomtype, checkin, checkout);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/freerooms/{roomtype}")
    public FreeRoomsByTypeHelper getFreeRoomsByType(@PathVariable String roomtype) {
        return roomService.getNrOfFreeRoomsByType(roomtype);
    }
}
