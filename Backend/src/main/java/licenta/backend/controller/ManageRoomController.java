package licenta.backend.controller;


import licenta.backend.exception.ResourceNotFoundException;
import licenta.backend.helpers.RoomDetails;
import licenta.backend.helpers.RoomImagesHelper;
import licenta.backend.model.Room;
import licenta.backend.model.RoomImages;
import licenta.backend.payload.response.ResponseMessage;
import licenta.backend.service.RoomImageService;
import licenta.backend.service.RoomReservationService;
import licenta.backend.service.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

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
    public ResponseEntity<ResponseMessage> createRoom(@RequestParam RoomImagesHelper roomHelper, @RequestParam MultipartFile[] files) throws IOException {
       
String message=" ";
       try{
           Room room=new Room(roomHelper.getName(),roomHelper.getRoomtype(),roomHelper.getRoomdetails(),roomHelper.getRoomprice(),roomHelper.getPricecurency());
           Arrays.asList(files).stream().forEach(file -> {
               RoomImages images= null;
               try {
                   images = new RoomImages(file.getOriginalFilename(),file.getContentType(),compressBytes(file.getBytes()));
                   List <RoomImages> imagesList= new ArrayList<>();
                   imagesList.add(images);
                   room.setImages(imagesList);
                   images.setRoomforimage(room);
                   roomService.save(room);
                   roomImageService.save(images);
               } catch (IOException e) {
                   e.printStackTrace();
               }

           });
           message = "Uploaded the files successfully";

           return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
       }
       catch (Exception e) {
           message = "Fail to upload files!";
           return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
       }


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
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }

        return outputStream.toByteArray();
    }
    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch ( DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

}
