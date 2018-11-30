package com.project.sms.entities.promotion;

import java.util.List;
import java.util.Optional;

public interface IPromotionService {

	public Optional<Promotion> findPromotionById(int promotionId);

	public List<Promotion> findAllPromotions();

	public void insertPromotion(Promotion promotion);

	public void updatePromotion(Promotion promotion);

	public void deletePromotionById(int promotionId);

}
