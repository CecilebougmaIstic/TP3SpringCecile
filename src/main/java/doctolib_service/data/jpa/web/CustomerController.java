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
import doctolib_service.data.jpa.domain.Worker;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")

public class CustomerController {
	@Autowired
	private CustomerDao  customerDao;

	/*Get a customer by id*/
	@ApiOperation(value = "Get a customer by his id "
			+ "condition: if this customer exits in base.")
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

	@ApiOperation(value = "Get a listof customer, filtred on @parametres firstName, lastName and email "
			+ "condition: if this customer exits in base!")

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

	@ApiOperation(value = "Create a customer")
	@PostMapping("/customers")
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
		try {

			//customerDao.save(customer);
			Customer cust = customerDao
					.save(new Customer(customer.getFirstName(),customer.getLastName(), customer.getEmail(),
							customer.getPassword(),customer.getBankCard()));
			return new ResponseEntity<>("User succesfully created with id = " +customer.getId(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error creating the user: "+" "+customer.getId(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/*Update a Customer by id*/
	@ApiOperation(value = "Modified a Customer by id ")
	@PutMapping("/customers/{id}")
	@ResponseBody
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody  Customer customer) {


		Optional<Customer> customerData = customerDao.findById(id);
		if(customerData.isPresent())
		{
			Customer _customer=customerData.get();
			_customer.setFirstName(customer.getFirstName());
			_customer.setLastName(customer.getLastName()); 
			_customer.setEmail(customer.getEmail());
			_customer.setPassword(customer.getPassword());
			_customer.setBankCard(customer.getBankCard());
			return new ResponseEntity<>(customerDao.save(_customer), HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}


	/*Delete a customer by id*/
	@ApiOperation(value = "D ")
	@DeleteMapping("/customers/{id}")
	
	public ResponseEntity<String> deleteCustomerById(@PathVariable("id") long id) {
		Optional<Customer> customerData = customerDao.findById(id);
		if (!customerData.isPresent()) {
			return new ResponseEntity<>("Error deleting:"+" "+ id,HttpStatus.NOT_FOUND);
		}else { 
			customerDao.deleteById(id);
			return new ResponseEntity<>( id+ " "+" succesfully deleted!",HttpStatus.NO_CONTENT);
		}
		
	}

	/*Delete all customers*/
	@ApiOperation(value = "Supprimer tous les Customer par son id ")
	@DeleteMapping("/customers")
	@ResponseBody
	public ResponseEntity<String> deleteAllCustomers() {
		try {
			customerDao.deleteAll();
			return new ResponseEntity<>(" succesfully deleted!",HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>("Error deleting the user:" + e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
