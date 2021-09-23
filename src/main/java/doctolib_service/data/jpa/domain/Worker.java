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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
@DiscriminatorValue("W")
public class Worker extends User implements Serializable{

	/*Variables*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String job;
	private String bakRib;

	@OneToMany(mappedBy = "worker", cascade = CascadeType.PERSIST)
	@ElementCollection
	
	private List<TypeOfAppointement> typeAppointements = new ArrayList<TypeOfAppointement>();
	/*@OneToMany(mappedBy = "worker", cascade = CascadeType.PERSIST)*/
	@ElementCollection
	@JsonIgnore
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
