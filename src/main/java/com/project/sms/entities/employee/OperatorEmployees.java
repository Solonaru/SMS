package com.project.sms.entities.employee;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = OperatorEmployees.class)
public class OperatorEmployees implements IRight {

	// ----- Constructors -----
	public OperatorEmployees() {

	}

	// ----- Methods -----
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
