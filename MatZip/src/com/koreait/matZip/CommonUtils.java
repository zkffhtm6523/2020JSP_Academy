package com.koreait.matZip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommonUtils {
	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}
}
