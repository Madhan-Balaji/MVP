package com.carshop.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.carshop.model.ResponseWithUserReviews;
import com.carshop.model.UserReviewModel;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class UserReviewsDaoImplement implements UserReviewsDao {
	
	public DBCollection getUserReviewCollection() throws UnknownHostException{
		MongoClient mongo = new MongoClient("localhost",27017);
		DB mongoDB = mongo.getDB("carshop");
		return mongoDB.getCollection("user_reviews");
	}
	
	public UserReviewModel allDataSetter(BasicDBObject handler){
		UserReviewModel review = new UserReviewModel();
		review.setId(handler.getString("_id"));
		review.setCarId(handler.getString("carId"));
		review.setRating(handler.getString("rating"));
		review.setReview(handler.getString("review"));
		review.setUserId(handler.getString("userId"));
		review.setUserName(handler.getString("userName"));
		return review;
	}
	
	@Override
	public String addReview(UserReviewModel userReview) throws UnknownHostException {
		DBCollection collection = getUserReviewCollection();
		BasicDBObject document = new BasicDBObject();
		document.append("carId", userReview.getCarId());
		document.append("userId", userReview.getUserId());
		document.append("userName", userReview.getUserName());
		document.append("review", userReview.getReview());
		document.append("rating", userReview.getRating());
		collection.insert(document);
		return "success";
	}

	@Override
	public ResponseWithUserReviews fetchReviews(String carId, String userId) throws UnknownHostException {
		DBCollection collection = getUserReviewCollection();
		ResponseWithUserReviews response = new ResponseWithUserReviews();
		BasicDBObject search = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("userId", userId ));
		obj.add(new BasicDBObject("carId", carId ));
		search.put("$and", obj);
		int hasData = collection.find().count();
		if(hasData > 0){
			DBCursor cursor = collection.find(search);
			if(cursor.hasNext()){
				response.userReviewed = "yes";
				int count = collection.find().sort(new BasicDBObject("_id", -1)).count();
				DBCursor gotCursor = collection.find().sort(new BasicDBObject("_id", -1)).limit(4);
				int hasUser = 0;
				if(count>4){
					count = 4;
				}
				for(int i=0;i<count;i++){
					BasicDBObject holder = (BasicDBObject) gotCursor.next();
					String user = holder.getString("userId");
					if(user.equals(userId)){
						hasUser = 1;
						break;
					}
				}
				if(hasUser == 1){
					int n=0;
					UserReviewModel[] review = new UserReviewModel[count];
					gotCursor = collection.find().sort(new BasicDBObject("_id", -1)).limit(4);
					while(gotCursor.hasNext()){
						BasicDBObject handler = (BasicDBObject) gotCursor.next();
						review[n] = allDataSetter(handler);
						n++;
					}
					response.status ="success";
					response.userReview = review;
				}
				else{
					int n=0;
					UserReviewModel[] review = new UserReviewModel[count+1];
					gotCursor = collection.find().sort(new BasicDBObject("_id", -1)).limit(4);
					while(gotCursor.hasNext()){
						BasicDBObject handler = (BasicDBObject) gotCursor.next();
						review[n] = new UserReviewModel();
						review[n] = allDataSetter(handler);
						n++;
					}
					BasicDBObject handler = (BasicDBObject) cursor.next();
					review[n] = new UserReviewModel();
					review[n] = allDataSetter(handler);
					response.status ="success";
					response.userReview = review;
				}
			}
			else{
				response.userReviewed = "no";
				int count = collection.find().sort(new BasicDBObject("_id",-1)).count();
				DBCursor gotCursor = collection.find().sort(new BasicDBObject("_id",-1)).limit(4);
				if(count>4){
					count = 4;
				}
				UserReviewModel[] review = new UserReviewModel[count];
				int n=0;
				while(gotCursor.hasNext()){
					BasicDBObject handler = (BasicDBObject) gotCursor.next();
					review[n] = new UserReviewModel();
					review[n] = allDataSetter(handler);
					n++;
				}
				response.status = "success";
				response.userReview = review;
			}
		}
		else{
			response.status = "failed";
		}
		return response;
	}

}
