package licenta.backend.helpers;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class RoomDetail {
    public String name;
    public String roomtype;
    public String roomdetails;
    public int roomid;
    @NotNull
    public Date checkin;
    @NotNull
    public  Date checkout;

    public RoomDetail(String name, String roomtype, String roomdetails, int id, Date checkin, Date checkout) {
        this.name = name;
        this.roomtype = roomtype;
        this.roomdetails = roomdetails;
        this.roomid=id;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getRoomdetails() {
        return roomdetails;
    }

    public void setRoomdetails(String roomdetails) {
        this.roomdetails = roomdetails;
    }

    public int getRoomimage() {
        return roomid;
    }

    public void setRoomimage(int id) {
        this.roomid = id;
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
}
