package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertGallery(GalleryVo vo) {
		return sqlSession.insert("gallery.insertGallery", vo);
	}
	public List<GalleryVo> getList(){
		return sqlSession.selectList("gallery.getList");
	}
	public int remove(GalleryVo galleryVo) {
		System.out.println(galleryVo);
		int count= sqlSession.delete("gallery.remove",galleryVo);
		System.out.println(count);
		return count;
	}

}
