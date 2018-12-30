package com.project.sms.entities.location;

public class AddressGenerate {
	public static void main (String a[]) {
		Address a1 = new AddressBuilder().setApartamentNr(1).setStreetNr(2).setBuildingNr("2").setFloorNr("5").getAddress();	

		System.out.println(a1);
		
}}
