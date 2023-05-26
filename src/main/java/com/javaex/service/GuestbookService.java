package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	public int addGuestBook(GuestbookVo guestbookVo) {
		return guestbookDao.addGuestBook(guestbookVo);
	}
	public List<GuestbookVo> getAddList(){
		return guestbookDao.getAddList();
	}
	public int deleteGuestbook(GuestbookVo guestbookVo) {
		return guestbookDao.deleteGuestbook(guestbookVo);
	}
	

}
