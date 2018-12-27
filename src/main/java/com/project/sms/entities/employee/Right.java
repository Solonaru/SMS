package com.project.sms.entities.employee;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name = "Right.findAll", query = "SELECT r FROM Right r")
@Entity

//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
//@JsonSubTypes({ @JsonSubTypes.Type(value = Admin.class, name = "Admin"),
//		@JsonSubTypes.Type(value = OperatorCatalogues.class, name = "OperatorCatalogues"),
//		@JsonSubTypes.Type(value = OperatorCategories.class, name = "OperatorCategories"),
//		@JsonSubTypes.Type(value = OperatorEmployees.class, name = "OperatorEmployees"),
//		@JsonSubTypes.Type(value = OperatorProducts.class, name = "OperatorProducts"), })

//@JsonDeserialize(as = Admin.class)
@JsonDeserialize(using = RightDeserializer.class)
public abstract class Right implements IRight, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "right_generator")
	@SequenceGenerator(name = "right_generator", sequenceName = "right_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;

	public Right() {
		
	}

	protected Integer getId() {
		return id;
	}

	protected void setId(Integer id) {
		this.id = id;
	}

}
