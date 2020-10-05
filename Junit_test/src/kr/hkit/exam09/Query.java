package kr.hkit.exam09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Query {
	
	//DB 연결
	public static Connection getConn() {
		Connection conn = null;
		String url = "jdbc:mysql://127.0.0.1:3306/exam?characterEncoding=UTF-8";
		String user = "root";
		String pw = "koreait2020";
		
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pw);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	//DB 연결 해제
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs != null) { rs.close(); }			
		} catch (SQLException e) {e.printStackTrace();}
		
		try {
			if(ps != null) { ps.close(); }			
		} catch (SQLException e) {e.printStackTrace();}
		
		try {
			if(con != null) { con.close(); }			
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	//테이블 생성
	public static void createTable() {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " CREATE TABLE T_BOARD "
				+ " ( bid INT,  "
				+ " btitle VARCHAR(255), "
				+ " bcontent VARCHAR(255), "				
				+ " PRIMARY KEY (bid)"
				+ " )";		
		try {
			con = getConn();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
	}
	
	//테이블 제거
	public static void dropTable() {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " DROP TABLE t_board ";		
		try {
			con = getConn();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
	}
	
	//레코드 제거
	public static void boardDelete(int bid) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "DELETE FROM t_board ";
		
		if(bid > 0) {
			sql += " where bid = " + bid;
		}
		
		try {
			con = getConn();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
	}
	
	//레코드 등록
	public static void boardInsert(BoardVO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " INSERT INTO t_board (bid, btitle, bcontent)"
				+ " Select ifnull(max(bid), 0) + 1 , ?, ? from t_board ";
		
		try {
			con = getConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getBtitle());
			ps.setString(2, vo.getBcontent());
			
			ps.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
	}
	
	//레코드 리스트
	public static List<BoardVO> getAllBoardList() {
		List<BoardVO> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM t_board ORDER BY bid DESC ";
		
		try {
			con = getConn();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {				
				BoardVO vo = new BoardVO();
				vo.setBid(rs.getInt("bid"));				
				vo.setBcontent(rs.getString("bcontent"));
				vo.setBtitle(rs.getString("btitle"));
				list.add(vo);
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return list;
	}
	
	//레코드 디테일
	public static BoardVO getBoardDetail(int bid) {
		BoardVO vo = new BoardVO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT bid, btitle, bcontent FROM t_board WHERE bid = ? ";
		
		try {
			con = getConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1,  bid);
			rs = ps.executeQuery();
			while(rs.next()) {				
				vo.setBid(rs.getInt("bid"));
				vo.setBtitle(rs.getString("btitle"));
				vo.setBcontent(rs.getString("bcontent"));
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return vo;
	}
	
	//레코드 수정
	public static void boardUpdate(BoardVO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " UPDATE t_board "
				+ " SET btitle = ? "
				+ " , bcontent = ? "
				+ " WHERE bid = ? ";
		
		try {
			con = getConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getBtitle());
			ps.setString(2, vo.getBcontent());
			ps.setInt(3, vo.getBid());
			
			ps.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
	}
}











