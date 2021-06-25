package licenta.backend.controller;

import licenta.backend.helpers.ReviewDetails;
import licenta.backend.helpers.ReviewHelper;
import licenta.backend.helpers.UserRoomHelper;
import licenta.backend.model.Review;
import licenta.backend.model.UserReview;
import licenta.backend.service.ReviewService;
import licenta.backend.service.RoomService;
import licenta.backend.service.UserReviewService;
import licenta.backend.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reviews")
public class ReviewsController {
    @Resource
    UserReviewService userReviewService;
    @Resource
    ReviewService reviewService;
    @Resource
    RoomService roomService;
@Resource
UserService service;
    @GetMapping("/{id}")
    public List<ReviewDetails> getAll(@PathVariable int id) {
        return userReviewService.getAll(id);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
@PostMapping
    public  void saveReview(@RequestBody ReviewHelper reviewHelper){
     Review review = new Review(reviewHelper.getReviewTitle(),reviewHelper.getReviewText());
    UserReview  userReview=new UserReview(service.getOneById(reviewHelper.getUserId()),roomService.getOneById(reviewHelper.getRoomId()));
    List<UserReview> reviews= new ArrayList<>();
    reviews.add(userReview);
    review.setUserReviews(reviews);
    userReview.setReview(review);
    reviewService.save(review);
    userReviewService.save(userReview);
}
@GetMapping("/reviewed/{id}")
    public  List<UserRoomHelper> getRoomsReviewed(@PathVariable Long id){
        return  userReviewService.getRoomsReviewed(id);
}
}
