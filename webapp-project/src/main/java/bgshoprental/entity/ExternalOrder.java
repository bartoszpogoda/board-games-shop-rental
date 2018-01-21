package bgshoprental.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import bgshoprental.entity.converter.ExternalOrderStatusConverter;

@Entity
@Table(name = "ZamowieniaZewnetrzne")
public class ExternalOrder {

	@Id
	@Column(name = "ZamowienieZewnetrzneID")
	private int id;

	@OneToOne(optional = false)
	@JoinColumn(name = "PracownikID")
	private Employee createdBy;

	@OneToOne(optional = false)
	@JoinColumn(name = "DostawcaNazwa")
	private Supplier supplier;

	@Column(name = "Status")
	@Convert(converter = ExternalOrderStatusConverter.class)
	private ExternalOrderStatus status;

	@Column(name = "DataZlozenia")
	@Temporal(TemporalType.DATE)
	private Calendar creationDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "externalOrder", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<ExternalOrderElement> elements;

	public ExternalOrder() {
		elements = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Employee createdBy) {
		this.createdBy = createdBy;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public ExternalOrderStatus getStatus() {
		return status;
	}

	public void setStatus(ExternalOrderStatus status) {
		this.status = status;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public List<ExternalOrderElement> getElements() {
		return elements;
	}

	public void setElements(List<ExternalOrderElement> elements) {
		this.elements = elements;
	}

}
