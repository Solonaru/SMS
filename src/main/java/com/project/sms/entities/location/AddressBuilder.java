package com.project.sms.entities.location;

public class AddressBuilder {
	private String street;
	private Integer streetNr;
	private String buildingNr;
	private String entranceNr;
	private String floorNr;
	private Integer apartamentNr;
	private Integer zipCode;

	public AddressBuilder(String street, Integer streetNr) {
		this.street = street;
		this.streetNr = streetNr;
	}

	public AddressBuilder setBuildingNr(String buildingNr) {
		this.buildingNr = buildingNr;
		return this;
	}

	public AddressBuilder setEntranceNr(String entranceNr) {
		this.entranceNr = entranceNr;
		return this;
	}

	public AddressBuilder setFloorNr(String floorNr) {
		this.floorNr = floorNr;
		return this;
	}

	public AddressBuilder setApartamentNr(Integer apartamentNr) {
		this.apartamentNr = apartamentNr;
		return this;
	}

	public AddressBuilder setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
		return this;
	}

	public Address getAddress() {
		return new Address(street, streetNr, buildingNr, entranceNr, floorNr, apartamentNr, zipCode);
	}

}
