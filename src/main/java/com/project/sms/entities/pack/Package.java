package com.project.sms.entities.pack;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.project.sms.entities.item.Item;

@Entity
@NamedQuery(name = "Package.findAll", query = "SELECT p FROM Package p")
@DiscriminatorValue("Package")
public class Package extends Item {
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "pack")
	private List<PackageLine> packageLines = new ArrayList<PackageLine>();

	// ------ Constructors -------
	public Package() {
		super();
	}

	public Package(String name, Date updateDate) {
		super(name, updateDate);
	}

	// ------ Getters and Setters ------
	public List<PackageLine> getPackageLines() {
		return packageLines;
	}

	public void setPackageLines(List<PackageLine> packageLines) {
		this.packageLines = packageLines;
	}

}
