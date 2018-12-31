package com.project.sms.entities.employee;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Admin.class)
public class Admin implements IRight {

	// ----- Constructors -----
	public Admin() {

	}

	// ----- Methods -----
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
		return true;
	}

	public boolean canEditCategory() {
		return true;
	}

	public boolean canRemoveCategory() {
		return true;
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
		return true;
	}

	public boolean canEditEmployee() {
		return true;
	}

	public boolean canRemoveEmployee() {
		return true;
	}

}
