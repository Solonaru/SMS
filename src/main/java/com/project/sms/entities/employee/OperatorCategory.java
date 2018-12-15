package com.project.sms.entities.employee;

public class OperatorCategory implements IRight{
	public OperatorCategory() {
		// TODO Auto-generated constructor stub
	}
	
	/// Category rights

	public static boolean canEditCategory()
	{
		return true;
	}
	public static boolean canCreateCategory()
	{
		return true;
	}
	public static boolean canRemoveCategory()
	{
		return true;
	}
}
