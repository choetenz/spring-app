package com.tenzin.app.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
@NamedQuery(name=QueryConstant.CUSTOMER_SEARCH,query="select c from CustomerEntity c where UPPER(c.firstName) like UPPER(:searchStr) OR UPPER(c.lastName) like UPPER(:searchStr)")
@NamedNativeQuery(name=QueryConstant.CUSTOMER_GET_ALL,query="select * from Customer",resultClass = CustomerEntity.class)
public class CustomerEntity {
	@Id
	@Column(name = "CUSTOMER_ID")
	@SequenceGenerator(name = "custSeq", sequenceName = "JPA_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "custSeq")
	private Long pk;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "SSN")
	private String ssn;

	/*@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
	private CustomerDetail detail;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
	private List<OrderEntity> orders;*/

	public CustomerEntity() {
		super();
	}

	public CustomerEntity(String firstName, String lastName, String ssn) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(long pk) {
		this.pk = pk;
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

	/*public CustomerDetail getDetail() {
		return detail;
	}

	public void setDetail(CustomerDetail detail) {
		this.detail = detail;
	}

	public List<OrderEntity> getOrders() {
		if (this.orders == null) {
			// List<OrderEntity> order = new ArrayList<OrderEntity>();
			orders = new ArrayList<OrderEntity>();
		}
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}*/

	@Override
	public String toString() {
		return "CustomerEntity [pk=" + pk + ", firstName=" + firstName + ", lastName=" + lastName + ", ssn=" + ssn
				+ "]";
	}

}

