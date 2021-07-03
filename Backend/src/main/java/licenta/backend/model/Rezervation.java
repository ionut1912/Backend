package licenta.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity

@Table(name = "reservations")
public class Rezervation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationid;

    @Column(name = "name")
    private String name;



    @Column(name = "email")
    private String email;

    @Column(name = "roomtype")
    private String roomtype;

    @Column(name = "checkin")
    private Date checkin;

    @Column(name = "checkout")
    private Date checkout;


    @Column(name = "deleted")
    private boolean deleted;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    @JsonBackReference("user-reservation")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rezervation")
    @JsonManagedReference("reservation-roomReservation")
    private List<RoomReservation> roomReservations;

    public Rezervation(String name, String email, String roomtype, Date checkin, Date checkout, boolean deleted, User user) {
        this.name = name;
        this.email = email;
        this.roomtype = roomtype;
        this.checkin = checkin;
        this.checkout = checkout;
        this.deleted = deleted;
        this.user = user;
    }

    public Rezervation() {
    }




    public long getRezervationid() {
        return reservationid;
    }

    public void setRezervationid(long rezervationid) {
        this.reservationid = rezervationid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @JsonBackReference
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonManagedReference
    public List<RoomReservation> getRoomReservations() {
        return roomReservations;
    }

    public void setRoomReservations(List<RoomReservation> roomReservations) {
        this.roomReservations = roomReservations;
    }

    @Override
    public String toString() {
        return "Rezervation{" +
                "reservationid=" + reservationid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", roomtype='" + roomtype + '\'' +
                ", checkin=" + checkin +
                ", checkout=" + checkout +
                ", deleted=" + deleted +
                ", user=" + user +
                ", roomReservations=" + roomReservations +
                '}';
    }
}
