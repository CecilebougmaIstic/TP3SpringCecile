package doctolib_service.data.jpa.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import doctolib_service.data.jpa.dao.CustomerDao;
import doctolib_service.data.jpa.domain.Customer;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")

public class CustomerController {
	@Autowired
	private CustomerDao  customerDao;

	/*Get a customer by id*/
	@ApiOperation(value = "Récupère un Customer grâce à son ID à "
			+ "condition que celui-ci existe dans la base!")
	@RequestMapping(value="/customers/{Id}")
	@ResponseBody
	
	public Customer getCustomerById(@PathVariable("Id") Long id)  {

		try {
			Optional<Customer> customer = customerDao.findById(id);
			if(customer.isPresent()) 
			{
				return customer.get();

			}
			throw  new ResponseStatusException(HttpStatus.NOT_FOUND);

		}
		catch (Exception ex) {
			throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*Get a list of customers by they lastName or firstName or email*/

	@ApiOperation(value = "Récupère une liste Customer grâce à un filtre sur les paramètres firstName, lastName et email "
			+ "condition que celui-ci existe dans la base!")
	
	@GetMapping("/customers")
	
	public ResponseEntity <List<Customer>> getCustomerByFirstname(@RequestParam(required = false) String lastName,
			@RequestParam(required = false) String firstName,@RequestParam(required = false) String email) {
		List<Customer> customers = new ArrayList<Customer>();
		try {
			if (lastName!=null && firstName!=null)
				customerDao.findByLastNameContainingAndFirstNameContaining(lastName,firstName).forEach(customers::add);
			else 
				if (lastName!=null)
					customerDao.findByLastNameContaining(lastName).forEach(customers::add);
				else
					if(firstName!=null )
						customerDao.findByFirstNameContaining(firstName).forEach(customers::add);
					else
						if(email!=null)
							customerDao.findByEmailContaining(email).forEach(customers::add);
						else
							customerDao.findAll().forEach(customers::add);
			if (customers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(customers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Crer un customer")
	@PostMapping("/customers")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		try {

			//customerDao.save(customer);
			Customer cust = customerDao
					.save(new Customer(customer.getLastName(),customer.getFirstName(), customer.getEmail(),
							customer.getPassword(),customer.getBankCard()));
			return new ResponseEntity<>(customer, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/*Update a Customer by id*/
	@ApiOperation(value = "Modifier un Customer par son id ")
	@PutMapping("/customers/{id}")
	@ResponseBody
	public String updateCustomer(@PathVariable("id") long id, String firstName,String lastName,String email,String password, String bankCard) {
		//Long idCustomer;
		try {

			Customer customer = customerDao.findById(id).get();
			customer.setFirstName(firstName);
			customer.setLastName(lastName); 
			customer.setEmail(email);
			customer.setPassword(password);
			customer.setBankCard(bankCard);
			customerDao.save(customer);
			//idCustomer= customer.getId();
		}catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		//return idCustomer + " "+ "Customer succesfully updated!";
		return "Customer succesfully updated!";
	}


	/*Delete a customer*/
	@ApiOperation(value = "Supprimer un Customer par son id ")
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<HttpStatus> deleteCustomerById(@PathVariable("id") long id) {
		try {
			customerDao.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*Delete all customers*/
	@ApiOperation(value = "Supprimer tous les Customer par son id ")
	@DeleteMapping("/customers")
	@ResponseBody
	public ResponseEntity<HttpStatus> deleteAllCustomers() {
		try {
			customerDao.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
