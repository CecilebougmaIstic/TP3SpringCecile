package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/*This class create a customer*/
@Entity

@DiscriminatorValue("C")
public class Customer extends User implements Serializable{
	
	/*variables*/
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String bankCard;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
	@ElementCollection
	private List<Appointement> appointement = new ArrayList<Appointement>();
	
	/*Constructor*/

	public Customer(long id, String firstName, String lastName, String email, String password,String bankCard) {
		super();
		this.id = id;
		this.firstName=firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.bankCard = bankCard;
	}
	
	public Customer(String firstName, String lastName, String email, String password,String bankCard) {
		super();
		this.firstName=firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.bankCard = bankCard;
	}
	
	public Customer() {
		super();

	}
	
	
	/*Getters && Setters*/

    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	
	public List<Appointement> getAppointement() {
		return appointement;
	}

	public void setAppointement(List<Appointement> appointement) {
		this.appointement = appointement;
	}

	
	
	

}
