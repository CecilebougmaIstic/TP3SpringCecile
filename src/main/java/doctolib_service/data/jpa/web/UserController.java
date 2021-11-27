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
import doctolib_service.data.jpa.exeption.DoctolibSServiceExceptionResponse;
import doctolib_service.data.jpa.exeption.NotFoundDoctolibExeption;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public abstract class UserController {
	//It ' a abstract class
}
	

	

