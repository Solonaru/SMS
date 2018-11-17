package com.project.sms.entities.subscription;

import java.util.List;
import java.util.Optional;

public interface ISubscriptionService {

	public Optional<Subscription> findSubscriptionById(int subscriptionId);

	public List<Subscription> findAllSubscriptions();

	public void insertSubscription(Subscription subscription);

	public void updateSubscription(Subscription subscription);

	public void deleteSubscriptionById(int subscriptionId);
}
