package doctolib_service.data.jpa.exeption;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class AlreadyExistDoctolibExeption extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String messagePersonalise;
	
	
	
	public AlreadyExistDoctolibExeption(String messagePersonalise) {
		super();
		this.messagePersonalise = messagePersonalise;
	}
	
	public AlreadyExistDoctolibExeption(String message, String mess) {
		super(message);
		this.messagePersonalise =mess;
		// TODO Auto-generated constructor stub
	}




	public AlreadyExistDoctolibExeption() {
		super();
		// TODO Auto-generated constructor stub
	}




	public AlreadyExistDoctolibExeption(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}




	public AlreadyExistDoctolibExeption(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}




	public AlreadyExistDoctolibExeption(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

}
