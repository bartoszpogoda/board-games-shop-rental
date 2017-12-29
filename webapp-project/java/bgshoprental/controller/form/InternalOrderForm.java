package bgshoprental.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InternalOrderForm {

	@NotNull
	private Integer boardGameId;

	@NotNull
	@Size(min=0)
	private Integer quantity;

	public Integer getBoardGameId() {
		return boardGameId;
	}

	public void setBoardGameId(Integer boardGameId) {
		this.boardGameId = boardGameId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
