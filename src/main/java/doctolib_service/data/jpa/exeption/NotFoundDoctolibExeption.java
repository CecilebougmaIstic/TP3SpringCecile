package doctolib_service.data.jpa.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundDoctolibExeption extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String messagePersonalise;
	
	public NotFoundDoctolibExeption() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotFoundDoctolibExeption(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public NotFoundDoctolibExeption(String message, String mess) {
		super(message);
		this.setMessagePersonalise(mess);
		// TODO Auto-generated constructor stub
	}


	public String getMessagePersonalise() {
		return messagePersonalise;
	}

	public void setMessagePersonalise(String messagePersonalise) {
		this.messagePersonalise = messagePersonalise;
	}


}
