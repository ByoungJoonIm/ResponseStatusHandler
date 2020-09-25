/*
 * reference : https://github.com/ByoungJoonIm/ResponseStatusHandler
 * */

package handler;

import exceptions.BadRequestException;
import exceptions.ForbiddenException;
import exceptions.InternalServerErrorException;
import exceptions.StatusException;
import exceptions.UnauthorizedException;

public class ResponseStatusHandler {
	private static final String UNDEFIND_MESSAGE = "Á¤ÀÇµÇÁö ¾ÊÀ½";
	BadRequestException badRequestException = null;
	ForbiddenException forbiddenException = null;
	InternalServerErrorException internalServerErrorException = null;
	UnauthorizedException unauthorizedException = null;
	
	/*
	 * args : "400", "¹º°¡ Àß¸øµÊ", "401", "¹º°¡ ´õ Àß¸øµÊ"
	 * */
	public ResponseStatusHandler(String apiName, String args[]){
		String apiNameWrapper = "[" + apiName + "]";
		badRequestException = new BadRequestException(apiNameWrapper + UNDEFIND_MESSAGE);
		unauthorizedException = new UnauthorizedException(apiNameWrapper + UNDEFIND_MESSAGE);
		forbiddenException = new ForbiddenException(apiNameWrapper + UNDEFIND_MESSAGE);
		internalServerErrorException = new InternalServerErrorException(apiNameWrapper + UNDEFIND_MESSAGE);
		
		for (int i = 0; i < args.length; i+=2) {
			int statusCode = Integer.parseInt(args[i]);
			String message = args[i + 1];
			
			if(statusCode == 400)
				badRequestException = new BadRequestException(apiNameWrapper + message);
			else if(statusCode == 401)
				unauthorizedException = new UnauthorizedException(apiNameWrapper + message);
			else if(statusCode == 403)
				forbiddenException = new ForbiddenException(apiNameWrapper + message);
			else if(statusCode == 500)
				internalServerErrorException = new InternalServerErrorException(apiNameWrapper + message);
		}
	}
	
	public boolean isOKResponse(int responseCode){
		try{
			switch(responseCode){
			case 400: throw badRequestException;
			case 401: throw unauthorizedException;
			case 403: throw forbiddenException;
			case 500: throw internalServerErrorException;
			}
		} catch(StatusException e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
