package com.project.sms.entities.employee;

public class Admin implements IRight{

	public Admin() {
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
		
	/// Catalogue rights
		
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
