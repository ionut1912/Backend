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
    @Column(name = "roomprice")
    private  float roomprice;
    @Column(name = "pricecurency")
    private String pricecurency;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomforimage")
    @JsonManagedReference("room")
    private List<RoomImages> images;
    @JsonIgnore
    @JsonManagedReference("roomReservation")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")

    private List<RoomReservation> roomReservations;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomreview")
    @JsonManagedReference("roomreviews")
    private List<UserReview> reviews;

    public List<UserReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<UserReview> reviews) {
        this.reviews = reviews;
    }

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


    public Room(String name, String roomtype, String roomdetails, float roomprice, String pricecurency) {
        this.name = name;
        this.roomtype = roomtype;
        this.roomdetails = roomdetails;
        this.roomprice = roomprice;
        this.pricecurency = pricecurency;
    }

    @JsonManagedReference
    public List<RoomImages> getImages() {
        return images;
    }

    public void setImages(List<RoomImages> images) {
        this.images = images;
    }

    public float getRoomprice() {
        return roomprice;
    }

    public void setRoomprice(float roomprice) {
        this.roomprice = roomprice;
    }

    public String getPricecurency() {
        return pricecurency;
    }

    public void setPricecurency(String pricecurency) {
        this.pricecurency = pricecurency;
    }
}
