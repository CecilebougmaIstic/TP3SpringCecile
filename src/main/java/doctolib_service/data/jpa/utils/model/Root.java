package doctolib_service.data.jpa.utils.model;

import java.util.List;

public class Root {
	public List<Appt> appt;

	public List<Appt> getAppt() {
		return appt;
	}

	public void setAppt(List<Appt> appt) {
		this.appt = appt;
	}
	
}
