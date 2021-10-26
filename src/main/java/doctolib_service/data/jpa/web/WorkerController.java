package doctolib_service.data.jpa.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import doctolib_service.data.jpa.dao.WorkerDao;
import doctolib_service.data.jpa.domain.Customer;
import doctolib_service.data.jpa.domain.Worker;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")

public class WorkerController {
	@Autowired
	private WorkerDao  workerDao;

	/*Get a worker by id*/
	@ApiOperation(value = "Get a worker by his id "
			+ "condition: if this worker exits in base.")
	@RequestMapping(value="/workers/{Id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	
	public ResponseEntity<Worker>  getWorkerById(@PathVariable("Id") Long id)  {
		Optional<Worker> worker = workerDao.findById(id);
		
	
		if (worker.isPresent()) {
			return new ResponseEntity<>(worker.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

	@ApiOperation(value = "Create a worker")
	@PostMapping("/workers")
	public ResponseEntity<Worker> createWorker(@RequestBody Worker worker) {
		try {

			//workerDao.save(customer);
			Worker work = workerDao
					.save(new Worker(worker.getFirstName(),worker.getLastName(), worker.getEmail(),worker.getPassword(),
							worker.getJob(),worker.getBakRib()));
			return new ResponseEntity<>(worker, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/*Update a Worker by id*/
	@ApiOperation(value = "Modified a worker by his id")
	@PutMapping("/workers/{id}")
	@ResponseBody

	

	public ResponseEntity<Worker> updateWorker(@PathVariable("id") long id, @RequestBody  Worker worker) {
		//Long idWorker;
		

			Optional<Worker> workerData = workerDao.findById(id);
			if(workerData.isPresent()) {
				Worker _worker=workerData.get();
				_worker.setFirstName(worker.getFirstName());
				_worker.setLastName(worker.getLastName()); 
				_worker.setEmail(worker.getEmail());
				_worker.setPassword(worker.getPassword());
				_worker.setBakRib(worker.getBakRib());
			
			return new ResponseEntity<>(workerDao.save(_worker), HttpStatus.OK);
			}else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}


	/*Delete a worker*/
	@ApiOperation(value = "delete a Worker by id ")
	@DeleteMapping("/workers/{id}")
	public ResponseEntity<String> deleteWorkerById(@PathVariable("id") long id) {
		Optional<Worker> workerData = workerDao.findById(id);
		if (!workerData.isPresent()) {
			return new ResponseEntity<>("Error deleting:"+" "+ id,HttpStatus.NOT_FOUND);
		}else { 
			workerDao.deleteById(id);
		
			return new ResponseEntity<>( id+ " "+" succesfully deleted!",HttpStatus.NO_CONTENT);
		}
		
	}

	/*Delete all workers*/
	@ApiOperation(value = "delete all workers ")
	@DeleteMapping("/workers")
	@ResponseBody
	public ResponseEntity<String> deleteAllWorkers() {
		try {
			workerDao.deleteAll();
			return new ResponseEntity<>(" succesfully deleted!",HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>("Error deleting the worker:" + e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
