package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//로그인
	public UserVo login(UserVo userVo) {
		System.out.println("UserService.login()");
		
		UserVo authUser = userDao.selectUser(userVo);
		return authUser;
	}
	
	
	//회원등록
	public int join(UserVo userVo) {
		System.out.println("UserService.join");
		
		int count = userDao.insertUser(userVo);
		return count;
	}
	
	
	//회원정보수정폼
	public UserVo modifyForm(int no) {
		
		System.out.println("UserService.modify()");
		UserVo userVo = userDao.selectUser(no);
		return userVo;
	}
	
	//회원정보수정
	public int modify(UserVo userVo) {
		System.out.println("UserService.modify()");
		int count = userDao.updateUser(userVo);
		return count;
	}
	
	//아이디 중복체크
	public boolean idcheck(String id) {
		System.out.println("UserService.idcheck()");
		UserVo userVo = userDao.selectUser(id);
		
		if(userVo == null) { //사용가능
			return true;
		}else {
			return false;
		}
	}
}
