package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;
	
	String saveDir = "C:\\javaStudy\\upload";
	
	public GalleryVo restore(MultipartFile file,String text,int user_no) {
		//원파일 이름
		String orgName = file.getOriginalFilename();
	    //확장자
	    String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
	    //저장파일 이름
	    String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
	    //파일패스
	    String filePath = saveDir + "\\" + saveName;
	    System.out.println("filePath : "+filePath);
	    //파일사이즈
	    int fileSize = (int)file.getSize();
	    try {
			byte[] fileData = file.getBytes();
		    OutputStream out = new FileOutputStream(filePath);
		    BufferedOutputStream bout = new BufferedOutputStream(out);
		    bout.write(fileData);
		    bout.close();
		    }
		    catch(IOException e) {
		    	e.printStackTrace();
		    }
	    GalleryVo vo = new GalleryVo(user_no,text,filePath,orgName,saveName,fileSize);
		
		return vo;
	}
	
	public int insertGallery(GalleryVo vo) {
		return galleryDao.insertGallery(vo);
	}
	public List<GalleryVo> getList(){
		return galleryDao.getList();
	}
	public int remove(GalleryVo galleryVo) {
		return galleryDao.remove(galleryVo);
	}

}
