package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer{
	
	@Id // diciendole que este atributo es un ID
	@GeneratedValue(strategy=GenerationType.AUTO) // diciendole que este atributo es autoincrementable
	private long id;
	private String firstName;
	private String lastName;
	
	protected Customer() { }
	
	public Customer(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public String toString(){
		return String.format("Customer[id=%d, firstName='%s', lastName='%s']", 
				id, firstName, this.lastName);
	}
	
}