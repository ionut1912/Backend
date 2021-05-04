package licenta.backend.helpers;

import java.util.Date;

public class RoomReservationHelper {
    public long roomid;
    public long priceid;
    public long reservationid;
    public Date checkin;
    public Date checkout;
    public int noofrooms;
    public int noofadults;
    public int noofchildrens;

    public long getRoomid() {
        return roomid;
    }

    public void setRoomid(long roomid) {
        this.roomid = roomid;
    }

    public long getPriceid() {
        return priceid;
    }

    public void setPriceid(long priceid) {
        this.priceid = priceid;
    }

    public long getReservationid() {
        return reservationid;
    }

    public void setReservationid(long reservationid) {
        this.reservationid = reservationid;
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
