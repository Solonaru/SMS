package com.project.sms.entities.employee;

import javax.persistence.AttributeConverter;

public class RightConverter implements AttributeConverter<IRight, String> {

	public String convertToDatabaseColumn(IRight right) {
		return right.getClass().getSimpleName().toLowerCase();
	}

	public IRight convertToEntityAttribute(String dbData) {
		// works as a factory
		if (dbData.equalsIgnoreCase("Admin")) {
			return new Admin();
		} else if (dbData.equalsIgnoreCase("OperatorProducts")) {
			return new OperatorProducts();
		} else if (dbData.equalsIgnoreCase("OperatorCatalogues")) {
			return new OperatorCatalogues();
		} else if (dbData.equalsIgnoreCase("OperatorCategories")) {
			return new OperatorCategories();
		} else if (dbData.equalsIgnoreCase("OperatorEmployees")) {
			return new OperatorEmployees();
		} else if (dbData.equalsIgnoreCase("NoRight")) {
			return new NoRight();
		}

		return null;
	}

}
