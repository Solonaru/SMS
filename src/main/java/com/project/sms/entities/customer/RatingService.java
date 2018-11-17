package com.project.sms.entities.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService implements IRatingService{
	
	@Autowired
	private IRatingRepository ratingRepository;

	public Optional<Rating> findRatingById(int ratingId) {
		return ratingRepository.findById(ratingId);
	}

	public List<Rating> findAllRatings() {
		return (List<Rating>) ratingRepository.findAll();
	}

	public void insertRating(Rating rating) {
		ratingRepository.save(rating);
	}

	public void updateRating(Rating rating) {
		ratingRepository.save(rating);
	}

	public void deleteRatingById(int ratingId) {
		ratingRepository.deleteById(ratingId);
	}
}
