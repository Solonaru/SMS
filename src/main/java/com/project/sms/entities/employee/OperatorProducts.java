package com.project.sms.entities.employee;

import javax.persistence.Entity;

@Entity
public class OperatorProducts extends Right {
	private static final long serialVersionUID = 1L;

	public OperatorProducts() {

	}

	public boolean canCreateProduct() {
		return true;
	}

	public boolean canEditProduct() {
		return true;
	}

	public boolean canRemoveProduct() {
		return true;
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
