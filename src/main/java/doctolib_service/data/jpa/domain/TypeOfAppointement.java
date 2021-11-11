package doctolib_service.data.jpa.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import com.fasterxml.jackson.annotation.JsonIgnore;

/*with this class a professionnal can manage a type of an appointement
 * describe and duration of an appointement
 *
 */
@Entity
public class TypeOfAppointement implements Serializable{
	
	
	/*Variables*/
	 @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String appointementDescription;
	private int appointementLimit;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="worker_id", nullable=false)
	@JsonBackReference(value="worker-typeOfAppointement") //ignore
	private Worker worker;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "typeAppointement",
			cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
	@ElementCollection
	@JsonBackReference(value="appointement-typeAppointement")
	private List<Appointement> appointement = new ArrayList<Appointement>();	
	
	
	
	


	/*Constructors*/
	public TypeOfAppointement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TypeOfAppointement(String appointementDescription, int appointementLimit,Worker worker) {
		super();
		//this.id = id;
		this.appointementDescription = appointementDescription;
		this.appointementLimit = appointementLimit;
		this.worker= worker;
	}

	public TypeOfAppointement(String appointementDescription, int appointementLimit) {
		super();
		this.appointementDescription = appointementDescription;
		this.appointementLimit = appointementLimit;
	}
	
	
	/*Getters && setters*/

    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAppointementDescription() {
		return appointementDescription;
	}

	public void setAppointementDescription(String appointementDescription) {
		this.appointementDescription = appointementDescription;
	}

	public int getAppointementLimit() {
		return appointementLimit;
	}

	public void setAppointementLimit(int appointementLimit) {
		this.appointementLimit = appointementLimit;
	}
	
	public List<Appointement> getAppointement() {
		return appointement;
	}

	public void setAppointement(List<Appointement> appointement) {
		this.appointement = appointement;
	}
	
	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	@Override
	public String toString() {
		return "TypeOfAppointement [id=" + id + ", appointementDescription=" + appointementDescription
				+ ", appointementLimit=" + appointementLimit + ", appointement=" + appointement + ", worker=" + worker.toString()
				+ "]";
	}
	
	
	
	
	
	
}
