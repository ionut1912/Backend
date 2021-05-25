package licenta.backend.helpers;

import java.util.Date;

public class ReservationHelper {
    public  String  name;
    public  String email;
    public  String roomtype;
    public Date checkin;
    public  Date checkout;

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
