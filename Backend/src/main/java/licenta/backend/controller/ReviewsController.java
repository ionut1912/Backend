package licenta.backend.controller;

import licenta.backend.helpers.ReviewDetails;
import licenta.backend.service.UserReviewService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reviews")
public class ReviewsController {
    @Resource
    UserReviewService userReviewService;

    @GetMapping("/{id}")
    public List<ReviewDetails> getAll(@PathVariable int id) {
        return userReviewService.getAll(id);
    }
}
