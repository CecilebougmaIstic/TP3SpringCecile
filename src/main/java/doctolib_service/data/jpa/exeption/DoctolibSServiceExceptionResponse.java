package doctolib_service.data.jpa.exeption;


import org.springframework.web.bind.annotation.ResponseStatus;

public class DoctolibSServiceExceptionResponse {
	
	private String message;
	//private String exception;
	private int exeptionResponse;
	private String exception;
	
	public int getExeptionResponse() {
		return exeptionResponse;
	}

	public void setExeptionResponse(int exeptionResponse) {
		this.exeptionResponse = exeptionResponse;
	}

	public DoctolibSServiceExceptionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoctolibSServiceExceptionResponse(String message) {
		super();
		this.message = message;
	}

	public DoctolibSServiceExceptionResponse(String message, String exception) {
		super();
		this.message = message;
		this.exception = exception;
	}


	public DoctolibSServiceExceptionResponse(String message, int exeptionResponse, String exception) {
		super();
		this.message = message;
		this.exeptionResponse = exeptionResponse;
		this.exception = exception;
	}
	
	public DoctolibSServiceExceptionResponse(String message, int exeptionResponse) {
		super();
		this.message = message;
		this.exeptionResponse = exeptionResponse;
		
	}
	
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	@Override
	public String toString() {
		return "DoctolibSServiceExceptionResponse [message=" + message + ", exeptionResponse=" + exeptionResponse
				+ ", exception=" + exception + "]";
	}



}
