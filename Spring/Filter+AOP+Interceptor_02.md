
# Filter / Interceptor / AOP

<br/>
 

>## 3. AOP
**스프링의 3대 기반 기술 중 하나, *"OOP를 더 OOP 답게'***

<br/>

`AOP`는 *Aspect Oriented Programming*의 약자로 '**관점 지향 프로그래밍**' 이라고 불린다. '관점'이라는 용어는 개발자들에게는 '**관심사**(concern)'라는 말로 통용된다. '관심사'는 개발 시 필요한 고민이나 염두에 두어야 하는 일이라고 생각할 수 있다. 코드를 작성하며 염두에 두는 일들은 주로 다음과 같다.

- 파라미터가 올바르게 들어왔는지
- 이 작업을 하는 사용자가 적절한 권한을 가진 사용자인지
- 이 작업에서 발생할 수 있는 모든 예외는 어떻게 처리해야 하는지

<br/>

위와 같은 사항들은 '핵심 로직'은 아니지만 코드를 온전하게 위해서 필요한데, AOP가 추구하는 것은 '관심사의 분리'이다. AOP는 개발자가 염두에 두어야 하는 일들은 별도의 '관심사'로 분리하고, 핵심 비지니스 로직만을 작성할 것을 권장한다.   
실제 실행은 개발자가 작성한 코드와 분리된 관심사가 결합된 상태의 코드가 실행된다. -> 개발자는 핵심 비지니스 로직에만 근거해 코드를 작성하고, 나머지는 어떤 관심사들과 결합할 것인지를 설정하는 것 만으로 모든 개발을 마칠 수 있다.

<br/>

**AOP를 사용하는 이유**
- 모듈을 상속받아 공통 기능을 부여하기에는 한계가 있음. (JAVA는 클래스 다중 상속 불가능)
- 기능 구현부분에 핵심코드와 공통기능코드가 섞여있을 시 보기에 불편하고 효율성이 떨어짐.

<br/>

---

<br/>

### **<정리>**   

**AOP**는 기능을 핵심 기능(비지니스 로직)과 공통 기능(공통 모듈)으로 구분한다. 공통 기능을 필요로 하는 핵심 기능들에서 사용하는 방식.

<br/>

**장점**   
: 공통 기능을 별도의 모듈에서 개발하여 적용하므로, 여러 서비스에 걸쳐있는 반복적 작업을 줄일 수 있어 **생산성이 높아진다.**   
이후 요구사항 변경 시에도 유연하게 대처할 수 있으므로, **유지보수성이 높아진다.**

<br/>

**사용 방법**   
1. 공통 기능을 정의
2. IOC 기반 하에, AOP 설정을 **pointcut**으로 적용대상을 지정
3. **advice**로 공통 기능 적용 시점을 지정

<br/>
<br/>

**[ AOP 적용 전 ]**

<img width="80%" src="https://user-images.githubusercontent.com/75427390/145671980-aad42216-e26a-4dc4-992a-e8e6bccd1d19.png">

<br/>

**[ AOP 적용 후 ]**

<img width="70%" src="https://user-images.githubusercontent.com/75427390/145671984-b49dc4df-bc8d-4629-bf22-7c662a5dd6c4.png">   

*위빙 : 횡단 관심 사항을 엮어준다.   
<span style="color:gray">사진 출처 - https://creamilk88.tistory.com/148</span>

<br/>
<br/>

>### AOP 주요 용어

<br/>

- **`Aspect`** : 공통 기능 (흩어진 관심사 모듈화한 것) ex. 로깅, 트랜잭션 관리 등
- **`Target`** : `Aspect`가 적용된 객체 (클래스, 메서드 ..)
- **`Advice`** : 실질적으로 어떤 일을 해야할 지에 대한 것, 실직적인 부가기능을 담은 구현체
- **`JoinPoint`** : `Advice`가 적용될 위치, 끼어들 수 있는 지점. 메서드 진입 시점, 생성자 호출 시점, 필드에서 값을 꺼내올 때 등 다양한 시점에 적용 가능
- **`PointCut`** : `JoinPoint`의 부분으로 실제로 `Advice`가 적용된 부분. 'A란 메서드의 진입 시점에 호출할 것'과 같이 더욱 구체적으로 `Advice`가 실행될 지점을 정할 수 있음.
- **`Weaving`** : 애플리케이션의 적절한 시점에 aspect를 적용하는 것을 말함

<br/>

>### AOP Advice 유형

<br/>

