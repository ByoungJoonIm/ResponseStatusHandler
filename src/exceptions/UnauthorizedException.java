package exceptions;

public class UnauthorizedException extends StatusException{
	public UnauthorizedException(String msg){
		super("[401]" + msg);
	}
}
