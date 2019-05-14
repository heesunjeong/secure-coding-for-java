package kr.co.bookking.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class QnaWritingInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		String sessionToken = (String)session.getAttribute("csrf");
		String requestToken = request.getParameter("csrf");
		
		System.out.println("QnaWritingInterceptor.preHandle()" + requestToken + sessionToken);

		if(!request.getMethod().equals("POST")) {
			
		}
		
		if (requestToken == null || !sessionToken.equals(requestToken)) {
			response.sendRedirect(request.getContextPath() +"/user/userLogin");
			return false;
		}
		
		System.out.println(sessionToken);
		return true;
	}

}
