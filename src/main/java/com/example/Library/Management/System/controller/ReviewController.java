package com.example.Library.Management.System.controller;

import com.example.Library.Management.System.entity.Review;
import com.example.Library.Management.System.entity.User;
import com.example.Library.Management.System.service.BookService;
import com.example.Library.Management.System.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {

    private ReviewService reviewService;

    ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/book/{id}/review")
    public String showReviewForm(@PathVariable Long id, Model model) {

        Review review = new Review();
        model.addAttribute("review", review);
        model.addAttribute("bookId", id);

        return "review_form";
    }

    @PostMapping("/book/{id}/review")
    public String submitReview(@PathVariable Long id, @ModelAttribute Review review, HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        String name = loggedInUser.getName();

        reviewService.saveReview(name, id, review);

        return "redirect:/book/" + id;
    }
}
