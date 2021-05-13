package licenta.backend.helpers;

import java.util.Date;

public class ReservationHelper {
    public  String  name;
    public  String email;
    public  String roomtype;
    public Date checkin;
    public  Date checkout;

    public  boolean deleted;
    public  int userId;
    public  int roomId;
    public  int priceId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }
}
