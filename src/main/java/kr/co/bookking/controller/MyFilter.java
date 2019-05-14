package kr.co.bookking.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("Start of MyFilter, dofilter()");
		String ip = request.getRemoteAddr();
		if (ip.startsWith("192.")) {
			chain.doFilter(request, response);
		}
		// 
		System.out.println("End of MyFilter, dofilter()");
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
