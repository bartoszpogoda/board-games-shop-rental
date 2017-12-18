package bgshoprental.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ElementyZamowienZewnetrznych")
public class ExternalOrderElement implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "ZamowienieZewnetrzneID")
	private ExternalOrder externalOrder;

	@Id
	@OneToOne(optional = false)
	@JoinColumn(name = "GraPlanszowaID")
	private BoardGame boardGame;

	@Column(name = "Cena", scale = 10, precision = 2)
	private BigDecimal price;

	@Column(name = "Ilosc")
	private int quantity;

	public ExternalOrder getExternalOrder() {
		return externalOrder;
	}

	public void setExternalOrder(ExternalOrder externalOrder) {
		this.externalOrder = externalOrder;
	}

	public BoardGame getBoardGame() {
		return boardGame;
	}

	public void setBoardGame(BoardGame boardGame) {
		this.boardGame = boardGame;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
