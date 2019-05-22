# secure-coding-for-java
* KOSTA 에서 진행한 Java기반의 시큐어코딩 수업 내용을 정리합니다.
* 수업 기간: 2019/05/13 - 2019/05/14


* * *


#### 웹 어플리 케이션의 보안은 선택이 아니라 아니라 필수이다
인터넷을 통해 웹 서버를 해킹하거나 인터넷에 연결된 내부 사용자 pc를 해킹 시도할수 있고, 감염된 웹 서버나 내부 사용자 pc로 부터2차로 대부 공격이 있을 수 있음.


#### 정보보호 목표
정보의 무결성, 가용성, 기밀성을 높여서 기업의 경쟁력을 확보한다.


#### 대표적 정보 보안 침해 사례
ajax로 요청하는 클라이언트 요청에 로그인 여부를 체크하지않고 고객 명세서를 json응답처리하는 application에 위조된 고객번호를 요청하여 개인 정보가 유출된 사례
SQL injection 공격에 의해 db 정보를 열람, 조작 할 수 있는 취약점을 악용한 공격하는 사례
인증되지 않은 사용자가 파라미터를 조작하여 다른 사용자의 정보 조회할 수 있었던 사례


### 실습
#### sql injection
and , or 중에서 and 연산이 우선순위가 더 높아서 and 진행 후( 'a' = 'a' and user_password = '' ) or 연산 되면서 검색 결과가 나옴
login id : ' or 'a' = 'a / password : ' or 'a' = 'a 넣으면 로그인이 가능
```
select * from bk_user where user_id = '' or 'a' = 'a' and user_password = '' or 'a' = 'a';
```
```
SELECT USER_ID, USER_PASSWORD FROM BK_USER
WHERE USER_ID = '${userId}' AND USER_PASSWORD = '${userPassword}'
``` 
##### mysql에서 #으로 주석처리되어 결과값이 나옴
Prepared Statement 를 사용해서 막기 ${} -> single quotation을 내부에서 사용하도록
```
select * from bk_user where user_id = 'admin'#' and user_password='aaaa';
```

#### 크로스 사이트 스크립팅(XSS)
서버가 클라이언트의 요청을 인증받은 사용자의 요청인지 점검하지 않고 요청을 처리하는 경우 발생할 수 있는 보안 취약점
##### CSRF 토큰 생성
```
public class CSRFTokenManager {

	public static String getTokenFromSession(HttpSession session) {
		String token = null;
		token = (String) session.getAttribute("csrf");
		
		if(token == null) {
			token = UUID.randomUUID().toString();
			session.setAttribute("csrf", token);
		}
		
		return token;
	}
	
	public static String getTokenFromRequest(HttpServletRequest request) {
		return request.getParameter("csrf");
	}
}
```
```
public class QnaWritingInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		String sessionToken = (String)session.getAttribute("csrf");
		String requestToken = request.getParameter("csrf");
		
		if (requestToken == null || !sessionToken.equals(requestToken)) {
			response.sendRedirect(request.getContextPath() +"/user/userLogin");
			return false;
		}
		return true;
	}
}
```

#### 캡슐화 위배
```
public class EncapsuleRoleTest {
	public static void main(String[] args) {
		Role role = Role.getInstance();
		System.out.println(role.adminList());
		
		String[] list = {"user"};
		// role이 가지고있는 객체의 주소 참조
		list = role.getRoles();
		
		// role의  private member 변수가 변경됨
		list[0] = "member";
	}
}

```
getter, setter 메소드 구현할 때 객체를 복사해서 반환하도록 수정
```
public class Role {
	private String[] roles = {"admin", "root", "manager"};
	private static Role role;
	
	public static Role getInstance() {
		if (role == null) {
			role = new Role();
		}
		
		return role;
	}

	public String[] getRoles() {
		return roles;
		// 객체를 복제해서 리턴하도록 수정
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}
}
```

#### Password 암호화 하여 저장
Password를 해쉬로 변환하여 저장, salt적용,  SHA방식 채택, md5로 암호화하더라도 특수문자, 대문자 등 섞어서 만들도록 유도하여 복호화 어렵게 하기
```
var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
``` 

#### 보안 약점 진단 도구 사용
[findbugs](http://findbugs.cs.umd.edu/eclipse/) - eclipse plugin, PMD, Lapse+

