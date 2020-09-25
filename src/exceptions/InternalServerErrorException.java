package exceptions;

public class InternalServerErrorException extends StatusException{
	public InternalServerErrorException(String msg){
		super("[500]" + msg);
	}
}
