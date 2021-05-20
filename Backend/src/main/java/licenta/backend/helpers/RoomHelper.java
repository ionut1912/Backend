package licenta.backend.helpers;

import java.util.List;

public class RoomHelper {
    public String name;
    public String roomtype;
    public String roomdetails;
    public float roomprice;
    public String pricecurency;
   public String[] imagepath;
    public String getName() {
        return name;
    }


    public String[] getImagepath() {
        return imagepath;
    }

    public void setImagepath(String[] imagepath) {
        this.imagepath = imagepath;
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
