package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.koreait.matzip.user.model.UserPARAM;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter{
	//preHandle controller이 시작되기 전
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {		
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		
		
		if(uri.equals("/")) {
			return true;
		} else if(uriArr[1].equals("res")) { //리소스 (js, css, img)
			return true;
		} 
		
		System.out.println("인터셉터!!");
		boolean isLogout = SecurityUtils.isLogout(request);
					
		switch(uriArr[1]) {	
		case ViewRef.URI_USER : //user
			switch(uriArr[2]) {
			case "login": case "join": 
				if(!isLogout) { //로그인 되어 있는 상태
					response.sendRedirect("/rest/map");
					return false;
				}
			}
		case ViewRef.URI_REST : //rest
			switch(uriArr[2]) {
			case "reg" :
				if(isLogout) { //로그아웃 상태
					response.sendRedirect("/user/login");
					return false;
				}
			}
		}
		
		return true;
	}
}
