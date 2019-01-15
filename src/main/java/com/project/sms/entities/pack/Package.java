package com.project.sms.entities.pack;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.sms.entities.item.Item;
import com.project.sms.entities.lines.ILine;
import com.project.sms.entities.lines.ILineIterator;
import com.project.sms.enums.Month;

@Entity
@NamedQuery(name = "Package.findAll", query = "SELECT p FROM Package p")
@DiscriminatorValue("Package")
public class Package extends Item implements ILineIterator {
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pack")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties(value = { "pack", "category" })
	private List<PackageLine> packageLines = new ArrayList<PackageLine>();

	// ------ Constructors -------
	public Package() {
		super();
	}

	public Package(String name, Integer stockQuantity, Date updateDate, String description) {
		super(name, stockQuantity, updateDate, description);
	}

	// ------ Getters and Setters ------
	public List<PackageLine> getPackageLines() {
		return packageLines;
	}

	public void setPackageLines(List<PackageLine> packageLines) {
		this.packageLines = packageLines;
	}

	// ----- Methods -----
	public void addLine(PackageLine packageLine) {
		packageLines.add(packageLine);
		packageLine.setPack(this);
	}

	public Double getPrice() {
		Double price = 0.0;
		Iterator<? extends ILine> linesIterator = this.createLinesIterator();

		while (linesIterator.hasNext()) {
			PackageLine packageLine = (PackageLine) linesIterator.next();
			price += packageLine.getProduct().getPrice();
		}

		return (double) Math.round(price * 0.9);
	}

	public Double getPrice(Month month) {
		Double price = 0.0;
		Iterator<? extends ILine> linesIterator = this.createLinesIterator();

		while (linesIterator.hasNext()) {
			PackageLine packageLine = (PackageLine) linesIterator.next();
			price += packageLine.getProduct().getPrice(month);
		}

		return (double) Math.round(price * 0.9);
	}

	public Iterator<? extends ILine> createLinesIterator() {
		return packageLines.iterator();
	}

}
