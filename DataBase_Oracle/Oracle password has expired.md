
### **ORA-28001: the password has expired** : 오라클 비밀번호 만료

<br/>

어제까지 잘만 되던 데이터베이스에서 오류가 나길래 찾아보니 오라클 비밀번호가 만료된 것이었다!   
오라클 11g r2가 나왔을 때 180일이 지나면 비밀번호가 만료되도록 변경되었다고 한다.

<br/>
<br/>

**비밀번호 변경하기**

```SQL
ALTER USER user_id IDENTIFIED BY new_password;
```

<br/>
<br/>

**비밀번호가 만료되지 않게 설정**

```SQL
ALTER PROFILE DEFAULT LIMIT PASSWORD_LIFE_TIME UNLIMITED;
```

<br/>
<br/>

명령어를 찾아본 뒤에 Cmd에서 SQLPLUS 실행하고 기존 비밀번호를 입력했더니

```cmd
Enter user-name: 유저네임
Enter password:
ERROR:
ORA-28001: the password has expired


Changing password for 유저네임
New password:
Retype new password:
Password changed

Connected to:
Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production
```

비밀번호가 만료되었다는 메세지와 함께 새로운 비밀번호를 설정할 수 있는 메세지가 떴다!! 명령어를 따로 입력하지 않아도 알아서 바꿔주나보다ㅎㅎ

