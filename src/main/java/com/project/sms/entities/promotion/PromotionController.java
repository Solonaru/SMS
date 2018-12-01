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

import com.project.sms.entities.promotion.Promotion;
import com.project.sms.utils.DisplayData;
import com.project.sms.entities.promotion.IPromotionService;

@RestController
@RequestMapping("/promotion")
public class PromotionController {

	@Autowired
	private IPromotionService promotionService;
	
	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{promotionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Promotion> findPromotionById(@PathVariable("promotionId") int promotionId) {
		dataDisplay.printCrudInfo(promotionId); 
		return promotionService.findPromotionById(promotionId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Promotion> getPromotions() {
		dataDisplay.printCrudInfo(); 
		return promotionService.findAllPromotions();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertPromotion(@RequestBody Promotion promotion) {
		dataDisplay.printCrudInfo(); 
		promotionService.insertPromotion(promotion);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updatePromotion(@RequestBody Promotion promotion) {
		dataDisplay.printCrudInfo(); 
		promotionService.updatePromotion(promotion);
	}

	@RequestMapping(value = "/delete/{promotionId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deletePromotion(@PathVariable("promotionId") int promotionId) {
		dataDisplay.printCrudInfo(promotionId); 
		promotionService.deletePromotionById(promotionId);
	}

}
