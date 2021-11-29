package doctolib_service.data.jpa.domain;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/*This class create a customer*/
@Entity
@Table
@DiscriminatorValue("C")
public class Customer extends User implements Serializable{

	/*variables*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String bankCard;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
	@ElementCollection
	@JsonBackReference(value="customer-appointement")
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

	@Override
	public String toString() {
		return "Customer [id=" + id + ", bankCard=" + bankCard + ", appointement=" + appointement + "]";
	}


}
