package com.almostkbal.web.services.workflow.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="request_payment")
public class RequestPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,  generator="SEQ_REQUEST_PAYMENT")
	@Column(name = "request_payment_id")
	private int id;
	
	@Column(name = "receipt_serial_no")
	private String receiptSerialNumber;
	
	@Column(name = "payment_done")
	private byte paymentDone;
	
	@Column(name = "payment_date")
	private Date paymentDate;
	
	@Column(name = "price")
	private int price;
	
	public RequestPayment() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReceiptSerialNumber() {
		return receiptSerialNumber;
	}

	public void setReceiptSerialNumber(String receiptSerialNumber) {
		this.receiptSerialNumber = receiptSerialNumber;
	}

	public byte getPaymentDone() {
		return paymentDone;
	}

	public void setPaymentDone(byte paymentDone) {
		this.paymentDone = paymentDone;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	

}
