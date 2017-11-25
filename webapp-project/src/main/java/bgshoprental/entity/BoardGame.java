package bgshoprental.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GryPlanszowe")
public class BoardGame {

	@Id
	@Column(name = "GraPlanszowaID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "Nazwa")
	private String title;

	@Column(name = "IloscSprzedaz")
	private int sellQuantity;

	@Column(name = "CenaSprzedazy", scale = 10, precision = 2)
	private BigDecimal sellPrice;

	@Column(name = "IloscWypozyczenie")
	private int rentalQuantity;

	/**
	 * Price per day of rental
	 */
	@Column(name = "CenaWypozyczenie", scale = 10, precision = 2)
	private BigDecimal rentalPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DostawcaNazwa")
	private Supplier supplier;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSellQuantity() {
		return sellQuantity;
	}

	public void setSellQuantity(int sellQuantity) {
		this.sellQuantity = sellQuantity;
	}

	public int getRentalQuantity() {
		return rentalQuantity;
	}

	public void setRentalQuantity(int rentalQuantity) {
		this.rentalQuantity = rentalQuantity;
	}

	public BigDecimal getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(BigDecimal rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

}
