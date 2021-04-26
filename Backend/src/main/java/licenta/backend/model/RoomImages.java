package licenta.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

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
    @Column(name = "imagepath")
    private String imagepath;

    public RoomImages(int imageid, Room roomid, String roomimage) {
        this.imageid = imageid;
        this.roomforimage = roomid;
        this.imagepath = roomimage;
    }

    public RoomImages() {
    }

    public long getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    @JsonBackReference
    public Room getRoomid() {
        return roomforimage;
    }

    public void setRoomid(Room roomid) {
        this.roomforimage = roomid;
    }


    public void setRoomforimage(Room roomforimage) {
        this.roomforimage = roomforimage;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
}
