package doctolib_service.data.jpa.domain;
import java.io.Serializable;
import java.util.Date;


import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/*This class manage an appointement */
@Entity
public class Appointement implements Serializable{
	
	/*Variables*/
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	//@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date appointementStart;
	//@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date appointementEnd;
	
	private String appointementPlace;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="typeAppointement_id", nullable=false)
	//@JsonManagedReference(value="appointement-typeAppointement")
	private TypeOfAppointement typeAppointement;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id", nullable=false)
	//@JsonManagedReference(value="customer-appointement")
	private Customer customer;	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="worker_id", nullable=false)
	//@JsonManagedReference(value="worker-appointement")
	private Worker worker;
	
	/*Construtors*/	
	
	public Appointement() {
		super();
	}
	public Appointement(long id, Date appointementStart, Date appointementEnd, String appointementPlace,
			TypeOfAppointement typeAppointement, Customer customer, Worker worker) {
		super();
		this.id = id;
		this.appointementStart = appointementStart;
		this.appointementEnd = appointementEnd;
		this.appointementPlace = appointementPlace;
		this.typeAppointement = typeAppointement;
		this.customer = customer;
		this.worker = worker;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getAppointementStart() {
		return appointementStart;
	}

	public void setAppointementStart(Date appointementStart) {
		this.appointementStart = appointementStart;
	}

	public Date getAppointementEnd() {
		return appointementEnd;
	}

	public void setAppointementEnd(Date appointementEnd) {
		this.appointementEnd = appointementEnd;
	}

	public String getAppointementPlace() {
		return appointementPlace;
	}

	public void setAppointementPlace(String appointementPlace) {
		this.appointementPlace = appointementPlace;
	}

	public TypeOfAppointement getTypeAppointement() {
		return typeAppointement;
	}

	public void setTypeAppointement(TypeOfAppointement typeAppointement) {
		this.typeAppointement = typeAppointement;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	
	@Override
	public String toString() {
		return "Appointement [id=" + id + ", appointementStart=" + appointementStart + ", appointementEnd="
				+ appointementEnd + ", appointementPlace=" + appointementPlace + ", typeAppointement="
				+ typeAppointement + ", customer=" + customer + ", worker=" + worker + "]";
	}


	
	
	
	
	
	
	
	

	

}
