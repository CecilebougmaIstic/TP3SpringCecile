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

import doctolib_service.data.jpa.domain.Worker;
import doctolib_service.data.jpa.service.WorkerDao;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")

public class WorkerController {
	@Autowired
	private WorkerDao  workerDao;

	/*Get a worker by id*/
	@ApiOperation(value = "Récupère un Worker grâce à son ID à "
			+ "condition que celui-ci existe dans la base!")
	@RequestMapping(value="/workers/{Id}")
	@ResponseBody
	public Worker getWorkerById(@PathVariable("Id") Long id)  {

		try {
			//UserDao userDao = new UserDao();
			Optional<Worker> worker = workerDao.findById(id);
			if(worker.isPresent()) 
			{
				return worker.get();

			}
			throw  new ResponseStatusException(HttpStatus.NOT_FOUND);

		}
		catch (Exception ex) {
			throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*Get a list of workers by they lastName or firstName or email*/

	@ApiOperation(value = "Récupère une liste Worker grâce à un filtre sur les paramètres firstName, lastName et email "
			+ "condition que celui-ci existe dans la base!")
	@GetMapping("/workers")
	public ResponseEntity <List<Worker>> getWorkerByFirstname(@RequestParam(required = false) String lastName,
			@RequestParam(required = false) String firstName,@RequestParam(required = false) String email) {
		List<Worker> workers = new ArrayList<Worker>();
		try {
			if (lastName!=null && firstName!=null)
				workerDao.findByLastNameContainingAndFirstNameContaining(lastName,firstName).forEach(workers::add);
			else 
				if (lastName!=null)
					workerDao.findByLastNameContaining(lastName).forEach(workers::add);
				else
					if(firstName!=null )
						workerDao.findByFirstNameContaining(firstName).forEach(workers::add);
					else
						if(email!=null)
							workerDao.findByEmailContaining(email).forEach(workers::add);
						else
							workerDao.findAll().forEach(workers::add);
			if (workers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(workers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Crer un customer")
	@PostMapping("/workers")
	public ResponseEntity<Worker> createWorker(@RequestBody Worker worker) {
		try {

			//workerDao.save(customer);
			Worker work = workerDao
					.save(new Worker(worker.getLastName(),worker.getFirstName(), worker.getEmail(),worker.getPassword(),
							worker.getJob(),worker.getBakRib()));
			return new ResponseEntity<>(worker, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/*Update a Worker by id*/
	@ApiOperation(value = "Modifier un Worker par son id ")
	@PutMapping("/workers/{id}")
	@ResponseBody
	public String updateWorker(@PathVariable("id") long id, String firstName,String lastName,String email,String password, String bakRib) {
		//Long idWorker;
		try {

			Worker worker = workerDao.findById(id).get();
			worker.setFirstName(firstName);
			worker.setLastName(lastName); 
			worker.setEmail(email);
			worker.setPassword(password);
			worker.setBakRib(bakRib);
			workerDao.save(worker);
			//idWorker= customer.getId();
		}catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		//return idWorker + " "+ "Worker succesfully updated!";
		return "Worker succesfully updated!";
	}


	/*Delete a customer*/
	@ApiOperation(value = "Supprimer un Worker par son id ")
	@DeleteMapping("/workers/{id}")
	public ResponseEntity<HttpStatus> deleteWorkerById(@PathVariable("id") long id) {
		try {
			workerDao.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*Delete all workers*/
	@ApiOperation(value = "Supprimer tous les Worker par son id ")
	@DeleteMapping("/workers")
	@ResponseBody
	public ResponseEntity<HttpStatus> deleteAllWorkers() {
		try {
			workerDao.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
