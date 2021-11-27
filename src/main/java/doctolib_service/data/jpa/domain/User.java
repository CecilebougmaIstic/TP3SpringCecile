package doctolib_service.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/*This class manage a User*/
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="User_type")
@Table
public  abstract class User implements Serializable{
	
	/*Variables*/
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String password;
	
	
	/*Constructors*/
	public User() {
		super();
		
	}
	public User(long id, String namefirstName, String lastName, String email, String password) {
		super();
		this.id = id;
		this.firstName = namefirstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	public User(String namefirstName, String lastName, String email, String password) {
		super();
		this.firstName = namefirstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	public User(String email, String password) {
		super();
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String namefirstName) {
		this.firstName = namefirstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
