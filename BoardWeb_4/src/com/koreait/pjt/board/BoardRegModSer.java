package com.koreait.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/regmod")
public class BoardRegModSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//화면 띄우는 용도(등록창/수정창)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		if(hs.getAttribute(Const.LOGIN_USER) == null) {
			response.sendRedirect("/login");
			return;
		}
		if(request.getParameter("i_user") == null) {
			ViewResolver.forward("/board/regmod", request, response);
		}else if(((UserVO)hs.getAttribute(Const.LOGIN_USER)).getI_user() == Integer.parseInt(request.getParameter("i_user"))) {
			BoardVO param = new BoardVO();
			int i_board = Integer.parseInt(request.getParameter("i_board"));
			param.setI_board(i_board);
			param = BoardDAO.selDetailBoardList(param);
			request.setAttribute("data", param);
			ViewResolver.forward("/board/regmod", request, response);
		}
	}
	
	//처리 용도(DB에 등록/수정) 실시
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		System.out.println(request.getParameter("title"));
		System.out.println(request.getParameter("ctnt"));
		System.out.println(request.getParameter("i_board"));
		if(request.getParameter("i_user") == null) {
			System.out.println("업데이트 아님");
			String title = request.getParameter("title");
			String ctnt = request.getParameter("ctnt");
			UserVO u = (UserVO)hs.getAttribute(Const.LOGIN_USER);
			
			BoardVO param = new BoardVO();
			param.setTitle(title);
			param.setCtnt(ctnt);
			param.setI_user(u.getI_user());
			int result = BoardDAO.insBoardList(param);
			response.sendRedirect("/board/list");
			return;
		}else if(request.getParameter("i_user") != null){
			System.out.println("업데이트");
			String strI_board = request.getParameter("i_board");
			String title = request.getParameter("title");
			String ctnt = request.getParameter("ctnt");
			int i_board = Integer.parseInt(strI_board);
			
			BoardVO param = new BoardVO();
			param.setTitle(title);
			param.setCtnt(ctnt);
			param.setI_board(i_board);
			int result = BoardDAO.uptDetailBoardList(param);
			response.sendRedirect("/board/list");
			return;
		}
	}

}