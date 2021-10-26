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

import doctolib_service.data.jpa.dao.TypeOfAppointementDao;
import doctolib_service.data.jpa.domain.Customer;
import doctolib_service.data.jpa.domain.TypeOfAppointement;
import doctolib_service.data.jpa.domain.Worker;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")

public class TypeOfAppointementController {
	@Autowired
	private TypeOfAppointementDao typeOfAppointementDao;

	/* Get a worker by id */
	@ApiOperation(value = "Find a TypeOfAppointement by it id" + "By condition, it exists in base!")
	
	@RequestMapping(value = "/typeOfAppointements/{Id}")
	@ResponseBody
	@GetMapping("/typeOfAppointements")
	public TypeOfAppointement getTypeOfAppointementById(@PathVariable("Id") Long id) {

		try {
			Optional<TypeOfAppointement> typeOfAppointement = typeOfAppointementDao.findById(id);
			if (typeOfAppointement.isPresent()) {
				return typeOfAppointement.get();

			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* Get a list of TypeOfAppointement by  */

	@ApiOperation(value = "Get a liste of TypeOfAppointement by filtering on the "
			+ "@parameter appointementDescription, @ appointementLimit"
			+ "condition: A TypeOfAppointement exists on base!")
	@GetMapping("/typeOfAppointements")
	
	public ResponseEntity<List<TypeOfAppointement>> getTypeOfAppointementAttributs(@RequestParam(required = false) String appointementDescription,
			@RequestParam(required = false) Integer appointementLimit) {
		List<TypeOfAppointement> typeAppointements = new ArrayList<TypeOfAppointement>();
		try {
			if (appointementDescription != null && appointementLimit != null)
				typeOfAppointementDao.findByAppointementDescriptionContainingAndAppointementLimitContaining(appointementDescription, appointementLimit).forEach(typeAppointements::add);
			else if (appointementDescription!= null)
				typeOfAppointementDao.findByAppointementDescription(appointementDescription).forEach(typeAppointements::add);
			else if (appointementLimit != null)
				typeOfAppointementDao.findByAppointementLimit(appointementLimit).forEach(typeAppointements::add);
			else
				typeOfAppointementDao.findAll().forEach(typeAppointements::add);
			if (typeAppointements.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(typeAppointements, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*Create an TypeOfAppointement*/
	@ApiOperation(value = "Create a TypeOfAppointement")
	@PostMapping("/typeOfAppointements")
	public ResponseEntity<TypeOfAppointement> createTypeOfAppointement(@RequestBody TypeOfAppointement typeOfAppointement) {
		try {

			// workerDao.save(customer);
	
			TypeOfAppointement _typeOfAppointement = typeOfAppointementDao.save(new TypeOfAppointement(typeOfAppointement.getAppointementDescription(), typeOfAppointement.getAppointementLimit(), 
					typeOfAppointement.getWorker()));
			return new ResponseEntity<>(_typeOfAppointement, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* Update a TypeOfAppointementDao by id */
	@ApiOperation(value = "Update TypeOfAppointement by id ")
	@PutMapping("/typeOfAppointements/{id}")
	@ResponseBody
	public ResponseEntity<TypeOfAppointement> updateTypeOfAppointements(@PathVariable("id") long id, @RequestBody TypeOfAppointement typeOfAppointement) {
		// Long idWorker;
		Optional<TypeOfAppointement> typeOfAppointementData = typeOfAppointementDao.findById(id);
		if(typeOfAppointementData.isPresent())
		{
			TypeOfAppointement _typeOfAppointement = typeOfAppointementData.get();
			_typeOfAppointement.setAppointementDescription(typeOfAppointement.getAppointementDescription());
			_typeOfAppointement.setAppointementLimit(typeOfAppointement.getAppointementLimit());
			_typeOfAppointement.setId(typeOfAppointement.getId());
			//_typeOfAppointement.setWorker(typeOfAppointement.getWorker());
		
			return new ResponseEntity<>(typeOfAppointementDao.save(_typeOfAppointement), HttpStatus.OK);

			
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	
			
		
		// return idWorker + " "+ "Worker succesfully updated!";
		//return  id + " " + "succesfully updated!";
		
	}

	/* Delete a  */
	@ApiOperation(value = "Delete a TypeOfAppointement by id ")
	@DeleteMapping("/typeOfAppointements/{id}")
	public ResponseEntity<HttpStatus> deleteTypeOfAppointementById(@PathVariable("id") long id) {
		try {
			typeOfAppointementDao.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* Delete all TypeOfAppointements */
	@ApiOperation(value = "Delete allTypeOfAppointement by id ")
	@DeleteMapping("/typeOfAppointements")
	@ResponseBody
	public ResponseEntity<HttpStatus> deleteAllWorkers() {
		try {
			typeOfAppointementDao.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
