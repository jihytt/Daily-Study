
# Filter / Interceptor / AOP

<br/>
 

>## 4. Interceptor

<br/>

- `Interceptor`는 컨트롤러에 들어오는 요청 **HttpRequest**와 컨트롤러가 응답하는 **HttpResponse**를 가로채는 역할을 한다.
- DispatcherServlet과 Controller의 사이에 존재
- ✔ **`Filter`와의 차이점** :   
`filter`는 웹어플리케이션 내에서만 접근이 가능하지만 `interceptor`는 **스프링 내의 모든 객체**에 접근 가능하다.

- **HandlerInterceptor** 상속받음

<br/>

출처 : https://rongscodinghistory.tistory.com/2

<br/>
<br/>

>### ***HandlerInterceptor* 메소드**

<br/>

- **`preHandle`** : DispatcherServlet에서 Controller로 보내주는 부분, 특정 조건에 맞으면 넘어가고 특정 조건에 안맞으면 다른 데로 넘어가게 할 수 있다.
- **`postHandle`** : Controller에서 DispatcherServlet으로 보내주는 부분, 컨트롤러가 수행되고 화면에 보여지기 직전에 수행된다.
- **`afterCompletion`** : View rendering 된 후, view가 실행된 후 처리

<br/>
<br/>
