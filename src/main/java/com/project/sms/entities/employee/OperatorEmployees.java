package com.project.sms.entities.employee;

public class OperatorEmployees implements IRight{

	public OperatorEmployees() {
		// TODO Auto-generated constructor stub
	}

	/// Employee rights
	
	public static boolean canEditEmployee()
	{
		return true;
	}
	public static boolean canCreateEmployee()
	{
		return true;
	}
	public static boolean canRemoveEmployee()
	{
		return true;
	}
}
