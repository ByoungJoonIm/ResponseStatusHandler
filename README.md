# ResponseStatusHandler
- http request의 응답 코드에 따른 exception 발생을 간편하게 해주는 handler

## 사용법
```java
ResponseStatusHandler startAPIStatusHandler = new ResponseStatusHandler(
				"start API",
				new String[] {
						"400", "problem_id 또는 number_of_elevators의 형식 또는 범위가 잘못됨",
						"401", "X-Auth-Token Header가 잘못됨",
						"403", "user_key가 잘못되었거나 10초 이내에 생성한 토큰이 존재",
						"500", "서버 에러, 문의 필요"});
```

## 예제
- code
  ```java
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
  ```

- 결과
  ```
  [startAPIStatusHandler]
  200 : true
  401 : false
  exceptions.UnauthorizedException: [401][start API]X-Auth-Token Header가 잘못됨
  403 : false
	  at handler.ResponseStatusHandler.<init>(ResponseStatusHandler.java:33)
	  at sample.Main.main(Main.java:7)
  exceptions.ForbiddenException: [403][start API]user_key가 잘못되었거나 10초 이내에 생성한 토큰이 존재
	  at handler.ResponseStatusHandler.<init>(ResponseStatusHandler.java:35)
	  at sample.Main.main(Main.java:7)
  exceptions.InternalServerErrorException: [500][start API]서버 에러, 문의 필요
	  at handler.ResponseStatusHandler.<init>(ResponseStatusHandler.java:37)
	  at sample.Main.main(Main.java:7)
  500 : false

  [oncallsAPIStatusHandler]
  200 : true
  exceptions.UnauthorizedException: [401][oncalls API]X-Auth-Token Header가 잘못됨
	  at handler.ResponseStatusHandler.<init>(ResponseStatusHandler.java:33)
	  at sample.Main.main(Main.java:15)
  401 : false
  exceptions.ForbiddenException: [403][oncalls API]정의되지 않음
	  at handler.ResponseStatusHandler.<init>(ResponseStatusHandler.java:23)
	  at sample.Main.main(Main.java:15)
  403 : false
  exceptions.InternalServerErrorException: [500][oncalls API]서버 에러, 문의 필요
	  at handler.ResponseStatusHandler.<init>(ResponseStatusHandler.java:37)
	  at sample.Main.main(Main.java:15)
  500 : false

  [actionAPIStatusHandler]
  200 : true
  exceptions.UnauthorizedException: [401][action API]X-Auth-Token Header가 잘못됨
	  at handler.ResponseStatusHandler.<init>(ResponseStatusHandler.java:33)
	  at sample.Main.main(Main.java:21)
  401 : false
  exceptions.ForbiddenException: [403][action API]정의되지 않음
	  at handler.ResponseStatusHandler.<init>(ResponseStatusHandler.java:23)
	  at sample.Main.main(Main.java:21)
  403 : false
  exceptions.InternalServerErrorException: [500][action API]서버 에러, 문의 필요
	  at handler.ResponseStatusHandler.<init>(ResponseStatusHandler.java:37)
	  at sample.Main.main(Main.java:21)
  500 : false
  ```
