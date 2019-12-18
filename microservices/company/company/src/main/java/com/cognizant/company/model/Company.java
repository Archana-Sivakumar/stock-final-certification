package com.cognizant.company.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="company")
public class Company {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY) 
		@Column(name="co_id")
		@NotNull
		private int id;
		
		@NotNull
		@Column(name="co_name")
		private String companyName;
		
		@NotNull
		@Column(name="co_turn_over")
		private long turnOver;
		
		@NotNull
		@Column(name="co_ceo")
		private String ceo;
		
		@NotNull
		@Column(name="co_board_of_directors")
		private String boardOfDirectors;
		
		@NotNull
		@Column(name="co_check_se")
		private boolean checkStockExchange;
		
		@NotNull
		@Column(name="co_brief")
		private String brief;
		
		@ManyToMany(fetch=FetchType.EAGER)
		 @JoinTable(name = "company_stock_exchange",
	    joinColumns = @JoinColumn(name = "cs_co_id"), 
	    inverseJoinColumns = @JoinColumn(name = "cs_se_id"))
		private Set<StockExchange> stockExchange;
		
		@ManyToOne
		@JoinColumn(name = "sec_id")
		private Sector sector;
		
		public Company() {
			super();
			
		}

		public Company(@NotNull int id, @NotNull String companyName, @NotNull long turnOver, @NotNull String ceo,
				@NotNull String boardOfDirectors, @NotNull boolean checkStockExchange, @NotNull String brief,
				Set<StockExchange> stockExchange, Sector sector) {
			super();
			this.id = id;
			this.companyName = companyName;
			this.turnOver = turnOver;
			this.ceo = ceo;
			this.boardOfDirectors = boardOfDirectors;
			this.checkStockExchange = checkStockExchange;
			this.brief = brief;
			this.stockExchange = stockExchange;
			this.sector = sector;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public long getTurnOver() {
			return turnOver;
		}

		public void setTurnOver(long turnOver) {
			this.turnOver = turnOver;
		}

		public String getCeo() {
			return ceo;
		}

		public void setCeo(String ceo) {
			this.ceo = ceo;
		}

		public String getBoardOfDirectors() {
			return boardOfDirectors;
		}

		public void setBoardOfDirectors(String boardOfDirectors) {
			this.boardOfDirectors = boardOfDirectors;
		}

		public boolean isCheckStockExchange() {
			return checkStockExchange;
		}

		public void setCheckStockExchange(boolean checkStockExchange) {
			this.checkStockExchange = checkStockExchange;
		}

		public String getBrief() {
			return brief;
		}

		public void setBrief(String brief) {
			this.brief = brief;
		}

		public Set<StockExchange> getStockExchange() {
			return stockExchange;
		}

		public void setStockExchange(Set<StockExchange> stockExchange) {
			this.stockExchange = stockExchange;
		}

		public Sector getSector() {
			return sector;
		}

		public void setSector(Sector sector) {
			this.sector = sector;
		}

		@Override
		public String toString() {
			return "Company [id=" + id + ", companyName=" + companyName + ", turnOver=" + turnOver + ", ceo=" + ceo
					+ ", boardOfDirectors=" + boardOfDirectors + ", checkStockExchange=" + checkStockExchange
					+ ", brief=" + brief + ", stockExchange=" + stockExchange + ", sector=" + sector + "]";
		}

		
		
	}
