package licenta.backend.controller;


import licenta.backend.exception.ResourceNotFoundException;
import licenta.backend.helpers.RoomDetails;
import licenta.backend.model.Room;
import licenta.backend.model.RoomImages;
import licenta.backend.service.RoomImageService;
import licenta.backend.service.RoomReservationService;
import licenta.backend.service.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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


    @GetMapping("/{checkin}/{checkout}")
    public List<RoomDetails> roomInfo(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkin, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkout) {
        return roomService.getInfo(checkin, checkout);
    }

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
    public Room createRoom(@RequestBody Room room) {
        return roomService.save(room);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id,@RequestBody Room room) {
        Room room1=roomService.findById(id).orElseThrow(()->new ResourceNotFoundException("Camera cu id-ul " + id + " nu exista" ));
        room1.setName(room.getName());
        room1.setRoomtype(room.getRoomtype());
        room1.setRoomdetails(room.getRoomdetails());
        room1.setRoomprice(room.getRoomprice());
        room1.setPricecurency(room.getPricecurency());
        Room modifiedroom=roomService.save(room1);
        return  ResponseEntity.ok(modifiedroom);


    }
    @DeleteMapping("/{id}")
    public  void  deleteById(@PathVariable Long id){
        roomService.deleteRoombyId(id);
    }
}
