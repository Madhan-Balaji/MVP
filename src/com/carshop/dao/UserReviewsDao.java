package com.carshop.dao;

import java.net.UnknownHostException;

import com.carshop.model.ResponseWithUserReviews;
import com.carshop.model.UserReviewModel;

public interface UserReviewsDao {
	public String addReview(UserReviewModel userReview) throws UnknownHostException;
	public ResponseWithUserReviews fetchReviews(String carId, String userId) throws UnknownHostException;
}
