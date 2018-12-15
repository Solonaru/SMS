package com.project.sms.entities.employee;

public class OperatorCatalogue implements IRight{

	public OperatorCatalogue() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean canEditCatalogue()
	{
		return true;
	}
	public static boolean canCreateCatalogue()
	{
		return true;
	}
	public static boolean canRemoveCatalogue()
	{
		return true;
	}
	

}
