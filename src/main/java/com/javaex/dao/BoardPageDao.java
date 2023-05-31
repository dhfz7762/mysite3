package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardPageVo;
import com.javaex.vo.BoardVo;

@Repository
public class BoardPageDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int countBoard() {
		return sqlSession.selectOne("boardpage.countBoard");
				
	}
	public List<BoardVo> selectBoard(BoardPageVo vo) {
		return sqlSession.selectList("boardpage.selectBoard",vo);
	}
	public List<BoardVo> searchBoard(BoardPageVo vo,String option) {
		if(option.equals("username")) {
			return sqlSession.selectList("boardpage.selectUsernameBoard",vo);
		}
		else {
			return sqlSession.selectList("boardpage.selectTitleBoard",vo);
		}
		
	}

}
