package com.project.sms.entities.employee;

public class OperatorCatalogue implements IRight {

	public OperatorCatalogue() {

	}

	public boolean canCreateProduct() {
		return false;
	}

	public boolean canEditProduct() {
		return false;
	}

	public boolean canRemoveProduct() {
		return false;
	}

	public boolean canCreateCategory() {
		return false;
	}

	public boolean canEditCategory() {
		return false;
	}

	public boolean canRemoveCategory() {
		return false;
	}

	public boolean canCreateCatalogue() {
		return true;
	}

	public boolean canEditCatalogue() {
		return true;
	}

	public boolean canRemoveCatalogue() {
		return true;
	}

	public boolean canCreateEmployee() {
		return false;
	}

	public boolean canEditEmployee() {
		return false;
	}

	public boolean canRemoveEmployee() {
		return false;
	}

}
