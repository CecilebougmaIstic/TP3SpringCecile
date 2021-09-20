package fr.istic.taa.jaxrs.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.istic.taa.jaxrs.dao.generic.CustomerDao;
import fr.istic.taa.jaxrs.dao.generic.WorkerDao;
import fr.istic.taa.jaxrs.domain.Customer;
import fr.istic.taa.jaxrs.domain.Worker;
import io.swagger.v3.oas.annotations.Parameter;

@Path("/customer")
@Produces({"application/json", "application/xml"})

public class CustomerResource {
	
	/*To obtain a one customer*/
	
	@GET
	@Path("/{customerId}")	
	@Produces({"application/json"})
	public Customer getCustomerById(@PathParam("customerId") Long customerId)  {
		CustomerDao cusDao = new CustomerDao();
		Customer lookCustomer = cusDao.findOne(customerId);
	      return lookCustomer;
	  }
	
	/*To obtain all customer in DB*/
	
	 
	  /*To recuperate All Worker*/
	  
	  @GET
	  @Path("/all")
	  @Consumes("application/json")
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Customer> getallCustomer()  {
		  CustomerDao listCustomer = new CustomerDao();
		  List<Customer> cus = (List<Customer>) listCustomer.findAll();
		  return cus;
	  }
	      
	 /*Ta save a customer*/
	 
	  @POST
	  @Consumes("application/json")
	  @Produces({MediaType.TEXT_PLAIN})
	  public String addCustomer(@Parameter(required = true) Customer customer) {
		  
		  Long idCustomer=(long) 0;
		  CustomerDao cusDao = new CustomerDao();
			Customer customerD = new Customer(customer.getLastName(),customer.getFirstName(), customer.getEmail(),
					customer.getPassword(),customer.getBankCard());
			
			cusDao.save(customerD);
			idCustomer= customer.getId();
		 System.out.println(customer.getEmail());
	    // add pet
	    return  "Le client a été créé avec succès"+ " "+ "son iddentifiant est:" + 
	    		+ idCustomer;
	  }  
	  
	  
	  
}
