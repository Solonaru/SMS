package com.project.sms.entities.promotion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sms.entities.promotion.Promotion;
import com.project.sms.entities.promotion.IPromotionRepository;

@Service
public class PromotionService implements IPromotionService {

	@Autowired
	private IPromotionRepository promotionRepository;

	public Optional<Promotion> findPromotionById(int promotionId) {
		return promotionRepository.findById(promotionId);
	}

	public List<Promotion> findAllPromotions() {
		return (List<Promotion>) promotionRepository.findAll();
	}

	public void insertPromotion(Promotion promotion) {
		promotionRepository.save(promotion);
	}

	public void updatePromotion(Promotion promotion) {
		promotionRepository.save(promotion);
	}

	public void deletePromotionById(int promotionId) {
		promotionRepository.deleteById(promotionId);
	}
	
}
