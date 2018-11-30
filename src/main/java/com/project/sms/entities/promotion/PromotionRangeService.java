package com.project.sms.entities.promotion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionRangeService implements IPromotionRangeService {

	@Autowired
	private IPromotionRangeRepository promotionRangeRepository;

	public Optional<PromotionRange> findPromotionRangeById(int promotionRangeId) {
		return promotionRangeRepository.findById(promotionRangeId);
	}

	public List<PromotionRange> findAllPromotionRanges() {
		return (List<PromotionRange>) promotionRangeRepository.findAll();
	}

	public void insertPromotionRange(PromotionRange promotionRange) {
		promotionRangeRepository.save(promotionRange);
	}

	public void updatePromotionRange(PromotionRange promotionRange) {
		promotionRangeRepository.save(promotionRange);
	}

	public void deletePromotionRangeById(int promotionRangeId) {
		promotionRangeRepository.deleteById(promotionRangeId);
	}

}
