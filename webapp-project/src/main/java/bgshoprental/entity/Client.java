package bgshoprental.entity;

import javax.persistence.*;

@Entity
@Table(name = "Klienci")
public class Client {

	@Id
	@Column(name = "KlientID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "Imie")
	private String firstName;

	@Column(name = "Nazwisko")
	private String lastName;

	@Column(name = "Telefon")
	private String phoneNumber;

	@Column(name = "Email")
	private String email;

	@Column(name = "Haslo")
	private String passwordHash;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

}
