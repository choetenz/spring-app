package com.tenzin.app.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerData {

	private Long id;
	private String firstName;
	private String lastName;
	private String ssn;
	
	public CustomerData( ) {}
	
	public CustomerData(String firstName, String lastName, String ssn) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "CustomerData [firstName=" + firstName + ", lastName=" + lastName + ", ssn=" + ssn + "]";
	}

	
}
