package fr.istic.taa.jaxrs.dao.generic;


import java.util.List;

import fr.istic.taa.jaxrs.domain.Worker;



public class WorkerDao extends AbstractJpaDao <Long,Worker>{

	public WorkerDao() {
		super(Worker.class);
		// TODO Auto-generated constructor stub
	}
	
	/*Get a list*/
  public List < Worker>getAllTypeOfAppointementsForAWorker(){
	 String query ="select distinct K.typeAppointements from TypeOfAppointement as k";
	 return this.entityManager.createQuery(query).getResultList();
	}
	
}
