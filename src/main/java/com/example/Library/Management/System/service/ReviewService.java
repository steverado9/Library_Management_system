package com.example.Library.Management.System.service;

import com.example.Library.Management.System.entity.Review;

public interface ReviewService {

    void saveReview(String email, Long id, Review review);
}
