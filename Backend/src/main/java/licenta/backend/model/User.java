package licenta.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")

public class User implements UserDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Erole type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference("user-reservation")
    private List<Rezervation> rezervations;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userreviews")
    @JsonManagedReference("user-review")
    private List<UserReview> reviews;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userroomviewed")
    @JsonManagedReference("userroomviewed")
    private List<RoomViewed>  roomsviewed;
    private boolean enabled;

    @Column(name = "hotelreview")
    private String hotelreview;
   @Column(name = "usercode")
   private  String usercode;



    public User() {
    }
    @JsonManagedReference
    public List<RoomViewed> getRoomsviewed() {
        return roomsviewed;
    }

    public void setRoomsviewed(List<RoomViewed> roomsviewed) {
        this.roomsviewed = roomsviewed;
    }

    public User(String name, String email, String username) {
        this.name = name;
        this.email = email;
        this.username = username;
    }

    public User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String name, String email, String username, String password, Erole type, boolean enabled) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.type = type;
        this.enabled = enabled;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

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

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonManagedReference
    public List<Rezervation> getRezervations() {
        return rezervations;
    }

    public void setRezervations(List<Rezervation> rezervations) {
        this.rezervations = rezervations;
    }

    public Erole getType() {
        return type;
    }

    public void setType(Erole type) {
        this.type = type;
    }

    public String getHotelreview() {
        return hotelreview;
    }

    public void setHotelreview(String hotelreview) {
        this.hotelreview = hotelreview;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.type.name()));

        return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userid, user.userid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, name, email, username, password, type, rezervations, enabled);
    }




    @JsonManagedReference
    public List<UserReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<UserReview> reviews) {
        this.reviews = reviews;
    }
}
