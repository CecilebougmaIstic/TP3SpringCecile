package doctolib_service.data.jpa.web;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import doctolib_service.data.jpa.dao.TypeOfAppointementDao;
import doctolib_service.data.jpa.dao.WorkerDao;
import doctolib_service.data.jpa.domain.Appointement;
import doctolib_service.data.jpa.domain.Customer;
import doctolib_service.data.jpa.domain.TypeOfAppointement;
import doctolib_service.data.jpa.domain.Worker;
import doctolib_service.data.jpa.exeption.AlreadyExistDoctolibExeption;
import doctolib_service.data.jpa.exeption.DoctolibSServiceExceptionResponse;
import doctolib_service.data.jpa.exeption.NotFoundDoctolibExeption;
import doctolib_service.data.jpa.utils.RestClientZimbra;
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
	@Autowired
	TypeOfAppointementDao typeOfApppointementDao;

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

	/*Get all appointments of a one worker 
	 * @param workerId
	 *  */
	

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Récupère tous les appointements d'un Worker à "
			+ "condition que celui-ci existe dans la base!")
	@RequestMapping(value="/appointements/workers/{workerId}")
	public ResponseEntity <?> getAllAppointementsOfWorker(@PathVariable("workerId") Long workerId)
	{
		DoctolibSServiceExceptionResponse MessageError= new DoctolibSServiceExceptionResponse();
		List<Appointement> appointements = new ArrayList<Appointement>();

		try {
			Optional<Worker> _worker=workerDao.findById(workerId);
			if(_worker.isPresent()) {

				appointementDao.findAppointementByWorkerId(workerId).forEach(appointements::add);
			} else {
				DoctolibSServiceExceptionResponse MessageError1= new DoctolibSServiceExceptionResponse("This Worker doesn't exist",HttpStatus.NOT_FOUND.value());
				//String Message=MessageError.messageNotFoundOrEmpty("This Worker doesn't exist",HttpStatus.NOT_FOUND.value());
				 return new ResponseEntity<>(MessageError1,HttpStatus.NOT_FOUND);
			}

			if(appointements.isEmpty()) 
			{
				//throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
			DoctolibSServiceExceptionResponse appointementEmptyMess= new DoctolibSServiceExceptionResponse("No appointements", HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<>(appointementEmptyMess,HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(appointements, HttpStatus.OK);
		}
		catch (Exception ex) {
			DoctolibSServiceExceptionResponse ErrorMess= new DoctolibSServiceExceptionResponse(ex.toString());
			
		 return new ResponseEntity<>(ErrorMess,HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
	}



	@GetMapping("/appointements")
	@ResponseBody	
	public ResponseEntity <?> getAllAppointements() {	
		
		try {	
			List<Appointement> appointements = new ArrayList<Appointement>();
			
			appointementDao.findAll().forEach(appointements::add);
			if (appointements.isEmpty()) {
				DoctolibSServiceExceptionResponse error= new DoctolibSServiceExceptionResponse("Liste est vide");
				return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
			}

			//System.out.println("******************"+appointements.get(0).getAppointementStart());
			return new ResponseEntity<>(appointements, HttpStatus.OK);
		} catch (Exception e) {
			DoctolibSServiceExceptionResponse MessageError= new DoctolibSServiceExceptionResponse(e.toString());
			return new ResponseEntity<>(MessageError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*List of appointments for a one customer */	

	@ApiOperation(value = "Récupère tous les appointements d'un Customer à "
			+ "condition que celui-ci existe dans la base!")
	
	@GetMapping(value="/appointements/customers/{customerId}")
	@ResponseBody
	
	@Supervision(dureeMillis = 5)
	
	public ResponseEntity <?> getAllAppointementsOfCustomer(@PathVariable("customerId") Long customerId)
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
			//DoctolibSServiceExceptionResponse MessageError= new DoctolibSServiceExceptionResponse(e.toString());
			return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@ApiOperation(value = "Create an Appointement")
	@PostMapping("/appointements")
	@ResponseBody
	public ResponseEntity<?> createAppointement(@RequestBody  Appointement appointement) {
	
		Appointement _appointement=null ;
		Worker workerData=null;
		Customer custumerData;
		TypeOfAppointement typeOfApppointementData =null;
		LocalDateTime appointementEndFinal;
		//DoctolibSServiceExceptionResponse MessageError= new DoctolibSServiceExceptionResponse();
		
		/*Verify if a Customer exists*/
		Optional<Customer> _customer=customerDao.findById(appointement.getCustomer().getId());
		
		if(!_customer.isPresent()) {
			DoctolibSServiceExceptionResponse Message= new DoctolibSServiceExceptionResponse("This Customer doesn't exist",HttpStatus.NOT_FOUND.value());
			//return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST );
			return new ResponseEntity<>(Message,HttpStatus.BAD_REQUEST );

		}else
			custumerData=_customer.get();
		
		/*Verify if a Worker exists*/
		Optional<Worker> _worker=workerDao.findById(appointement.getWorker().getId());
		
		if(!_worker.isPresent()) {
			DoctolibSServiceExceptionResponse message= new DoctolibSServiceExceptionResponse("This Worker doesn't exist",HttpStatus.NOT_FOUND.value());
			//return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST );
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST );

		}else
			workerData=_worker.get();
		
		/*Verify if a TypeOfAppointement exists*/
		Optional<TypeOfAppointement> _typeOfApppointement=typeOfApppointementDao.findById(appointement.getTypeAppointement().getId());
		
		if(!_typeOfApppointement.isPresent()) {
			DoctolibSServiceExceptionResponse MessageError1= new DoctolibSServiceExceptionResponse("This kind of typeOfApppointement doesn't exist",HttpStatus.NOT_FOUND.value());
			//return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST );
			return new ResponseEntity<>(MessageError1,HttpStatus.BAD_REQUEST );

		}else
			typeOfApppointementData=_typeOfApppointement.get();
		
			/*Verify that this appointment is allow to registered*/
		if(appointementDao.appointementAlreadyExistForAWorker(appointement.getAppointementStart(), appointement.getAppointementEnd(),appointement.getWorker().getId()).isPresent())
		{
			return new ResponseEntity<>("This appointement is not disponible",HttpStatus.BAD_REQUEST );
		}
		 
		
		
			/*Don't allow to create an appointment in past*/
		/*************************************************************/
		 	//obtenir la date courante
		LocalDateTime dateCourante = LocalDateTime.now();
	
		   /*Don't allow to create an appointment in past*/
		   /*Conversion en long des date du jour et la date 
		    * de début d'appointemnt
		    * */
	

		 // System.out.println("*********************dateDuJourLong*****************"+dateDuJourLong);
		  if (appointement.getAppointementStart().isBefore(dateCourante)) {
			  DoctolibSServiceExceptionResponse dateOld= new DoctolibSServiceExceptionResponse("On ne peut pas réserver un rdv pour une date passée", HttpStatus.BAD_REQUEST.value());
			  return new ResponseEntity<>(dateOld, HttpStatus.BAD_REQUEST);
		 
		  }else {
			 
				 appointementEndFinal = appointement.getAppointementStart().plusMinutes(appointement.getTypeAppointement().getAppointementLimit());
				
		  }
			
		/*Contrôle au niveau de Zimbra*/
		LocalDateTime dateEnd = appointement.getAppointementStart().plusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String datePourZimbraStart = formatter.format(appointement.getAppointementStart());

		String datePourZimbraEnd = formatter.format(dateEnd);
				//dateEnd.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	
		//String dateAppointementEnd = formatter.format(appointement.getAppointementEnd());
		//LocalDateTime dateAppointementEnd1;
		try {
			//On récupère les rdv dans zimbra sur une tranche d'une journée par rapport (au jour) du rdv souhaité
			String json=RestClientZimbra.connexionApiZimbra(workerData.getEmail(), workerData.getPassword(),datePourZimbraStart,datePourZimbraEnd);
			
			boolean accept=false;
			//s'il existe des rdv dans zimbra pour ce jour là
			if(!json.equals("{}")) {
				//vérifier que les horaires sont disponibles
				formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String dateAppointementStart = formatter.format(appointement.getAppointementStart());
				String dateAppointementEnd = formatter.format(appointementEndFinal); 
				accept=RestClientZimbra.acceptReservation(json,dateAppointementStart,dateAppointementEnd);
				
			}else
				accept=true;
		
			//si horaire disponible
			if(accept==true) {
				LocalDateTime DateEnd = appointement.getAppointementStart().plusMinutes(appointement.getTypeAppointement().getAppointementLimit());

			///////////////////////////////////////////////
				LocalDateTime DateStart = appointement.getAppointementStart();
				LocalDateTime DateStartPlus30 = appointement.getAppointementStart().plusMinutes(appointement.getTypeAppointement().getAppointementLimit());
				int minute =appointement.getTypeAppointement().getAppointementLimit();
			
				
				
				/////////////////////////////////////////

				_appointement = appointementDao.save(new Appointement(0,appointement.getAppointementStart(),appointement.getAppointementStart().plusMinutes(30),
						appointement.getAppointementPlace(),appointement.getTypeAppointement(), appointement.getCustomer(),appointement.getWorker()));
				return new ResponseEntity<>(_appointement, HttpStatus.CREATED);

			}
			else { 	//horaire non disponible dans zimbra	
				DoctolibSServiceExceptionResponse errorMessage= new DoctolibSServiceExceptionResponse("horaire non disponible", HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST );
			}
		}
		catch (Exception e) {
			DoctolibSServiceExceptionResponse ErrorMessage= new DoctolibSServiceExceptionResponse("Don't create Appointement", e.toString());
			return new ResponseEntity<>(ErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	/*Delete all Appointments*/
	@ApiOperation(value = "delete all appointements ")
	@DeleteMapping("/appointements")
	@ResponseBody
	public ResponseEntity<?> deleteAllAppointement() {
		try {
			appointementDao.deleteAll();
			DoctolibSServiceExceptionResponse error= new DoctolibSServiceExceptionResponse(" succesfully deleted!");
			return new ResponseEntity<>(error,HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>("Error deleting the appointement:" + e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*Delete an Appointment*/

	@ApiOperation(value = "delete an appointement by id ")
	@DeleteMapping("/appointements/{id}")
	@ResponseBody

	public ResponseEntity<String> deleteAppointementById(@PathVariable("id") long id) {
		Optional<Appointement> appointementToDelete = appointementDao.findById(id);
		if (!appointementToDelete.isPresent()) {
			//return new ResponseEntity<>("Error deleting:"+" "+ id,HttpStatus.NOT_FOUND);
			throw new NotFoundDoctolibExeption("Error deleting:"+" "+ id,"le RDV n'existe pas en base de donnée.");
		}else { 
			appointementDao.deleteById(id);
			return new ResponseEntity<>( id+ " "+" succesfully deleted!",HttpStatus.NO_CONTENT);
		}
	}

	
}
