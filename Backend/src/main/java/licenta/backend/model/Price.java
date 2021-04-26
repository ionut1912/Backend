package licenta.backend.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "prices")

public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long priceid;

    @Column(name = "price")
    private double prices;

    @Column(name = "type")
    private String type;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "price")
    @JsonManagedReference("price-roomReservation")
    private List<RoomReservation> roomReservationList;

    public long getPriceid() {
        return priceid;
    }

    public void setPriceid(long priceid) {
        this.priceid = priceid;
    }

    public double getPrice() {
        return prices;
    }

    public void setPrice(double price) {
        this.prices = price;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonManagedReference
    public List<RoomReservation> getRoomReservationList() {
        return roomReservationList;
    }

    public void setRoomReservationList(List<RoomReservation> roomReservationList) {
        this.roomReservationList = roomReservationList;
    }

}
