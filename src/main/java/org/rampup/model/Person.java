package org.rampup.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/** * */
@Entity
@Table(name = "PERSON")
@NamedQueries({ @NamedQuery(name = "SearchForPersons", query = "SELECT p from Person p"),
		@NamedQuery(name = "FindPerson", query = "SELECT p FROM Person p where p.id =:id") })
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	@NotEmpty
	@Size(max = 30, message = "First name max is 30 character")
	private String firstName;
	@NotNull
	@NotEmpty
	@Size(max = 30, min = 3, message = "First name max is 30 character and minimun is 3 character")
	private String lastName;
	@Email(message = "Invalid Email Format")
	@Size(max = 50, message = "Email must be at most 50 characters")
	private String email;
	@OneToMany(targetEntity = PersonAddress.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<PersonAddress> addresses = new HashSet<>();
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private PersonPassport personPassport;

	public Person() {
		super();
	}

	/**
	 * * @param id * @param firstName * @param lastName * @param email * @param
	 * addresses * @param personPassport
	 */
	
	
	public Person(String firstName, String lastName, String email, Set<PersonAddress> addresses,
			PersonPassport personPassport) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.addresses = addresses;
		this.personPassport = personPassport;
	}

	public Person(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Person(long id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	/** * @return the id */
	public long getId() {
		return id;
	}

	/** * @param id the id to set */
	public void setId(long id) {
		this.id = id;
	}

	/** * @return the firstName */
	public String getFirstName() {
		return firstName;
	}

	/** * @param firstName the firstName to set */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** * @return the lastName */
	public String getLastName() {
		return lastName;
	}

	/** * @param lastName the lastName to set */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** * @return the email */
	public String getEmail() {
		return email;
	}

	/** * @param email the email to set */
	public void setEmail(String email) {
		this.email = email;
	}

	/** * @return the addresses */
	public Set<PersonAddress> getAddresses() {
		return addresses;
	}

	/** * @param addresses the addresses to set */
	public void setAddresses(Set<PersonAddress> addresses) {
		this.addresses = addresses;
	}

	/** * @return the personPassport */
	public PersonPassport getPersonPassport() {
		return personPassport;
	}

	/** * @param personPassport the personPassport to set */
	public void setPersonPassport(PersonPassport personPassport) {
		this.personPassport = personPassport;
	}

	@Override
	public String toString() {
		return "[Person (" + "id = " + id + ", firstName = " + firstName + ", lastName = " + lastName + ", email = "
				+ email + ", personAddress = " + addresses + " + " + "]" + '\n';
	}
}