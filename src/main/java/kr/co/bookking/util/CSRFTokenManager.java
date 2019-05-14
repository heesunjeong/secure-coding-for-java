package kr.co.bookking.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

// 토큰 발행 , session, request
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
