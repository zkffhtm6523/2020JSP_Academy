package com.koreait.matzip.rest;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.matzip.rest.model.RestDMI;
import com.koreait.matzip.rest.model.RestPARAM;
import com.koreait.matzip.user.model.UserVO;

@Mapper
public interface RestMapper {
	public List<RestDMI> selRestList(RestPARAM p);
	public int insRest(RestPARAM p);
}
