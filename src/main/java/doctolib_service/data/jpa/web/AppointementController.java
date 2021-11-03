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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import doctolib_service.data.jpa.aspectJ.Supervision;
import doctolib_service.data.jpa.dao.AppointementDao;
import doctolib_service.data.jpa.dao.CustomerDao;
import doctolib_service.data.jpa.dao.WorkerDao;
import doctolib_service.data.jpa.domain.Appointement;
import doctolib_service.data.jpa.domain.Customer;
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

	/*Get an appointment by id*/
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

	/*Get all appointments of a worker*/
	/*List of a worker appointments*/	

	@ApiOperation(value = "Récupère tous les appointements d'un Worker à "
			+ "condition que celui-ci existe dans la base!")
	@RequestMapping(value="/appointements/workers/{workerId}")
	public ResponseEntity <List<Appointement>> getAllAppointementsOfWorker(@PathVariable("workerId") Long workerId)
	{
		List<Appointement> appointements = new ArrayList<Appointement>();

		try {
			Optional<Worker> _worker=workerDao.findById(workerId);
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
			//System.out.println("******************"+appointements.get(0).getAppointementStart());
			return new ResponseEntity<>(appointements, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/*List of a customer appointments*/	

	@ApiOperation(value = "Récupère tous les appointements d'un Customer à "
			+ "condition que celui-ci existe dans la base!")
	@GetMapping(value="/appointements/customers/{customerId}")
	@Supervision(dureeMillis = 5)
	public ResponseEntity <List<Appointement>> getAllAppointementsOfCustomer(@PathVariable("customerId") Long customerId)
	{
		System.out.println("réalise un traitement important pour l'application"
				+"Car affiche tous les Appointements d'un Customer");
		List<Appointement> appointements = new ArrayList<Appointement>();
	
		try {
			/*recuperate customer id*/
			Optional<Customer> _customer=customerDao.findById(customerId);
			//find in table Appointment, all appointments for a user by his id.		
			if(_customer.isPresent()) {

				appointementDao.findByCustomer(_customer.get()).forEach(appointements::add);			
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


	/*Create an Appointment by a Customer */

	@ApiOperation(value = "Create an Appointement")
	@PostMapping("/appointements")

	public ResponseEntity<Appointement> createAppointement(@RequestBody  Appointement appointement) {
		try {
			Appointement _appointement = appointementDao.save(new Appointement(0,appointement.getAppointementStart(),appointement.getAppointementEnd(),
					appointement.getAppointementPlace(),appointement.getTypeAppointement(), appointement.getCustomer(),appointement.getWorker()));

			return new ResponseEntity<>(_appointement, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/*Delete all Appointments*/
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

	/*Delete an Appointment*/

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
