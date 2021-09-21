package doctolib_service.data.jpa.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Calendar;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/*This class manage an appointement */
@Entity
public class Appointement implements Serializable{
	
	/*Variables*/
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private Calendar appointementStart;
	private Calendar appointementEnd;
	private String appointementPlace;
	
	@ManyToOne
	@JoinColumn(name="typeAppointement_id", nullable=false)
	private TypeOfAppointement typeAppointement;
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;	
	@ManyToOne
	@JoinColumn(name="worker_id", nullable=false)
	private Worker worker;
	/*Construtors*/	
	

	public Appointement(Calendar appointementStart, Calendar appointementEnd, String appointementPlace) {
		super();
		this.appointementStart = appointementStart;
		this.appointementEnd = appointementEnd;
		this.appointementPlace = appointementPlace;
	}


	public Appointement(Calendar appointementStart, Calendar appointementEnd, String appointementPlace,
			TypeOfAppointement typeAppointement, Customer customer) {
		super();
		this.appointementStart = appointementStart;
		this.appointementEnd = appointementEnd;
		this.appointementPlace = appointementPlace;
		this.typeAppointement = typeAppointement;
		this.customer = customer;
	}

	public Appointement(Calendar appointementStart, Calendar appointementEnd, String appointementPlace,
			TypeOfAppointement typeAppointement, Worker worker) {
		super();
		this.appointementStart = appointementStart;
		this.appointementEnd = appointementEnd;
		this.appointementPlace = appointementPlace;
		this.typeAppointement = typeAppointement;
		this.worker = worker;
	}
	
	

	/*Getters && Setters*/

    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Calendar getAppointementStart() {
		return appointementStart;
	}

	public void setAppointementStart(Calendar appointementStart) {
		this.appointementStart = appointementStart;
	}

	public Calendar getAppointementEnd() {
		return appointementEnd;
	}

	public void setAppointementEnd(Calendar appointementEnd) {
		this.appointementEnd = appointementEnd;
	}

	public String getAppointementPlace() {
		return appointementPlace;
	}

	public void setAppointementPlace(String appointementPlace) {
		this.appointementPlace = appointementPlace;
	}
	
	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public TypeOfAppointement getTypeAppointement() {
		return typeAppointement;
	}

	public void setTypeAppointement(TypeOfAppointement typeAppointement) {
		this.typeAppointement = typeAppointement;
	}

	@Override
	public String toString() {
		return "Appointement [id=" + id + ", appointementStart=" + appointementStart + ", appointementEnd="
				+ appointementEnd + ", appointementPlace=" + appointementPlace + ", typeAppointement="
				+ typeAppointement + ", customer=" + customer.toString() + ", worker=" + worker.toString() + "]";
	}

	
	
	
	
	
	
	

	

}
