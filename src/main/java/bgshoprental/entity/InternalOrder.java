package bgshoprental.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import bgshoprental.entity.converter.InternalOrderStatusConverter;

@Entity
@Table(name = "ZamowieniaWewnetrzne")
public class InternalOrder {
	@Id
	@Column(name = "ZamowienieWewnetrzneID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "KlientID")
	private Client orderedBy;

	@Column(name = "DataZlozenia")
	@Temporal(TemporalType.DATE)
	private Calendar creationDate;

	@Column(name = "Status")
	@Convert(converter = InternalOrderStatusConverter.class)
	private InternalOrderStatus status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "internalOrder", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<InternalOrderElement> elements;

	public InternalOrder() {
		elements = new ArrayList<>();
	}
	
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

	public List<InternalOrderElement> getElements() {
		return elements;
	}

	public void setElements(List<InternalOrderElement> elements) {
		this.elements = elements;
	}

}
