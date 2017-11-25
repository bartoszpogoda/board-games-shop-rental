package bgshoprental.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Dostawcy")
public class Supplier {

	@Id
	@Column(name = "DostawcaNazwa", length = 30)
	private String name;

	@Column(name = "Telefon", length = 9)
	private String phoneNumber;

	@Column(name = "Ulica", length = 30)
	private String street;

	@Column(name = "NumerDomu")
	private int homeNumber;

	@Column(name = "NumerMieszkania")
	private Integer apartmentNumber;

	@Column(name = "KodPocztowy", length = 5)
	private String postalCode;

	@Column(name = "Miasto", length = 30)
	private String city;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(int homeNumber) {
		this.homeNumber = homeNumber;
	}

	public Integer getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(Integer apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
