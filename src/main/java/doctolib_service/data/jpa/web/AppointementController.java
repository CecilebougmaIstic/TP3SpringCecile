package doctolib_service.data.jpa.web;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import doctolib_service.data.jpa.aspectJ.Supervision;
import doctolib_service.data.jpa.dao.AppointementDao;
import doctolib_service.data.jpa.dao.CustomerDao;
import doctolib_service.data.jpa.dao.WorkerDao;
import doctolib_service.data.jpa.domain.Appointement;
import doctolib_service.data.jpa.domain.Customer;
import doctolib_service.data.jpa.domain.TypeOfAppointement;
import doctolib_service.data.jpa.domain.User;
import doctolib_service.data.jpa.domain.Worker;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")

public class AppointementController {
	@Autowired
	AppointementDao appointementDao;
	@Autowired
	WorkerDao workerDao;
	@Autowired
	CustomerDao customerDao;
	/*
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}*/


	/*Get an appointement by id*/
	@ApiOperation(value = "Récupère un Appointement grâce à son ID à "
			+ "condition que celui-ci existe dans la base!")
	@RequestMapping(value="/appointements/{Id}",consumes={"application/json"})
	@ResponseBody
	public Appointement getAppointementById(@PathVariable("Id") Long id)  {

		try {

			Optional<Appointement> appointement = appointementDao.findById(id);
			if(appointement.isPresent()) 
			{
				return appointement.get();

			}
			throw  new ResponseStatusException(HttpStatus.NOT_FOUND);

		}
		catch (Exception ex) {
			throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*Get all appointements of a worker*/
	/*List of a worker' appointements*/	

	@ApiOperation(value = "Récupère tous les appointements d'un Worker à "
			+ "condition que celui-ci existe dans la base!")
	@RequestMapping(value="/appointements/workers/{workerId}")
	public ResponseEntity <List<Appointement>> getAllAppointementsOfWorker(@PathVariable("workerId") Long workerId)
	{
		List<Appointement> appointements = new ArrayList<Appointement>();
		long recupWorkerId;


		try {
			/*récupérer id de l'utilisateur*/
			//User recupUser= new User();
			Optional<Worker> _worker=workerDao.findById(workerId);
			//recupUserId =recupUser.getId();
			//find in table Appointement, all appointements for a user by his id.		
			if(_worker.isPresent()) {

				appointementDao.findAppointementByWorkerId(workerId).forEach(appointements::add);;

			} else

				throw  new ResponseStatusException(HttpStatus.NOT_FOUND);

			if(appointements.isEmpty()) 
			{
				throw  new ResponseStatusException(HttpStatus.NOT_FOUND); 


			}

			return new ResponseEntity<>(appointements, HttpStatus.OK);
		}
		catch (Exception ex) {
			throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	@GetMapping("/appointements")
	@ResponseBody
	public ResponseEntity<List<Appointement>> getAllAppointements() {
		try {
			List<Appointement> appointements = new ArrayList<Appointement>();

			
			appointementDao.findAll().forEach(appointements::add);

			if (appointements.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			System.out.println("******************"+appointements.get(0).getAppointementStart());
			return new ResponseEntity<>(appointements, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/*List of a customer' appointements*/	

	@ApiOperation(value = "Récupère tous les appointements d'un Customer à "
			+ "condition que celui-ci existe dans la base!")
	@GetMapping(value="/appointements/customers/{customerId}")
	@Supervision(dureeMillis = 5)
	public ResponseEntity <List<Appointement>> getAllAppointementsOfCustomer(@PathVariable("customerId") Long customerId)
	{
		System.out.println("réalise un traitement important pour l'application"
				+"Car affiche tous les Appointements d'un Customer");
		List<Appointement> appointements = new ArrayList<Appointement>();
		long recupCustomerId;


		try {
			/*récupérer id du client*/
			Optional<Customer> _customer=customerDao.findById(customerId);
			//find in table Appointement, all appointements for a user by his id.		
			if(_customer.isPresent()) {
				System.out.println("******************");
			
				appointementDao.findByCustomer(_customer.get()).forEach(appointements::add);;
			
				
					
					
			} else

				return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

			if(appointements.isEmpty()) 
			{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 

			}

			return new ResponseEntity<>(appointements, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	/*Create an Appointement by a Customer */

	@ApiOperation(value = "Create an Appointement")
	@PostMapping("/appointements")

	public ResponseEntity<Appointement> createAppointement(@RequestBody  Appointement appointement) {
		try {

			// workerDao.save(customer);
			System.out.println("ghhdddd");
			//System.out.println(appointement.toString());

			Appointement _appointement = appointementDao.save(new Appointement(0,appointement.getAppointementStart(),appointement.getAppointementEnd(),
					appointement.getAppointementPlace(),appointement.getTypeAppointement(), appointement.getCustomer(),appointement.getWorker()));
			 
			return new ResponseEntity<>(_appointement, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/*Delete all Appointements*/
	@ApiOperation(value = "delete all appointements ")
	@DeleteMapping("/appointements")
	@ResponseBody
	public ResponseEntity<String> deleteAllAppointement() {
		try {
			appointementDao.deleteAll();
			return new ResponseEntity<>(" succesfully deleted!",HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>("Error deleting the appointement:" + e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	/*Delete an Appointement*/

	@ApiOperation(value = "delete an appointement by id ")
	@DeleteMapping("/appointements/{id}")

	public ResponseEntity<String> deleteAppointementById(@PathVariable("id") long id) {
		Optional<Appointement> appointementToDelete = appointementDao.findById(id);
		if (!appointementToDelete.isPresent()) {
			return new ResponseEntity<>("Error deleting:"+" "+ id,HttpStatus.NOT_FOUND);
		}else { 
			appointementDao.deleteById(id);

			return new ResponseEntity<>( id+ " "+" succesfully deleted!",HttpStatus.NO_CONTENT);
		}

	}


	
}
