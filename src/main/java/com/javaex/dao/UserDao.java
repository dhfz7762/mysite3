package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertUser(UserVo userVo) {
		int count = sqlSession.insert("user.insertUser",userVo);
		return count;
	}
	public UserVo loginUser(UserVo userVo) {
		return sqlSession.selectOne("user.loginUser",userVo);
	}
	public int modifyUser(UserVo userVo) {
		System.out.println(userVo);
		return sqlSession.update("user.modifyUser",userVo);
	}
	public UserVo selectUser(String id) {
		UserVo dd = sqlSession.selectOne("user.selectUser",id);
		System.out.println(dd);
		return dd;
	}

}
