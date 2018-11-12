package com.project.msd.order;

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
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private IPaymentService paymentService;
	
	@RequestMapping(value = "/{paymentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Payment> findPaymentById(@PathVariable("paymentId") int paymentId) {
		return paymentService.findPaymentById(paymentId);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Payment> getPayments() {
		return paymentService.findAllPayments();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertPayment(@RequestBody Payment payment) {
		paymentService.insertPayment(payment);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updatePayment(@RequestBody Payment payment) {
		paymentService.updatePayment(payment);
	}
	
	@RequestMapping(value = "/delete/{paymentId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCounty(@PathVariable("paymentId") int paymentId) {
		paymentService.deletePaymentById(paymentId);
	}
}
