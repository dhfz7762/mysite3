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
		int count = sqlSession.insert("mysite.insertUser",userVo);
		return count;
	}
	public UserVo loginUser(UserVo userVo) {
		return sqlSession.selectOne("mysite.loginUser",userVo);
	}
	public int modifyUser(UserVo userVo) {
		System.out.println(userVo);
		return sqlSession.update("mysite.modifyUser",userVo);
	}

}
