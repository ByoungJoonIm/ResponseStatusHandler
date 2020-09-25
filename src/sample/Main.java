package sample;

import handler.ResponseStatusHandler;

public class Main {
	public static void main(String args[]){
		ResponseStatusHandler startAPIStatusHandler = new ResponseStatusHandler(
				"start API",
				new String[] {
						"400", "problem_id 또는 number_of_elevators의 형식 또는 범위가 잘못됨",
						"401", "X-Auth-Token Header가 잘못됨",
						"403", "user_key가 잘못되었거나 10초 이내에 생성한 토큰이 존재",
						"500", "서버 에러, 문의 필요"});
		
		ResponseStatusHandler oncallsAPIStatusHandler = new ResponseStatusHandler(
				"oncalls API",
				new String[] {
						"401", "X-Auth-Token Header가 잘못됨",
						"500", "서버 에러, 문의 필요"});
		
		ResponseStatusHandler actionAPIStatusHandler = new ResponseStatusHandler(
				"action API",
				new String[] {
						"400", "해당 명령을 실행할 수 없음 (실행할 수 없는 상태일 때, 엘리베이터 수와 Command 수가 일치하지 않을 때, 엘리베이터 정원을 초과하여 태울 때)",
						"401", "X-Auth-Token Header가 잘못됨",
						"500", "서버 에러, 문의 필요"});
		
		int statusCodes[] = {200, 401, 403, 500};
		
		System.out.println("[startAPIStatusHandler]");
		for (int i = 0; i < statusCodes.length; i++) {
			System.out.println(statusCodes[i] + " : " + startAPIStatusHandler.isOKResponse(statusCodes[i]));
		}
		
		System.out.println("\n[oncallsAPIStatusHandler]");
		for (int i = 0; i < statusCodes.length; i++) {
			System.out.println(statusCodes[i] + " : " + oncallsAPIStatusHandler.isOKResponse(statusCodes[i]));
		}
		
		System.out.println("\n[actionAPIStatusHandler]");
		for (int i = 0; i < statusCodes.length; i++) {
			System.out.println(statusCodes[i] + " : " + actionAPIStatusHandler.isOKResponse(statusCodes[i]));
		}
	}
}
