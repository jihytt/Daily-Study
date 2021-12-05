# jQuery의 each() 메서드

며칠 전 본 회사 면접에서 each 함수를 사용한 문제를 풀게 됐다.   
오랜만에 제이쿼리 써보려니 당황스러웠다. 공부는 제발 꾸준하게 하기...   

<br/>

✏ **jquery each 함수를 사용하여 아래 배열 중 짝수의 합계를 구하시오.**   

```JavaScript
var result = 0;
var array = [02, 85, 61, 30, 57, 73, 44];

$.each(array, function (index, element){
    if(element%2 == 0) {
        result += element;
    }
});
console.log(result);
```

<br/>

- `$.each()` 메서드는 object와 배열 모두에서 사용할 수 있는 반복 함수   
- 배열, length 속성을 갖는 배열과 유사 배열 객체들은 index를 기준으로 반복할 수 있다.   
- 첫 번째 매개변수로 **배열**이나 **객체**를 받는다.   
- 두 번째 매개변수로 콜백함수를 받으며 콜백함수의 인자로는 **인덱스**와 **값**을 인자로 갖는다.



