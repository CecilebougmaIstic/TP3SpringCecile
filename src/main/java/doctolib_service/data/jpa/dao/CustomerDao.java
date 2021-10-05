package doctolib_service.data.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import doctolib_service.data.jpa.domain.Customer;



public interface CustomerDao extends JpaRepository <Customer, Long>{
	
	public List<Customer> findByLastName(String lastName);
	public List<Customer> findByFirstName(String firstName);
	public List<Customer> findByLastNameContaining(String lastName);
	public List<Customer> findByFirstNameContaining(String firstName);
	public List<Customer> findByEmailContaining(String firstName);
	public List<Customer> findByLastNameContainingAndFirstNameContaining(String lastName, String firstName);
}
