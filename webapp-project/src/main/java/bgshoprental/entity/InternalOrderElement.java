package bgshoprental.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ElementyZamowienWewnetrznych")
public class InternalOrderElement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "ZamowienieWewnetrzneID")
	private InternalOrder internalOrder;

	@Id
	@OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "GraPlanszowaID")
	private BoardGame boardGame;

	@Column(name = "Ilosc")
	private int quantity;

	public InternalOrder getInternalOrder() {
		return internalOrder;
	}

	public void setInternalOrder(InternalOrder internalOrder) {
		this.internalOrder = internalOrder;
	}

	public BoardGame getBoardGame() {
		return boardGame;
	}

	public void setBoardGame(BoardGame boardGame) {
		this.boardGame = boardGame;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
