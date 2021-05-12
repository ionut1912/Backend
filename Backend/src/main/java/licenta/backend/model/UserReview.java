package licenta.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Table(name = "userreviews")
@Entity
public class UserReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userreviewid;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    @JsonBackReference("user-review")
    private User userreviews;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roomid")
    @JsonBackReference("roomreviews")
    private Room roomreview;

    public UserReview() {
    }

    public UserReview(User userreviews, Room roomreview) {
        this.userreviews = userreviews;
        this.roomreview = roomreview;
    }

    public User getUserreviews() {
        return userreviews;
    }

    public void setUserreviews(User userreviews) {
        this.userreviews = userreviews;
    }

    public Room getRoomreview() {
        return roomreview;
    }

    public void setRoomreview(Room roomreview) {
        this.roomreview = roomreview;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reviewid")
    @JsonBackReference("review")
    private Review review;

    public int getUserreviewid() {
        return userreviewid;
    }

    public void setUserreviewid(int userreviewid) {
        this.userreviewid = userreviewid;
    }



    @JsonBackReference
    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @JsonBackReference
    public User getUserreview() {
        return userreviews;
    }

    public void setUserreview(User userreview) {
        this.userreviews = userreview;
    }
}
