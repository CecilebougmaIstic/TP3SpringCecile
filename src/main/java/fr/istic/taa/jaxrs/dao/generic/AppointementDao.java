package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Appointement;

public class AppointementDao extends AbstractJpaDao <Long,Appointement> {

	public AppointementDao() {
		super(Appointement.class);
		
	}

}
