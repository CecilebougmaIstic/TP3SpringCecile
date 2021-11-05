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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import doctolib_service.data.jpa.dao.UserDao;
import doctolib_service.data.jpa.domain.Customer;
import doctolib_service.data.jpa.domain.User;
import doctolib_service.data.jpa.exeption.NotFoundDoctolibExeption;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public abstract class UserController {
	
	@Autowired
	private UserDao userDao;
	
	/*Get a user by id*/
	@ApiOperation(value = "Get a user by his id "
			+ "condition: if this user exits in base.")
	@RequestMapping(value="/users/{Id}")
	@ResponseBody
	public User getUserById(@PathVariable("Id") Long id)  {

		try {
			//UserDao userDao = new UserDao();
			Optional<User> user = userDao.findById(id);
			if(user.isPresent()) 
			{
				return user.get();
			}
			//throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
			throw new NotFoundDoctolibExeption("User" + id + "est INTROUVABLE");
		}
		catch (Exception ex) {
			throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/*Get All users*/
	
	@GetMapping("/users")
	@ResponseBody
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String lastName,
			@RequestParam(required = false) String firstName,@RequestParam(required = false) String email) {
		List<User> users = new ArrayList<User>();
		try {
			if (lastName!=null && firstName!=null)
				userDao.findByLastNameContainingAndFirstNameContaining(lastName,firstName).forEach(users::add);
			else 
				if (lastName!=null)
					userDao.findByLastNameContaining(lastName).forEach(users::add);
				else
					if(firstName!=null )
						userDao.findByFirstNameContaining(firstName).forEach(users::add);
					else
						if(email!=null)
							userDao.findByEmailContaining(email).forEach(users::add);
						else
							userDao.findAll().forEach(users::add);
			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	/*Update a User*/
	
	@PutMapping("/users/{id}")
	@ResponseBody
	public String updateUser(long id, String firstName,String lastName,String email,String password) {
		try {
			User user = userDao.findById(id).get();
			user.setFirstName(firstName);
			user.setLastName(lastName); 
			user.setEmail(email);
			user.setPassword(password);
			userDao.save(user);
		}catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		return "User succesfully updated!";
	}
	
	/**
	 * GET /delete  --> Delete the user having the passed id.
	 */
	
	@DeleteMapping("/users/{id}")
	@ResponseBody
	public String delete(long id) {
		try {
			Optional<User> user = userDao.findById(id);
			if(user.isPresent()) 
			{
				userDao.deleteById(id);
				return "User succesfully deleted!";
			}
			throw  new ResponseStatusException(HttpStatus.NOT_FOUND);

			
			
		}
		catch (Exception ex) {
			return "Error deleting the user:" + ex.toString();
		}
		
	}

}

