package com.koreait.pjt.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/join")
public class JoinSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//get : 화면 띄울 때
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileNm = "user/join";
		ViewResolver.forward(fileNm, request, response);
		//일반적이라면 위와 같은 방법을 했을 것임. jsp는 join만 가만히 있고 나머지는 그대로임..
	}

	//post : 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//join.jsp의 form의 name이 속한 값의 value를 받아온다
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String encrypt_pw = MyUtils.encryptString(user_pw);
		String nm = request.getParameter("nm");
		String email = request.getParameter("email");
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(encrypt_pw);
		param.setNm(nm);
		param.setEmail(email);
		
		int result = UserDAO.insUser(param);
		System.out.println(result);
		
		if(result != 1) {
			//"에러가 발생하였습니다. 관리자에게 문의하십시오"
			request.setAttribute("msg", "에러가 발생했습니다. 관리자에게 문의하세요");
			request.setAttribute("data", param);
			doGet(request,response);
			return;
		}
		String fileNm = "user/login";
		ViewResolver.forward(fileNm, request, response);
		
		System.out.println("로그인 성공!!");
	}
}
