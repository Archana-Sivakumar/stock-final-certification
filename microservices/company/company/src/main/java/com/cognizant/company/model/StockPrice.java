package com.cognizant.company.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="stock_price")
public class StockPrice {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="sp_id")
	@NotNull
	private int id;
	
	@NotNull
	@Column(name="sp_company_code")
	private int companyCode;
	
	@NotNull
	@Column(name="sp_stock_exchange_name")
	private String stockExchangeName;
	
	@NotNull
	@Column(name="sp_current_price")
	private double currentPrice;
	
	
	@NotNull
	@Column(name="sp_date")
	private Date date;
	
	@NotNull
	@Column(name="sp_time")
	private Time time;
	

	
	public StockPrice() {
		super();
	}


	public StockPrice(@NotNull int id, @NotNull int companyCode, @NotNull String stockExchangeName,
			@NotNull double currentPrice, @NotNull Date date, @NotNull Time time) {
		super();
		this.id = id;
		this.companyCode = companyCode;
		this.stockExchangeName = stockExchangeName;
		this.currentPrice = currentPrice;
		this.date = date;
		this.time = time;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	
	

	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Time getTime() {
		return time;
	}


	public void setTime(Time time) {
		this.time = time;
	}


	public int getCompanyCode() {
		return companyCode;
	}



	public void setCompanyCode(int companyCode) {
		this.companyCode = companyCode;
	}



	public String getStockExchangeName() {
		return stockExchangeName;
	}



	public void setStockExchangeName(String stockExchangeName) {
		this.stockExchangeName = stockExchangeName;
	}


	@Override
	public String toString() {
		return "StockPrice [id=" + id + ", companyCode=" + companyCode + ", stockExchangeName=" + stockExchangeName
				+ ", currentPrice=" + currentPrice + ", date=" + date + ", time=" + time + "]";
	}

}
