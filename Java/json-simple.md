
<br/>

> ## 1. **json-simple** 이란?

<br/>

**JSON 객체를 쉽게 처리하기 위한** 자바 라이브러리

<br/>

### **json-simple 특징**   

- 내부적으로 JSON 데이터를 처리하기 위해 Map과 List를 사용한다.
- JSON 데이터를 구문 분석하고 JSON을 파일에 기록할 수 있다.
- 매우 가벼운 API이며 간단한 JSON 데이터를 처리하기 위해 적합하다.

<br/>
<br/>

### **json-simple 주요 클래스**
- **org.json.simple Class JSONObejct**   
JSON 객체를 추상화한 클래스로, java.util.HashMap 클래스를 상속받고 있으므로 대부분의 메소드가 HashMap 클래스로부터 상속받고 있다.

- **org.json.simple Class JSONArrayJSON**  
배열을 추상화한 클래스로, java.util.ArrayList 클래스를 상속하고 있으므로 메소드 사용 방법은 대부분 ArrayList와 거의 흡사하다.

- **org.json.simple Class JSONParser**   
JSON 데이터를 파싱하는 기능을 구현한 클래스

- **org.json.simple Class JSONValue**  
JSON 데이터를 다루기 위한 몇 가지 메소드들을 제공

- **org.json.simple Class JSONException**   
JSONParser 클래스를 사용해서 파싱할 때 발생할 수 있는 예외 사항을 추상화한 클래스 

<br/>
<br/>

> ## 2. JSONObject 객체를 이용한 JSON 객체 생성

<br/>

```java
JSONObject jsonObject = new JSONObject();
obj.put("name", "apple");
obj.put("color", "red");
obj.put("category", "fruit");

System.out.println(jsonObject.toJsonString());
```

<br/>

위의 코드 실행 시 다음 결과를 얻게 된다.

<br/>

```
{"color":"red", "name":"apple", "category":"fruit"}
``` 

<br/>

JSONObject 클래스가 HashMap 클래스를 상속받고 있기 때문에 입력한 순서와는 상관없는 순서로 출력된다.

<br/>

<img width="70%" src="https://user-images.githubusercontent.com/75427390/146636425-42d3a202-983e-48e3-8903-0e67b419d21a.png">

<br/>

코드 작성 시 위와 같은 경고가 뜨는데 Generic을 통해 타입을 지정해 safety하게 데이터를 입력하라고 말해주고 있다.

<br/>
<br/>

> ## 3. HashMap을 통해 데이터 넣기

<br/>

```java
HashMap<String, String> hashMap = new HashMap<String, String>();
hashMap.put("name", "apple");
hashMap.put("color", "red");
hashMap.put("category", "fruit");

JSONObject jsonObject = new JSONObject(hashMap);

System.out.println(jsonObject.toJSONString());
```

<br/>
<br/>

> ## 4. JSONArray를 이용한 JSON 객체 배열 생성

<br/>

JSONArray는 JSONObject의 배열이라고 생각하면 된다.

<br/>

```java
HashMap<String, String> hashMap = new HashMap<String, String>();
hashMap.put("name", "apple");
hashMap.put("color", "red");
hashMap.put("category", "fruit");
JSONObject apple = new JSONObject(hashMap);

hashMap = newHashMap<String, String>();
hashMap.put("name", "banana");
hashMap.put("color", "yellow");
hashMap.put("category", "fruit");
JSONObject banana = new JSONObject(hashMap);

JSONArray jsonArray = new JSONArray();
jsonArray.add(apple);
jsonArray.add(banana);

System.out.println(jsonArray.toJSONString());
```

<br/>

**출력결과** 🔽

<br/>

```
[{"color":"red", "name":"apple", "category":"fruit"},{"color":"yellow", "name":"banana", "category":"fruit"}]
```

<br/>
<br/>

> ## 5. JSONParser : 문자열을 JSONObject 객체로

<br/>

HashMap, ArrayList 같은 자바 객체에서 JSON 문자열을 만들어 내는 것이 `JSONObject`, `JSONArray`의 역할이었다면 **`JSONParser`**는 **JSON 문자열에서 JSONObject 객체를 만들어내는 역할**을 한다.

<br/>

```java
String jsonString = "{\"color\":\"red\", \"name\":\"apple\", \"category\":\"fruit\"}";

JSONParser jsonParser = new JSONParser();
JSONObject jsonObject;

try {
    jsonObject = (JSONObject) jsonParser.parse(jsonString);
    String str = (String) jsonObject.get("color");

    System.out.println(str);
} catch (ParseException e) {
    e.printStackTrace();
}
```

**JSONArray**로도 파싱을 할 수 있다.

```java
String jsonString = "[{\"color\":\"red\", \"name\":\"apple\", \"category\":\"fruit\"},{\"color\":\"yellow\", \"name\":\"banana\", \"category\":\"fruit\"}]";

JSONParser jsonParser = new JSONParser();
JSONArray jsonArray;

try {
    jsonArray = (JSONArray) jsonParser.parse(jsonString);
    
    for (Object obj : jsonArray) {
        System.out.println(((JSONObject)obj).get("name"));
    }

} catch (ParseException e) {
    e.printStackTrace();
}

```


<br/>
<br/>

참조 출처 : https://tychejin.tistory.com/139


