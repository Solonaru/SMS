package com.project.sms.entities.employee;

import javax.persistence.Entity;

@Entity
public class OperatorEmployees extends Right {
	private static final long serialVersionUID = 1L;

	public OperatorEmployees() {

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
		return false;
	}

	public boolean canEditCatalogue() {
		return false;
	}

	public boolean canRemoveCatalogue() {
		return false;
	}

	public boolean canCreateEmployee() {
		return true;
	}

	public boolean canEditEmployee() {
		return true;
	}

	public boolean canRemoveEmployee() {
		return true;
	}
}
