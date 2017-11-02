package com.carshop.service;

import java.net.UnknownHostException;

import com.carshop.model.ResponseWithUserReviews;
import com.carshop.model.UserModel;
import com.carshop.model.UserReviewModel;

public class UserReviewServiceImplement implements UserReviewService {
	
	UserService userService = new UserServiceImplement();
	
	@Override
	public String addUserReview(String carId, String userId, String review,
			String rating) throws UnknownHostException {
		UserModel userModel = new UserModel();
		userModel = userService.getUserById(userId);
		UserReviewModel userReview = new UserReviewModel();
		userReview.setCarId(carId);
		userReview.setRating(rating);
		userReview.setReview(review);
		userReview.setUserId(userId);
		userReview.setUserName(userModel.getName());
		return null;
	}

	@Override
	public ResponseWithUserReviews getUserReviews(String carId, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
