package licenta.backend.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "prices")

public class Price {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long priceid;

	@Column(name = "price")
	private double price;

	@Column(name = "type")
	private String type;

	@Column(name = "checkin")
	private Date checkin;

	@Column(name = "checkout")
	private Date checkout;

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
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
