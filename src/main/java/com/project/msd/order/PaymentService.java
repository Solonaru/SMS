package com.project.msd.order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService{
	
	@Autowired
	private IPaymentRepository paymentRepository;

	public Optional<Payment> findPaymentById(int paymentId) {
		return paymentRepository.findById(paymentId);
	}
	
	public List<Payment> findAllPayments() {
		return (List<Payment>) paymentRepository.findAll();
	}

	public void insertPayment(Payment payment) {
		paymentRepository.save(payment);		
	}

	public void updatePayment(Payment payment) {
		paymentRepository.save(payment);		
	}

	public void deletePaymentById(int paymentId) {
		paymentRepository.deleteById(paymentId);		
	}
}
