package com.project.sms.entities.subscription;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.sms.utils.DisplayData;


@RestController
@RequestMapping("/subscription")
@CrossOrigin(origins = "http://localhost:4200")
public class SubscriptionController {

	@Autowired
	private ISubscriptionService subscriptionService;
	
	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{subscriptionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Subscription> findSubscriptionById(@PathVariable("subscriptionId") int subscriptionId) {
		dataDisplay.printCrudInfo(subscriptionId); 
		return subscriptionService.findSubscriptionById(subscriptionId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Subscription> getSubscriptions() {
		dataDisplay.printCrudInfo(); 
		return subscriptionService.findAllSubscriptions();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertSubscription(@RequestBody Subscription subscription) {
		dataDisplay.printCrudInfo(); 
		subscriptionService.insertSubscription(subscription);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateSubscription(@RequestBody Subscription subscription) {
		dataDisplay.printCrudInfo(); 
		subscriptionService.updateSubscription(subscription);
	}

	@RequestMapping(value = "/delete/{subscriptionId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteSubscription(@PathVariable("subscriptionId") int subscriptionId) {
		dataDisplay.printCrudInfo(subscriptionId); 
		subscriptionService.deleteSubscriptionById(subscriptionId);
	}
}
