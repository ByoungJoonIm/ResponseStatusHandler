package exceptions;

public class ForbiddenException extends StatusException{
	public ForbiddenException(String msg){
		super("[403]" + msg);
	}
}
