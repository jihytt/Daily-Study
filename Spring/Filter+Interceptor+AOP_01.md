
# Filter / Interceptor / AOP

<br/>
 

웹 개발을 하다보면, 공통적으로 처리해야 할 업무들이 많다.   
공통업무에 관련된 코드를 모든 페이지마다 작성 해야한다면 중복 코드는 많아지고 프로젝트 단위가 커질 수록 서버에 부하를 줄 수 있으며, 소스 관리도 되지 않는다.   
**=> 공통 부분은 빼서 따로 관리하는 것이 좋다.**

<br/>

공통처리를 위해 활용할 수 있는 3가지
### **"Filter, Interceptor, AOP"**

<br/><br/>

>## 1. Filter Interceptor AOP의 흐름

<br/>

<img width="90%" src="https://user-images.githubusercontent.com/75427390/145044352-c779d61a-0927-46b6-b224-b14d40bbed20.png">   

[내용 및 사진 출처: 심해펭귄의 심해도서관, [Spring] Interceptor, filter, AOP의 차이](https://blog.naver.com/platinasnow/220035316135)   

<br/>

- 실행순서를 보면 Filter가 가장 밖에 있고 그 안에 Interceptor, 그 안에 AOP가 있는 형태이다.   
따라서 요청이 들어오면 Filter -> Interceptor -> AOP -> Interceptor -> Filter 순으로 거치게 된다.   
- Interceptor와 Filter는 Servlet 단위에서 실행. AOP는 메소드 앞에 Proxy패턴의 형태로 실행.

<br/>

**실행 순서**   
1. 서버를 실행시켜 서블릿이 올라오는 동안 init이 실행되고 그 후 doFilter 실행
2. 컨트롤러에 들어가기 전 preHandler 실행
3. 컨트롤러에서 나와 postHandler, after Completion, doFilter 순으로 진행
4. 서블릿 종료시 destroy 실행

<br/>

||Filter|Interceptor|AOP|
|------|---|---|---|
|실행위치|servlet|servlet|method|
|실행순서|1(제일 먼저와 제일 나중)|2|3|
|설정위치|web.xml|xml 또는 java|xml 또는 java|
|실행 메소드|init<br/>dofilter<br/>destroy|preHandler<br/>postHandler<br/>afterCompletion|pointcut으로 @after, @before @around 등<br/>위치를 지정하여 자유롭게 메소드 생성 가능|

<br/> 

[내용 참조 : 갓대희의 작은공간](https://goddaehee.tistory.com/154)
