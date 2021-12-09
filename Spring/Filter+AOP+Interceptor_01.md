
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

<img width="80%" src="https://user-images.githubusercontent.com/75427390/145044352-c779d61a-0927-46b6-b224-b14d40bbed20.png">   

[내용 및 사진 출처: 심해펭귄의 심해도서관, [Spring] Interceptor, filter, AOP의 차이](https://blog.naver.com/platinasnow/220035316135)   

<br/>

- 실행순서를 보면 Filter가 가장 밖에 있고 그 안에 Interceptor, 그 안에 AOP가 있는 형태이다.   
따라서 요청이 들어오면 Filter -> Interceptor -> AOP -> Interceptor -> Filter 순으로 거치게 된다.   
- Interceptor와 Filter는 Servlet 단위에서 실행. AOP는 메소드 앞에 Proxy패턴의 형태로 실행.

<br/>

**실행 순서**   
1. 서버를 실행시켜 서블릿이 올라오는 동안 init이 실행되고 그 후 doFilter 실행
2. 컨트롤러에 들어가기 전 preHandler 실행
3. AOP가 실행된 후에 컨트롤러에서 나와 postHandler, after Completion, doFilter 순으로 진행
4. 서블릿 종료시 destroy 실행

<br/>

||Filter|Interceptor|AOP|
|------|---|---|---|
|실행위치|servlet|servlet|method|
|실행순서|1(제일 먼저와 제일 나중)|2|3|
|설정위치|web.xml|xml 또는 java|xml 또는 java|
|실행 메소드|init<br/>dofilter<br/>destroy|preHandler<br/>postHandler<br/>afterCompletion|pointcut으로 @after, @before @around 등<br/>위치를 지정하여 자유롭게 메소드 생성 가능|

<br/> 

**AOP의 경우 Interceptor나 Filter와 달리 메소드 전후의 지점을 자유롭게 설정 가능**하고, **Interceptor와 Filter가 주소로밖에 걸러낼 대상을 구분할 수 없는 것에 비해서 AOP는 주소, 파라미터, 어노테이션 등 다양한 방법으로 대상을 지정할 수 있는 장점**이 있다.


<br/>
<br/>


>## 2. Filter

<br/>

<img width="70%" src="https://user-images.githubusercontent.com/75427390/145203755-e7fe4a9c-6eda-41a1-887e-b760b1f1311f.png">

<br/>

`**Filter**`는 요청과 응답을 거른 뒤 정제하는 역할을 한다.   
 **자원이 받게 되는 요청 정보**는 클라이언트와 자원 사이에 존재하는 **필터에 의해 변경된 요청 정보**가 되며,   
**클라이언트가 보게 되는 응답 정보**는 클라이언트와 자원 사이에 존재하는 **필터에 의해 변경된 응답 정보**가 된다.

<br/>

**여러 개의 필터가 모여 하나의 체인을 형성**할 수도 있는데, 이 때는 첫 번째 필터가 변경하는 요청 정보는 클라이언트의 요청정보가 되지만, 체인의 두 번째 필터가 변경하는 요청 정보는 첫번째 필터를 통해서 변경된 요청 정보가 된다.   

<br/>

필터는 정보를 변경하는 역할 뿐만 아니라 흐름을 변경하는 역할도 할 수 있다. 즉, 필터는 클라이언트의 요청을 필터 체인의 다음 단계(결과적으로는 클라이언트가 요청한 자원)에 보내는 것이 아니라 다른 자원의 결과를 클라이언트에 전송할 수 있다.

<br/>
<br/>

>### **필터가 필요한 이유**
필터가 필요한 이유는 웹 컴포넌트(Servlet, JSP)에서 공통적으로 처리해야할 일들을 필터를 통해 처리할 수 있기 때문   

<br/>
예를 들어 요청 데이터의 본문을 UTF-8로 인코딩하는 부분은 거의 대부분의 웹 컴포넌트에서 처리하는 로직이다.   

```java
request.setCharacterEncoding("UTF-8");
String param = request.getParameter("param");
```   
그러나 웹 어플리케이션에서 수십, 수백개의 웹 컴포넌트를 개발해야 한다면 이런 한글 인코딩 처리 코드는 수백여개의 중복이 발생할 것이다.매번 웹 컴포넌트를 개발할 때마다 인코딩 처리 로직을 넣어주는 것도 문제지만, 인코딩 처리를 다른 것으로 바꿔야 한다면 중복되는 모든 코드를 바꿔줘야 하는 문제가 생긴다. (유지보수 측면에서 좋지 못함)   

<br/>

필터는 이런 상황에서 인코딩 처리 부분을 분리하여 처리할 수 있다.

<br/>

<img width="70%" src="https://t1.daumcdn.net/cfile/tistory/99624C405C33F9E034">   

사진 출처 : https://dololak.tistory.com/602

<br/>

### **필터의 기능**
- 서블릿이 호출되기 전에 요청(request)를 가로채어 조작한다.
- 서블릿이 호출되기 전에 요청을 가로채어 점검할 수 있다.
- 서블릿이 호출되기 전에 HTTP 요청의 헤더를 조작할 수 있다.
- 서블릿이 호출된 이후 응답(response)를 출력하기 전에 가로채어 조작한다.
- 서블릿이 호출된 이후 응답 헤더를 조작할 수 있다.

<br/>

### **필터의 사용 예**
- 로그인 여부나 권한 검사와 같은 인증 기능
- 요청이나 응답에 대한 로그(기록) 기능
- 오류 처리 기능
- 데이터 압축이나 변환 기능
- 인코딩 처리 기능 등


<br/>
<br/>

>## 2-1. Filter 생성

<br/>

`Filter`를 사용하기 위해서는 어떤 필터가 어떤 자원에 대해서 적용된다는 것을 서블릿/JSP컨테이너에게 알려주어야 한다. 서블릿 설정은 web.xml 파일을 통해 하고 있다.   
<span style="color:gray">web.xml은 톰캣이 실행될 때 처음으로 읽어들이는 설정 파일</span>   

<br/>

```java
	<!-- filter 한글 처리 코드 -->
	<filter>
		<filter-name>encodingFilter</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
		<init-param>
			<param-name>forceEncoding</param-name>
			<param-value>true</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>encodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
```   

위의 등록한 filter의 이름은 encodingFilter이고, 값은 UTF-8인 파라미터를 정의하고 있다.   
필터의 url-pattern을 /*로 정의하면, servlet, jsp 뿐만 아니라 이미지와 같은 모든 자원의 요청에도 호출된다.   

<br/>

위와 같이 필터를 web.xml에 바로 지정할 수 있지만, **사용자가 따로 필터클래스를 생성해서 필터를 적용시킬 수도 있다.**

<br/>
<br/>

>### **필터관련 인터페이스 및 클래스**
필터를 구현하는 데 있어 핵심적인 역할을 하는 인터페이스 및 클래스   
<br/>

**`javax.servlet.Filter` 인터페이스**   
- *클라이언트와 최종 자원 사이에 위치하는 필터를 나타내는 객체가 구현해야 하는 인터페이스* 

**`javax.servlet.ServletRequestWrapper`, `javax.servlet.ServletResponseWrapper`   클래스**
- *필터가 요청을 변경한 결과 또는 응답을 변경할 결과를 저장할 래퍼 클래스. 개발자는 이 두 클래스를 알맞게 상속하여 요청/응답 정보를 변경하면 된다.*

<br/>
<br/>

>### ***javax.servlet.Filter* 메소드**


- **public void init(FilterConfig filterConfig) throws ServletException**   
필터 인스턴스 초기화
- **public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException**   
전/후 처리, 체인을 따라 다음에 존재하는 필터로 이동한다. 체인의 가장 마지막에는 클라이언트가 요청한 최종 자원이 위치
- **public void destroy()**   
필터 인스턴스 종료

<br/>
<br/>

>### **doFilter()의 흐름**

1. request 파라미터를 이용하여 클라이언트의 요청 필터링
2. chain.doFilter() 메소드 호출
3. response 파라미터를 사용해 클라이언트로 가는 응답 필터링

<br/>

doFilter()메소드는 세번째 파라미터로 FilterChain 객체를 전달받는데, 이는 클라이언트가 요청한 자원에 이르기까지 클라이언트의 요청이 가쳐가게 되는 필터 체인을 나타낸다. **FilterChain을 사용함으로써 필터는 체인에 있는 다음 필터에 변경한 요청과 응답을 건내줄 수 있다.**

<br/>
<br/>

*필터 체인의 순서는 <filter-mapping>이 정의된 순서를 기준으로 정렬.

<br/>
<br/>

내용 참조 : https://goddaehee.tistory.com/154, https://yzlosmik.tistory.com/24, https://dololak.tistory.com/602
