package com.koreait.pjt.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardCmtDAO;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardCmtVO;
import com.koreait.pjt.vo.BoardVO;

@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session을 먼저 받아오고 getattribute를 해야함
		//매개변수에 request가 있고 넘어오고, 넘겨주니까 이것을 바로 씀.
		//session은 받아오는 것이 없으니까 request로 getSession으로 받아와야함
		//pageContext는 서블릿이 아닌 jsp파일에서 사용할 수 있음.
		HttpSession hs = request.getSession();
		if(hs.getAttribute(Const.LOGIN_USER) == null) {
			response.sendRedirect("/login");
			return;
		}
		int page = MyUtils.getIntParameter(request, "page");
		page = (page == 0 ? 1 : page);
		
		//댓글 카운트 구현
		BoardCmtVO cmt = new BoardCmtVO();
		cmt = BoardCmtDAO.selCountCmt(cmt);
		
		//페이지 표시할 갯수
		BoardVO vo = new BoardVO();
		vo.setRecode_cnt(Const.RECORD_CNT);
		vo.setEldx(page*Const.RECORD_CNT);
		vo.setSldx(vo.getEldx()-Const.RECORD_CNT);
		
		
		//게시판 리스트 가져오기
		List <BoardVO> list = BoardDAO.selBoardList(vo);
		
		//int값 반환
		request.setAttribute("currentPage", page);
		request.setAttribute("pagingCnt", BoardDAO.selPagingCnt(vo));
		request.setAttribute("list", list);
		String fileNm = "/board/list";
		ViewResolver.forward(fileNm, request, response);
	}
}
