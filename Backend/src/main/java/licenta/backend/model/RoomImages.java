package licenta.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

@Entity
@Table(name = "roomimages")

public class RoomImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roomid")
    @JsonBackReference("room")
    private Room roomforimage;

    @Lob
    @Column(name = "imagepath",length = 30000)
    private String[] imagepath;

    public RoomImages() {
    }

    public RoomImages(String[] imagepath) {
    }





    public long getImageid() {
        return imageid;
    }

    public void setImageid(long imageid) {
        this.imageid = imageid;
    }

    public Room getRoomforimage() {
        return roomforimage;
    }

    public void setRoomforimage(Room roomforimage) {
        this.roomforimage = roomforimage;
    }




    public String[] getImagepath() {
        return imagepath;
    }

    public void setImagepath(String[] imagepath) {
        this.imagepath = imagepath;
    }
}
