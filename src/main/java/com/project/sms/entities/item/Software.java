package com.project.sms.entities.item;

import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Software.findAll", query = "SELECT s FROM Software s")
@DiscriminatorValue("Software")
public class Software extends Component {
	private static final long serialVersionUID = 1L;

	private String version;

	// -----Constructors-----
	public Software() {
		super();
	}

	public Software(String name, Integer stockQuantity, Date updateDate, String description, String version) {
		super(name, stockQuantity, updateDate, description);
		this.version = version;
	}

	// -----Getters and Setters-----
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	// -----Methods-----
	public String toString() {
		return "Name: " + name;
	}
}