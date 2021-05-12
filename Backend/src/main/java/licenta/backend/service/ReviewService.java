package licenta.backend.service;

import licenta.backend.model.Review;
import licenta.backend.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Resource
    public ReviewRepository reviewRepository;



    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review save(Review review) {

        return reviewRepository.save(review);
    }
    public <T> Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }


}
