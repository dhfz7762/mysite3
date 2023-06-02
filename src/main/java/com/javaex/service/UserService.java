package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public int insertUser(UserVo userVo) {
		return userDao.insertUser(userVo);
	}
	public UserVo loginUser(UserVo userVo) {
		return userDao.loginUser(userVo);
	}
	public int modifyUser(UserVo userVo) {
		return userDao.modifyUser(userVo);
	}
	public UserVo idcheck(String id) {
		return userDao.selectUser(id);
	}

}
