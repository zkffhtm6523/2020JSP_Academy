package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

public class BoardDAO {
	public static int uptDetailBoardList(BoardVO param) {
		String sql = " update t_board4 set title = ?, ctnt = ?, m_dt = sysdate where i_board = ? ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_board());
			}
		});
	}
	
	public static BoardVO selDetailBoardList(BoardVO param) {
	String sql = " select t_board4.title, t_board4.r_dt, t_board4.hits, t_board4.ctnt, t_user.nm, t_user.i_user "
				+" from t_board4 "
				+" inner join t_user "
				+" on t_board4.i_user = t_user.i_user "
				+" where t_board4.i_board = "+param.getI_board();
	
	JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
		
		@Override
		public void prepared(PreparedStatement ps) throws SQLException {}
		
		@Override
		public int executeQuery(ResultSet rs) throws SQLException {
			if(rs.next()) {
				String title = rs.getNString("title");
				String nm = rs.getNString("nm");
				String r_dt = rs.getNString("r_dt");
				String ctnt = rs.getNString("ctnt");
				int hits = rs.getInt("hits");
				int i_user = rs.getInt("i_user");
				
				param.setTitle(title);
				param.setNm(nm);
				param.setR_dt(r_dt);
				param.setCtnt(ctnt);
				param.setHits(hits);
				param.setI_user(i_user);
			}
			return 1;
		}
	});
		return param;
	}
	
	public static List<BoardVO> selBoardList() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		String sql = " select t_board4.i_board, t_board4.title, t_board4.r_dt, t_board4.i_user, t_board4.hits, t_user.nm "
					+ " from t_board4 "
					+ " inner join t_user "
					+ " on t_board4.i_user = t_user.i_user "
					+ " order by t_board4.i_board desc ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					int i_board = rs.getInt("i_board");
					String title = rs.getNString("title");
					String r_dt = rs.getNString("r_dt");
					int i_user = rs.getInt("i_user");
					int hits = rs.getInt("hits");
					String nm = rs.getNString("nm");
					
					BoardVO vo = new BoardVO();
					vo.setI_board(i_board);
					vo.setTitle(title);
					vo.setHits(hits);
					vo.setI_user(i_user);
					vo.setR_dt(r_dt);
					vo.setNm(nm);
					
					list.add(vo);
				}
				return 1;
			}			
		});
		
		return list;
	}
	public static int insBoardList(BoardVO param) {
		String sql = " INSERT INTO t_board4 (i_board, title, ctnt, i_user) "
				+" select nvl(max(i_board),0)+1, ?, ?, ? "
				+" from t_board4 " ;
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_user());
			}
		});
		
	}

	public static void delBoardList(BoardVO param) {
		String sql = " delete from t_board4 where i_board = "+param.getI_board();
		
		JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {}
		});
	}
}