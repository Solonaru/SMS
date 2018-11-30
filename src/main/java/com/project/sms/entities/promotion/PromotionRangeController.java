package com.project.sms.entities.promotion;

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
@RequestMapping("/promotionRange")
public class PromotionRangeController {

	@Autowired
	private IPromotionRangeService promotionRangeService;

	@RequestMapping(value = "/{promotionRangeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<PromotionRange> findPromotionRangeById(@PathVariable("promotionRangeId") int promotionRangeId) {
		return promotionRangeService.findPromotionRangeById(promotionRangeId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PromotionRange> getPromotionRanges() {
		return promotionRangeService.findAllPromotionRanges();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertPromotionRange(@RequestBody PromotionRange promotionRange) {
		promotionRangeService.insertPromotionRange(promotionRange);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updatePromotionRange(@RequestBody PromotionRange promotionRange) {
		promotionRangeService.updatePromotionRange(promotionRange);
	}

	@RequestMapping(value = "/delete/{promotionRangeId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deletePromotionRange(@PathVariable("promotionRangeId") int promotionRangeId) {
		promotionRangeService.deletePromotionRangeById(promotionRangeId);
	}

}
