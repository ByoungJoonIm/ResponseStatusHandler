package exceptions;

public class BadRequestException extends StatusException{
	public BadRequestException(String msg){
		super("[400]" + msg);
	}
}
