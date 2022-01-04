
> ### java.lang.ClassNotFoundException: org.slf4j.Logger 

<br/>

<img width="70%" src="https://user-images.githubusercontent.com/75427390/147924698-eb4f66f0-dfa1-49ba-9dde-019771ba18dc.png"/>

<br/>

몇달 만에 프로젝트를 실행시켰는데 이런 오류가 떴다.  
slf4j 관련 dependency는 추가되어 있는 상태고 구글링하면서 해당 dependency를 업데이트해봤는데도 계속 오류가 났다.   

<br/>

해결은 간단하게도... 이클립스에서 프로젝트 우클릭-Maven-Update Project로 dependency를 일괄 업데이트했더니 정상적으로 실행됐다^^;
