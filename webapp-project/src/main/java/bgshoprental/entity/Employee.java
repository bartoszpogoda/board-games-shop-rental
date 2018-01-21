package bgshoprental.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Pracownicy")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PracownikID")
	private int id;

	@Column(name = "Email")
	private String email;

	@Column(name = "Haslo")
	private String password;

	@Column(name = "Imie")
	private String firstName;

	@Column(name = "Nazwisko")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@Column(name = "DataZatrudnienia")
	private Calendar hireDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "DataZwolnienia")
	private Calendar fireDate;
	
    @OneToOne(optional=true, mappedBy="employee")
	private Manager manager;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Calendar getHireDate() {
		return hireDate;
	}

	public void setHireDate(Calendar hireDate) {
		this.hireDate = hireDate;
	}

	public Calendar getFireDate() {
		return fireDate;
	}

	public void setFireDate(Calendar fireDate) {
		this.fireDate = fireDate;
	}
	
	public boolean isManager() {
		return this.manager != null;
	}

}
