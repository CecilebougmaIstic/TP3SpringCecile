package fr.istic.taa.jaxrs.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.istic.taa.jaxrs.dao.generic.WorkerDao;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.domain.Worker;
import io.swagger.v3.oas.annotations.Parameter;

@Path("/worker")
@Produces({"application/json", "application/xml"})

public class WorkerResource {

	@GET
	  @Path("/{workerId}")
	@Produces({"application/json"})
	  public Worker getWorkerById(@PathParam("workerId") Long workerId)  {
		WorkerDao wDao = new WorkerDao();
		Worker lookXorker = wDao.findOne(workerId);
	      return lookXorker;
	  }

	  @POST
	  @Consumes("application/json")
	  @Produces({MediaType.TEXT_PLAIN})
	  public String addWorker(@Parameter(required = true) Worker worker) {
		  
		  Long idWorker=(long) 0;
		  WorkerDao wDao = new WorkerDao();
			Worker workerD = new Worker(worker.getLastName(),worker.getFirstName(), worker.getEmail(),worker.getPassword(),
					worker.getJob(),worker.getBakRib());
			
			wDao.save(workerD);
			idWorker = workerD.getId();
		 System.out.println(workerD.getEmail());
	    // add pet
	    return  "Le Professionnel"+ "a été créé avec succès"+ "son iddentifiant est:" + 
	    		+ idWorker;
	  }
	  
	  
	  
	  /*To recuperate All Worker*/
	  
	  @GET
	  @Path("/all")
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Worker> getallWorker()  {
		  WorkerDao listWorkerDao = new WorkerDao();
		  List<Worker> w = (List<Worker>) listWorkerDao.findAll();
		
	      return  w;
	  }
	  
}
