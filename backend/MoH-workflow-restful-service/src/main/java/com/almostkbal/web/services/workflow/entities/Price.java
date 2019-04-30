package com.almostkbal.web.services.workflow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="price")
public class Price {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "price_id")
	private int id;
	
	@Column(name = "price_value")
	private int value;
	
	@Column(name = "price_description")
	private String priceDescription;
	
	public Price() {
		
	}

	public Price(int priceValue, String priceDescription) {
		super();
		this.value = priceValue;
		this.priceDescription = priceDescription;
	}

	public int getPriceValue() {
		return value;
	}

	public void setPriceValue(int priceValue) {
		this.value = priceValue;
	}

	public String getPriceDescription() {
		return priceDescription;
	}

	public void setPriceDescription(String priceDescription) {
		this.priceDescription = priceDescription;
	}
}
