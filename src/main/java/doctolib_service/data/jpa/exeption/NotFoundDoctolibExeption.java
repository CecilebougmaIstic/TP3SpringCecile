package doctolib_service.data.jpa.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundDoctolibExeption extends RuntimeException {

	public NotFoundDoctolibExeption() {
		super();
		// TODO Auto-generated constructor stub
	}



	public NotFoundDoctolibExeption(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	

}
