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
    @Column(name = "name")
    private  String name;
    @Column(name="type")
    private  String type;

    @Column(name = "imagepath",length = 30000)
    private byte[] imagepath;

    public RoomImages(String name, String type, byte[] imagepath) {
        this.name = name;
        this.type = type;
        this.imagepath = imagepath;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImagepath() {
        return imagepath;
    }

    public void setImagepath(byte[] imagepath) {
        this.imagepath = imagepath;
    }
}
