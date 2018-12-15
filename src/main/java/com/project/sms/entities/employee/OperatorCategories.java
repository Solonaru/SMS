package com.project.sms.entities.employee;

import javax.persistence.Entity;

@Entity
public class OperatorCategories extends Right {
	private static final long serialVersionUID = 1L;

	public OperatorCategories() {

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
		return true;
	}

	public boolean canEditCategory() {
		return true;
	}

	public boolean canRemoveCategory() {
		return true;
	}

	public boolean canCreateCatalogue() {
		return false;
	}

	public boolean canEditCatalogue() {
		return false;
	}

	public boolean canRemoveCatalogue() {
		return false;
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
