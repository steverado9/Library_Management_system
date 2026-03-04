package com.example.Library.Management.System.service;

import com.example.Library.Management.System.entity.Review;

public interface ReviewService {

    void addReview(Long userId, Long bookId, int rating, String comment);

    void saveReview(String name, Long id, Review review);
}
