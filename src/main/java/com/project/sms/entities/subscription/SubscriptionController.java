package com.project.sms.entities.subscription;

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
@RequestMapping("/subscription")
public class SubscriptionController {

	@Autowired
	private ISubscriptionService subscriptionService;

	@RequestMapping(value = "/{subscriptionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Subscription> findSubscriptionById(@PathVariable("subscriptionId") int subscriptionId) {
		return subscriptionService.findSubscriptionById(subscriptionId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Subscription> getSubscriptions() {
		return subscriptionService.findAllSubscriptions();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertSubscription(@RequestBody Subscription subscription) {
		subscriptionService.insertSubscription(subscription);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateSubscription(@RequestBody Subscription subscription) {
		subscriptionService.updateSubscription(subscription);
	}

	@RequestMapping(value = "/delete/{subscriptionId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteSubscription(@PathVariable("subscriptionId") int subscriptionId) {
		subscriptionService.deleteSubscriptionById(subscriptionId);
	}
}
