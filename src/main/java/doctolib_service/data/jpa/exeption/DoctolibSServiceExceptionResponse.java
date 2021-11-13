package doctolib_service.data.jpa.exeption;


import org.springframework.web.bind.annotation.ResponseStatus;

public class DoctolibSServiceExceptionResponse {
	
	private String message;
	private int codeError;
	private String exception="";
	
	/*Constructors*/
	
	public DoctolibSServiceExceptionResponse() {}
	
	public DoctolibSServiceExceptionResponse(String message, int codeError) {
		super();
		this.message = message;
		this.codeError = codeError;
	}
	
	public DoctolibSServiceExceptionResponse(String message) {
		super();
		this.message = message;
		}

	public DoctolibSServiceExceptionResponse(String message, int codeError, String exception) {
		super();
		this.message = message;
		this.codeError = codeError;
		this.exception = exception;
	}
	
	public DoctolibSServiceExceptionResponse(String message, String exception) {
		super();
		this.message = message;
		this.exception = exception;
	}
	
	/**/
	


		/*Getters & Setters*/
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCodeError() {
		return codeError;
	}

	public void setCodeError(int codeError) {
		this.codeError = codeError;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}




	


	


}
