package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int writeBoard(BoardVo boardVo) {
		int count = sqlSession.insert("mysite.writeBoard",boardVo);
		return count;
	}
	public List<BoardVo> getBoardList(){
		List<BoardVo> boardList = sqlSession.selectList("mysite.getBoardList");
		return boardList;
	}
	public int hitBoard(int no) {
		return sqlSession.update("mysite.hitCount",no);
	}
	public BoardVo readBoard(int no) {
		BoardVo readBoard = sqlSession.selectOne("mysite.readBoard", no);
		return readBoard;
	}
	public int modifyBoard(BoardVo boardVo) {
		return sqlSession.update("mysite.modifyBoard",boardVo);
	}
	public int deleteBoard(int no) {
		return sqlSession.delete("mysite.deleteBoard", no);
	}
	


}
