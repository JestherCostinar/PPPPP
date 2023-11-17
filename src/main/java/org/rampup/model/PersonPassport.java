package org.rampup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/** * */
@Entity
@Table(name = "PERSON_PASSPORT")
public class PersonPassport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@NotEmpty
	@Pattern(regexp = "\\d{3}-\\d{3}-\\d{3}-\\d{3}")
	private String passportNumber;
	@OneToOne
	@JoinColumn(name = "person_id")
	@JsonIgnore
	private Person person;

	/** * */
	public PersonPassport() {
		super();
	}

	/** * @param id * @param passportNumber * @param person */
	public PersonPassport(String passportNumber, Person person) {
		super();
		this.passportNumber = passportNumber;
		this.person = person;
	}

	/** * @return the id */
	public long getId() {
		return id;
	}

	/** * @param id the id to set */
	public void setId(long id) {
		this.id = id;
	}

	/** * @return the passportNumber */
	public String getPassportNumber() {
		return passportNumber;
	}

	/** * @param passportNumber the passportNumber to set */
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	/** * @return the person */
	public Person getPerson() {
		return person;
	}

	/** * @param person the person to set */
	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "PersonPassport [id=" + id + ", passportNumber=" + passportNumber + ", person=" + person + "]";
	}
	
	
}
