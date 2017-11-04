package com.carshop.service;

import java.net.UnknownHostException;

import com.carshop.model.ResponseWithUserReviews;

public interface UserReviewService {
	public String addUserReview(String carId, String userId, String review, String rating) throws UnknownHostException;
	public ResponseWithUserReviews getUserReviews(String carId, String userId) throws UnknownHostException;
}
