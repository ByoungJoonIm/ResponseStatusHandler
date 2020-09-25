package sample;

import handler.ResponseStatusHandler;

public class Main {
	public static void main(String args[]){
		ResponseStatusHandler startAPIStatusHandler = new ResponseStatusHandler(
				"start API",
				new String[] {
						"400", "problem_id �Ǵ� number_of_elevators�� ���� �Ǵ� ������ �߸���",
						"401", "X-Auth-Token Header�� �߸���",
						"403", "user_key�� �߸��Ǿ��ų� 10�� �̳��� ������ ��ū�� ����",
						"500", "���� ����, ���� �ʿ�"});
		
		ResponseStatusHandler oncallsAPIStatusHandler = new ResponseStatusHandler(
				"oncalls API",
				new String[] {
						"401", "X-Auth-Token Header�� �߸���",
						"500", "���� ����, ���� �ʿ�"});
		
		ResponseStatusHandler actionAPIStatusHandler = new ResponseStatusHandler(
				"action API",
				new String[] {
						"400", "�ش� ����� ������ �� ���� (������ �� ���� ������ ��, ���������� ���� Command ���� ��ġ���� ���� ��, ���������� ������ �ʰ��Ͽ� �¿� ��)",
						"401", "X-Auth-Token Header�� �߸���",
						"500", "���� ����, ���� �ʿ�"});
		
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
