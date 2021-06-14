package licenta.backend.helpers;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public class ReservationHelper {
    public  String  name;
    public  String email;
    public  String roomtype;

    public LocalDate checkin;

    public  LocalDate checkout;

    public  boolean deleted;
    public  int userid;
    public  int roomid;

    public  int noofrooms;
    public int noofadults;
    public  int noofchildrens;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public int getNoofrooms() {
        return noofrooms;
    }

    public void setNoofrooms(int noofrooms) {
        this.noofrooms = noofrooms;
    }

    public int getNoofadults() {
        return noofadults;
    }

    public void setNoofadults(int noofadults) {
        this.noofadults = noofadults;
    }

    public int getNoofchildrens() {
        return noofchildrens;
    }

    public void setNoofchildrens(int noofchildrens) {
        this.noofchildrens = noofchildrens;
    }

}
