package licenta.backend.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "reservations")
public class  Rezervation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reservationid;

	@Column(name = "name")
	private String name;

	@Column(name = "phone")
	private String phone;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
}
