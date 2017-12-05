package bgshoprental.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import bgshoprental.entity.converter.InternalOrderStatusConverter;

@Entity
@Table(name = "ZamowieniaWewnetrzne")
public class InternalOrder {
	@Id
	@Column(name = "ZamowienieWewnetrzneID")
	private int id;

	@OneToOne(optional = false)
	@JoinColumn(name = "KlientID")
	private Client orderedBy;

	@Column(name = "DataZlozenia")
	@Temporal(TemporalType.DATE)
	private Calendar creationDate;

	@Column(name = "Status")
	@Convert(converter = InternalOrderStatusConverter.class)
	private InternalOrderStatus status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(Client orderedBy) {
		this.orderedBy = orderedBy;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public InternalOrderStatus getStatus() {
		return status;
	}

	public void setStatus(InternalOrderStatus status) {
		this.status = status;
	}

}
