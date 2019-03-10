package com.elbon.HibernateMain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="employee")
@NamedQueries(  
	    {  
	        @NamedQuery(  
	        name = "FETCH_EMPLOYEE_BY_NAME",  
	        query = "from Employee e where e.firstName = :name"  
	        ),  
	    	@NamedQuery(
	    	name = "DELETE_EMPLOYEE_BY_NAME",
	    	query = "delete from Employee e where e.firstName = :name"
	    	),
	    	@NamedQuery(
	    	name = "UPDATE_EMPLOYEE_BY_FNAME",
	    	query = "update Employee e set e.lastName=:lname where e.firstName=:fname"
	    	)
	    }
	    )
public class Employee {
	
	@Id
	private int id;
	
	@Column(name="fname")
	private String firstName;
	
	@Column(name="lname")
	private String lastName;

	
	public Employee(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Employee(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	

	public Employee() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}
