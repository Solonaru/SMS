package com.project.sms.entities.order;

import java.util.List;
import java.util.Optional;

public interface IPaymentService {
	
	public Optional<Payment> findPaymentById(int paymentId);
	
	public List<Payment> findAllPayments();

	public void insertPayment(Payment payment);

	public void updatePayment(Payment payment);

	public void deletePaymentById(int payment);
}
