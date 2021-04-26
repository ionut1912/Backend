package licenta.backend.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "roomreservations")

public class RoomReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long roomreservationsid;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roomid")
	@JsonBackReference("roomReservation")
	private Room room;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "priceid")
	@JsonBackReference("price-roomReservation")
	private Price price;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reservationid")
	@JsonBackReference("reservation-roomReservation")
	private Rezervation rezervation;

	@Column(name = "checkin")
	private Date checkin;

	@Column(name = "checkout")
	private Date checkout;

	@Column(name = "noofrooms")
	private int noofrooms;


	@Column(name = "noofadults")
	private int noofadults;

	@Column(name = "noofchildrens")
	private int noofchildrens;



	public long getRoomreservationsid() {
		return roomreservationsid;
	}

	public void setRoomreservationsid(long roomreservationsid) {
		this.roomreservationsid = roomreservationsid;
	}
@JsonBackReference
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
@JsonBackReference
	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}
@JsonBackReference
	public Rezervation getRezervation() {
		return rezervation;
	}

	public void setRezervation(Rezervation rezervation) {
		this.rezervation = rezervation;
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