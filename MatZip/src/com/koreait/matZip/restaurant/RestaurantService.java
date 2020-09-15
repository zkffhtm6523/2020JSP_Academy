package com.koreait.matZip.restaurant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.koreait.matZip.CommonUtils;
import com.koreait.matZip.FileUtils;
import com.koreait.matZip.vo.RestaurantDomain;
import com.koreait.matZip.vo.RestaurantRecommendMenuVO;
import com.koreait.matZip.vo.RestaurantVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class RestaurantService {
	
	private RestaurantDAO dao;
	
	public RestaurantService() {
		dao = new RestaurantDAO();
	}
	
	public int restReg(RestaurantVO param) {
		return dao.insRestaurant(param);
	}
	
	public String getRestList(){
		List<RestaurantDomain> list = dao.selRestList(); 
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	public RestaurantDomain getRest(RestaurantVO param) {
		return dao.selRest(param);
	}
	public int addRecMenus(HttpServletRequest request) {
		String savePath = "/res/img/restaurant";
		String tempPath = request.getServletContext().getRealPath(savePath + "/temp");		
		FileUtils.makeFolder(tempPath);
		
		int maxFileSize = 10_485_760; //1024 * 1024 * 10 (10mb) //최대 파일 사이즈 크기
		MultipartRequest multi = null;
		int i_rest = 0;
		String[] menu_nmArr = null;
		String[] menu_priceArr = null;
		List<RestaurantRecommendMenuVO> list = null;
		try {
			multi = new MultipartRequest(request, tempPath, maxFileSize, "UTF-8", new DefaultFileRenamePolicy());
			
			i_rest = CommonUtils.getIntParameter("i_rest", multi);
			
			System.out.println("i_rest : " + i_rest);
			menu_nmArr = multi.getParameterValues("menu_nm");
			menu_priceArr = multi.getParameterValues("menu_price");
			
			if(menu_nmArr == null || menu_priceArr == null) {
				return i_rest;
			}
			
			list = new ArrayList();
			
			for(int i=0; i<menu_nmArr.length; i++) {
				RestaurantRecommendMenuVO vo = new RestaurantRecommendMenuVO();
				vo.setI_rest(i_rest);
				vo.setMenu_nm(menu_nmArr[i]);
				vo.setMenu_price(CommonUtils.parseStringToInt(menu_priceArr[i]));
				list.add(vo);
			}	
			
			
			String targetPath = request.getServletContext().getRealPath(savePath + "/" + i_rest);
			FileUtils.makeFolder(targetPath);
			
			String fileNm = "";
			String saveFileNm = "";
			Enumeration files = multi.getFileNames();
			while(files.hasMoreElements()) {		
				String key = (String)files.nextElement();
				System.out.println("key : " + key);
				fileNm = multi.getFilesystemName(key);
				System.out.println("fileNm : " + fileNm);
				
				if(fileNm != null) {
					String ext = FileUtils.getExt(fileNm);
					saveFileNm = UUID.randomUUID() + ext;
					
					System.out.println("saveFileNm : " + saveFileNm);				
					File oldFile = new File(tempPath + "/" + fileNm);
				    File newFile = new File(targetPath + "/" + saveFileNm);
				    oldFile.renameTo(newFile);	
				    
				    int idx = CommonUtils.parseStringToInt(key.substring(key.lastIndexOf("_") + 1));				    
				    RestaurantRecommendMenuVO vo = list.get(idx);
				    vo.setMenu_pic(saveFileNm);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(list != null) {
			for(RestaurantRecommendMenuVO vo : list) {
				dao.insRecommendMenu(vo);
			}	
		}
		
		return i_rest;
		}
	public List<RestaurantRecommendMenuVO> getRecommendMenuList(int i_rest) {
		return dao.selRecommendMenuList(i_rest);
	}
	public int delRecMenu(RestaurantRecommendMenuVO param) {
		return dao.delRecommendMenu(param);
	}
	
}
