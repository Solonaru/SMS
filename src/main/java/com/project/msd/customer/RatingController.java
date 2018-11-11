package com.project.msd.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private IRatingService ratingService;

	@RequestMapping(value = "/{ratingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Rating> findRatingById(@PathVariable("ratingId") int ratingId) {
		return ratingService.findRatingById(ratingId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Rating> getRatings() {
		return ratingService.findAllRatings();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertRating(@RequestBody Rating rating) {
		ratingService.insertRating(rating);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateRating(@RequestBody Rating rating) {
		ratingService.updateRating(rating);
	}

	@RequestMapping(value = "/delete/{ratingId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteRating(@PathVariable("ratingId") int ratingId) {
		ratingService.deleteRatingById(ratingId);
	}
}
