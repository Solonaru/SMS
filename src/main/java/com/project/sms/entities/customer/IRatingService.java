package com.project.sms.entities.customer;

import java.util.List;
import java.util.Optional;

public interface IRatingService {
	
	public Optional<Rating> findRatingById(int ratingId);

	public List<Rating> findAllRatings();

	public void insertRating(Rating rating);

	public void updateRating(Rating rating);

	public void deleteRatingById(int ratingId);
}
