package com.ehpadManagement.userManagement.controller;

import com.ehpadManagement.userManagement.model.Review;
import com.ehpadManagement.userManagement.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    public String addReview(@RequestBody Review review) {
        reviewService.addReview(review);
        return "Avis ajouté dans la bdd";
    }

    @DeleteMapping("/delete/{reviewId}")
    public String deleteReview(@PathVariable int reviewId) {
        reviewService.deleteReview(reviewId);
        return "Avis avec l'ID " + reviewId + " supprimé de la bdd";
    }

    @PutMapping("/edit")
    public String editReview(@RequestBody Review updatedReview) {
        reviewService.editReview(updatedReview);
        return "Avis modifié dans la bdd";
    }

    @GetMapping("/get/{reviewId}")
    public Review getReviewById(@PathVariable int reviewId) {
        Review review = reviewService.getReviewById(reviewId);
        if (review != null) {
            System.out.println("Avis trouvé : " + review);
        } else {
            System.out.println("Avis avec l'ID " + reviewId + " non trouvé");
        }
        return review;
    }

    @GetMapping("/getAll")
    public List<Review> getAllReviews() {
        List<Review> reviewList = reviewService.getAllReviews();
        if (reviewList != null && !reviewList.isEmpty()) {
            System.out.println("Liste des avis : " + reviewList);
        } else {
            System.out.println("Aucun avis trouvé dans la bdd");
        }
        return reviewList;
    }



}