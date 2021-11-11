package doctolib_service.data.jpa.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
@DiscriminatorValue("W")
//@Component
public class Worker extends User implements Serializable{

	/*Variables*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String job;
	private String bakRib;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "worker", cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
	@ElementCollection
	//@JsonManagedReference(value="worker-typeOfAppointement")
	private List<TypeOfAppointement> typeAppointements = new ArrayList<TypeOfAppointement>();
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "worker", cascade = CascadeType.PERSIST)
	@ElementCollection
	@JsonBackReference(value="worker-appointement")
	private List<Appointement> appointements = new ArrayList<Appointement>();


	public Worker() {
		super();

	}


	/*Constructor*/
	public Worker(long id, String job, String bakRib) {
		super();
		this.id = id;
		this.job = job;
		this.bakRib = bakRib;

	}


	public Worker(String firstName, String lastName, String email, String password,String job, String bakRib) {
		super();
		this.job = job;
		this.bakRib = bakRib;
		this.firstName=firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public Worker(long id,String firstName, String lastName, String email, String password,String job, String bakRib) {
		super();
		this.id =id;
		this.job = job;
		this.bakRib = bakRib;
		this.firstName=firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	/*Getters && Setters*/


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getJob() {
		return job;
	}


	public void setJob(String job) {
		this.job = job;
	}


	public String getBakRib() {
		return bakRib;
	}


	public void setBakRib(String bakRib) {
		this.bakRib = bakRib;
	}



	public List<Appointement> getAppointements() {
		return appointements;
	}


	public List<TypeOfAppointement> getTypeAppointements() {
		return typeAppointements;
	}


	public void setTypeAppointements(List<TypeOfAppointement> typeAppointements) {
		this.typeAppointements = typeAppointements;
	}


	public void setAppointements(List<Appointement> app) {
		this.appointements = app;
	}





	@Override
	public String toString() {
		return "Worker [id=" + id + ", job=" + job + ", bakRib=" + bakRib + ", typeAppointements="
				+ typeAppointements + ", appointements=" + appointements + "]";
	}










}
