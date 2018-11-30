package com.project.sms.entities.promotion;

import java.util.List;
import java.util.Optional;

public interface IPromotionRangeService {

	public Optional<PromotionRange> findPromotionRangeById(int promotionRangeId);

	public List<PromotionRange> findAllPromotionRanges();

	public void insertPromotionRange(PromotionRange promotionRange);

	public void updatePromotionRange(PromotionRange promotionRange);

	public void deletePromotionRangeById(int promotionRangeId);

}
