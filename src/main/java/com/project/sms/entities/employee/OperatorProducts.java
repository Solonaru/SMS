package com.project.sms.entities.employee;

public class OperatorProducts implements IRight{

	public OperatorProducts() {
		// TODO Auto-generated constructor stub
	}
	/// Product rights
	
	public static boolean canEditProduct()
	{
		return true;
	}
	public static boolean canCreateProduct()
	{
		return true;
	}
	public static boolean canRemoveProduct()
	{
		return true;
	}
}
