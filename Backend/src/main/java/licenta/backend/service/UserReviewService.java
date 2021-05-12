package licenta.backend.service;

import licenta.backend.helpers.ReviewDetails;
import licenta.backend.model.UserReview;
import licenta.backend.repository.UserReviewRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserReviewService {
    @Resource
    UserReviewRepository userReviewRepository;

    public List<ReviewDetails> getAll(int id) {
        return userReviewRepository.getReviews(id);
    }
    public UserReview save(UserReview userreview) {

        return userReviewRepository.save(userreview);
    }
}

