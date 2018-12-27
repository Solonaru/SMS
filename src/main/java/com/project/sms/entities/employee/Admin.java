package com.project.sms.entities.employee;

import javax.persistence.Entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@JsonDeserialize(as = Admin.class)
public class Admin extends Right {
	private static final long serialVersionUID = 1L;

	public Admin() {
		
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
