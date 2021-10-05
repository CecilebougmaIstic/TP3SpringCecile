package doctolib_service.data.jpa.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import doctolib_service.data.jpa.dao.AppointementDao;
import doctolib_service.data.jpa.dao.WorkerDao;
import doctolib_service.data.jpa.domain.Appointement;
import doctolib_service.data.jpa.domain.TypeOfAppointement;
import doctolib_service.data.jpa.domain.Worker;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")

public class AppointementController {
	@Autowired
	AppointementDao appointementDao;
	@Autowired
	WorkerDao workerDao;
	
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

	/*Get all appointements for a worker*/
	
	@ApiOperation(value = "Récupère tous les appointements d'un user à "
			+ "condition que celui-ci existe dans la base!")
	@RequestMapping(value="/appointements/worker/{workerId}")
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
	/*Create an Appointement by a Customer */
	
	@ApiOperation(value = "Create an Appointement")
	@PostMapping("/appointements")
	public ResponseEntity<Appointement> createAppointement(@RequestBody Appointement appointement) {
		try {

			// workerDao.save(customer);
	
			Appointement _appointement = appointementDao.save(new Appointement(appointement.getAppointementStart(),appointement.getAppointementEnd(),
					appointement.getAppointementPlace(),appointement.getTypeAppointement(), appointement.getCustomer()));

			return new ResponseEntity<>(_appointement, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/*List of a worker' appointements*/	
}
