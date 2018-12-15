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

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name = "Right.findAll", query = "SELECT r FROM Right r")
@Entity
public abstract class Right implements IRight, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "right_generator")
	@SequenceGenerator(name = "right_generator", sequenceName = "right_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;

}
