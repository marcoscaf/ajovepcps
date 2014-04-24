package br.com.ajovepcps.exceptions;

public class MemberConstraintViolationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String cause;

	public MemberConstraintViolationException(Exception e, String cause) {

		super(cause, e);
		
		setCauseException(cause);
		

	}
	
	private void setCauseException(String cause){
		this.cause = cause;
	}
	
	public String getCauseException(){
		return this.cause;
	}

}
