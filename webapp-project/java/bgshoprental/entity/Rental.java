package bgshoprental.entity;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import bgshoprental.entity.converter.RentalStatusConverter;

@Entity
@Table(name = "Wypozyczenia")
public class Rental {

	@Id
	@Column(name = "WypozyczenieID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "KlientID")
	private Client client;

	@ManyToOne(optional = false)
	@JoinColumn(name = "GraPlanszowaID")
	private BoardGame boardGame;

	@Column(name = "DataWypozyczenia")
	@Temporal(TemporalType.DATE)
	private Calendar creationDate;

	@Column(name = "DataZwrotu")
	@Temporal(TemporalType.DATE)
	private Calendar rentUntilDate;

	@Column(name = "KosztWypozyczenia", scale = 10, precision = 2)
	private BigDecimal price;

	@Column(name = "Status")
	@Convert(converter = RentalStatusConverter.class)
	private RentalStatus status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public BoardGame getBoardGame() {
		return boardGame;
	}

	public void setBoardGame(BoardGame boardGame) {
		this.boardGame = boardGame;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public Calendar getRentUntilDate() {
		return rentUntilDate;
	}

	public void setRentUntilDate(Calendar rentUntilDate) {
		this.rentUntilDate = rentUntilDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public RentalStatus getStatus() {
		return status;
	}

	public void setStatus(RentalStatus status) {
		this.status = status;
	}

}
