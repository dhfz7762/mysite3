package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//로그인 --> 세션 저장용
	public UserVo selectUser(UserVo userVo) {
		System.out.println("UserDao.selectUser");
		UserVo authUser = sqlSession.selectOne("user.selectUser",userVo);
		return authUser;
	}
		
	//회원등록
	public int insertUser(UserVo userVo) {
		System.out.println("UserDao.insertUser()");
		
		int count= sqlSession.insert("user.insert",userVo);
		return count;
	}
	
	//회원정보수정폼용(회원정보1명 가져오기)
    public UserVo selectUser(int no) {
    	System.out.println("UserDao.selectUser()");
        UserVo userVo = sqlSession.selectOne("user.selectUserByNo", no);
    	return userVo;
    }
    
    //회원정보수정
    public int updateUser(UserVo userVo) {
    	System.out.println("UserDao.updateUser()");
    	System.out.println("update"+userVo);
    	int count = sqlSession.update("user.updateUser", userVo);
    	return count;
    }
    
    
    //아이디 중복체크
    public UserVo selectUser(String id) {
    	System.out.println("UserDao.selectUser");
    	UserVo userVo = sqlSession.selectOne("user.selectUserById",id);
    	
    	return userVo;
    }
    
}