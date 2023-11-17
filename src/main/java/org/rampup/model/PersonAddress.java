package org.rampup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/** * */
@Entity
@Table(name = "PERSON_ADDRESS")
public class PersonAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String street;
	private String city;
	@NotNull
	@NotEmpty
	@Pattern(regexp = "\\d{5}")
	private String zipCode;
	private String country;

	/** * */
	public PersonAddress() {
		super();
	}

	/**
	 * * @param id * @param street * @param city * @param zipCode * @param country
	 */
	public PersonAddress(String street, String city, String zipCode, String country) {
		super();
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
		this.country = country;
	}

	/** * @return the id */
	public long  getId() {
		return id;
	}

	/** * @param id the id to set */
	public void setId(long  id) {
		this.id = id;
	}

	/** * @return the street */
	public String getStreet() {
		return street;
	}

	/** * @param street the street to set */
	public void setStreet(String street) {
		this.street = street;
	}

	/** * @return the city */
	public String getCity() {
		return city;
	}

	/** * @param city the city to set */
	public void setCity(String city) {
		this.city = city;
	}

	/** * @return the zipCode */
	public String getZipCode() {
		return zipCode;
	}

	/** * @param zipCode the zipCode to set */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/** * @return the country */
	public String getCountry() {
		return country;
	}

	/** * @param country the country to set */
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "PersonAddress [id=" + id + ", street=" + street + ", city=" + city + ", zipCode=" + zipCode
				+ ", country=" + country + "]";
	}
}
