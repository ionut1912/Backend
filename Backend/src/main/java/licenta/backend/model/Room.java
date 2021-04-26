package licenta.backend.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roomid;

    @Column(name = "name")
    private String name;

    @Column(name = "roomtype")
    private String roomtype;

    @Column(name = "roomdetails")
    private String roomdetails;
    @Column(name = "roomstatus")
    private String roomstatus;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomforimage")
    @JsonManagedReference("room")
    private List<RoomImages> images;
    @JsonIgnore
    @JsonManagedReference("roomReservation")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")

    private List<RoomReservation> roomReservations;
    @JsonIgnore
    @JsonManagedReference("room-review")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomreview")
    private List<UserReview> reviewList;


    public long getRoomid() {
        return roomid;
    }

    public void setRoomid(long roomid) {
        this.roomid = roomid;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoomReservation> getRoomReservations() {
        return roomReservations;
    }

    public void setRoomReservations(List<RoomReservation> roomReservations) {
        this.roomReservations = roomReservations;
    }

    public String getRoomdetails() {
        return roomdetails;
    }

    public void setRoomdetails(String roomdetails) {
        this.roomdetails = roomdetails;
    }


    public void setRoomstatus(String roomstatus) {
        this.roomstatus = roomstatus;
    }

    public String getRoomstatus() {
        return roomstatus;
    }

    @JsonManagedReference
    public List<RoomImages> getImages() {
        return images;
    }

    public void setImages(List<RoomImages> images) {
        this.images = images;
    }

    @JsonManagedReference
    public List<UserReview> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<UserReview> reviewList) {
        this.reviewList = reviewList;
    }
}
