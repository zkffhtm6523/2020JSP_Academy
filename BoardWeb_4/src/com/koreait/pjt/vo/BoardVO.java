package com.koreait.pjt.vo;

public class BoardVO {
	private int i_board;
	private String title;
	private String ctnt;
	private int hits;
	private int i_user;
	private String r_dt;
	private String m_dt;
	private String nm;
	private int yn_like;
	private int likeCount;
	private int loginUser;
	private int countCmt;
	private int recode_cnt;
	private int eldx;
	
	public int getEldx() {
		return eldx;
	}
	public void setEldx(int eldx) {
		this.eldx = eldx;
	}
	public int getSldx() {
		return sldx;
	}
	public void setSldx(int sldx) {
		this.sldx = sldx;
	}
	private int sldx;
	
	
	public int getRecode_cnt() {
		return recode_cnt;
	}
	public void setRecode_cnt(int recode_cnt) {
		this.recode_cnt = recode_cnt;
	}
	public int getCountCmt() {
		return countCmt;
	}
	public void setCountCmt(int countCmt) {
		this.countCmt = countCmt;
	}
	public int getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(int loginUser) {
		this.loginUser = loginUser;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getYn_like() {
		return yn_like;
	}
	public void setYn_like(int yn_like) {
		this.yn_like = yn_like;
	}
	public int getI_board() {
		return i_board;
	}
	public void setI_board(int i_board) {
		this.i_board = i_board;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCtnt() {
		return ctnt;
	}
	public void setCtnt(String ctnt) {
		this.ctnt = ctnt;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getI_user() {
		return i_user;
	}
	public void setI_user(int i_user) {
		this.i_user = i_user;
	}
	public String getR_dt() {
		return r_dt;
	}
	public void setR_dt(String r_dt) {
		this.r_dt = r_dt;
	}
	public String getM_dt() {
		return m_dt;
	}
	public void setM_dt(String m_dt) {
		this.m_dt = m_dt;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
}
