package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int addGuestBook(GuestbookVo guestbookVo) {
		return sqlSession.insert("guestbook.addGuestBook", guestbookVo);
	}
	public List<GuestbookVo> getAddList(){
		return sqlSession.selectList("guestbook.getAddList");
	}
	public int deleteGuestbook(GuestbookVo guestbookVo) {
		return sqlSession.delete("guestbook.deleteGuestbook",guestbookVo);
	}
	

}
