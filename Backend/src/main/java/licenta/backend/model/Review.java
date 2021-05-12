package licenta.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewid;
    @Column(name = "reviewtitle")
    private String reviewtitle;
    @Column(name = "reviewtext")
    private String reviewtext;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "review")
    @JsonManagedReference("review")
    public List<UserReview> userReviews;

    public Review(String reviewtitle, String reviewtext) {
        this.reviewtitle = reviewtitle;
        this.reviewtext = reviewtext;
    }

    public Review() {
    }

    public int getReviewid() {
        return reviewid;
    }

    public void setReviewid(int reviewid) {
        this.reviewid = reviewid;
    }

    public String getReviewtitle() {
        return reviewtitle;
    }

    public void setReviewtitle(String reviewtitle) {
        this.reviewtitle = reviewtitle;
    }

    public String getReviewtext() {
        return reviewtext;
    }

    public void setReviewtext(String reviewtext) {
        this.reviewtext = reviewtext;
    }

    @JsonManagedReference
    public List<UserReview> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<UserReview> userReviews) {
        this.userReviews = userReviews;
    }
}
