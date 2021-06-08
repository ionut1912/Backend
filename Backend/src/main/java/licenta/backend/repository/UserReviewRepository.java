package licenta.backend.repository;

import licenta.backend.helpers.ReviewDetails;
import licenta.backend.helpers.UserRoomHelper;
import licenta.backend.model.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
    @Query(value = "select name,reviewtitle,reviewtext from userreviews inner join reviews on userreviews.reviewid=reviews.reviewid inner join users on userreviews.userid=users.userid where roomid=?1", nativeQuery = true)
    List<ReviewDetails> getReviews(int id);
    @Query(value = "select roomid from userreviews where userid=?1",nativeQuery = true)
    List<UserRoomHelper> getRoomsReviewed(Long id);
}