- **`Before`** : 메서드 실행 전에 실행하는 Advice
- **`After Returning`** : 메서드 정상 실행 후 실행하는 advice
- **`After Throwing`** : 메서드 실행 시 예외 발생 시 실행하는 Advice
- **`After`** : 메서드 정상 실행 또는 예외 발생 상관없이 실행하는 Advice
- **`Around`** : 위 네가지 Advice를 모두 포함, 모든 시점에서 실행할 수 있는 Advice

<br/><br/>

>### 스프링 AOP 특징

<br/>

- **프록시 패턴** 기반의 AOP 구현체   
프록시 객체를 쓰는 이유는 접근 제어 및 부가 기능을 추가하기 위해서!   


    client(호출부) -> proxy -> Target(핵심기능)

    1. client가 프록시에 요청해서 공통 기능을 실행
    2. 프록시가 다시 Target으로 가서 핵심 기능 실행
    3. 다시 공통 기능을 실행하기 위해 프록시로 와서 공통 기능 실행   
    <span style="color:gray">(프록시가 중간에서 '대행' 역할을 한다)</span>

    <br/>

    ```
    ✏ 프록시 패턴이란 ? 프록시 객체는 원래 객체를 감싸고 있는 객체로 원래 객체와 타입은 동일하다. 프록시 객체가 원래 객체를 감싸서 client의 요청을 처리하게 하는 패턴.   
    접근을 제어하고 싶거나, 부가 기능을 추가하고 싶을 때 사용   
    ```


- 스프링 빈에만 AOP 적용 가능   
- 모든 AOP 기능을 제공하는 것이 아닌 스프링 IoC와 연동하여 애플리케이션에서 가장 흔한 문제(중복코드, 프록시 클래스 작성의 번거로움, 객체들 간 관계 복잡도 증가 ...)에 대한 해결책을 지원하는 목적

<br/>
<br/>

>### AOP 적용

<br/>

1. **pom.xml에 AOP관련 라이브러리 작성**
    ```xml
        <!-- aspectj weaver 라이브러리 -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>${org.aspectj-version}</version>
        </dependency>
    ```

    <br/>

2. **공통기능이 될 클래스 LogAop 작성**

    ```java
    public class LogAop {
        
        public void before(JoinPoint join) {
            Logger logger = LoggerFactory.getLogger(join.getTarget() + "");	// 대상 객체 (연결될 cc)
            logger.info("----------AOP Start----------");
            
            Object[] args = join.getArgs();	// 대상 아규먼트 (넘어가는 값이 있는지)
            if (args != null) {
                // 만일 있으면 대상 메서드 정보 가져와서 몇번째 아규먼트가 넘어가는거 반복해서 출력
                logger.info("method : " + join.getSignature().getName());
                for (int i = 0; i < args.length; i++) {
                    logger.info(i + "번째 : " + args[i]);
                }
            }
        }
        
        public void after(JoinPoint join) {
            Logger logger = LoggerFactory.getLogger(join.getTarget() + "");
            logger.info("-------AOP End------");
        }
        
        public void afterThrowing(JoinPoint join) {
            Logger logger = LoggerFactory.getLogger(join.getTarget() + "");
            logger.info("------AOP Error------");
            logger.info("ERROR : " + join.getArgs());
            logger.info("ERROR : " + join.toString());
        }
    }
    ```

    <br/>

3. **WEB-INF/spring/appServlet/aop-context.xml 작성**   

    *Namespace `aop` 체크   

    <img width="50%" src="https://user-images.githubusercontent.com/75427390/145674150-086f129d-46e5-49a1-b6ad-4f9c4126a50a.png">

    <br/>

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.1.xsd">

	<bean name="logAop" class="com.jh.study.common.aop.LogAop" />
	
	<aop:config>
		<aop:pointcut expression="execution(public * com.jh.study.model.dao.*Dao*.*(..))" id="daoPoint"/>
		<aop:aspect id="logAop" ref="logAop">
			<aop:before method="before" pointcut-ref="daoPoint"/>
			<aop:after-throwing method="after" pointcut-ref="daoPoint"/>
			<aop:after-throwing method="afterThrowing" pointcut-ref="daoPoint"/>
		</aop:aspect>
	</aop:config>

</beans>

<br/>

4. **AOP를 web.xml에 등록**   
contextConfigLocation - /WEB-INF/spring/appServlet/aop-context.xml

<br/>

5. **src/main/resources/log4j.xml**



<br/>
<br/>

내용 참조 : https://engkimbs.tistory.com/746, https://hongku.tistory.com/114, https://creamilk88.tistory.com/148
