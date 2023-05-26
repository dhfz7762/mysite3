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
		int count = sqlSession.insert("board.writeBoard",boardVo);
		return count;
	}
	public List<BoardVo> getBoardList(){
		List<BoardVo> boardList = sqlSession.selectList("board.getBoardList");
		return boardList;
	}
	public int hitBoard(int no) {
		return sqlSession.update("board.hitCount",no);
	}
	public BoardVo readBoard(int no) {
		BoardVo readBoard = sqlSession.selectOne("board.readBoard", no);
		return readBoard;
	}
	public int modifyBoard(BoardVo boardVo) {
		return sqlSession.update("board.modifyBoard",boardVo);
	}
	public int deleteBoard(int no) {
		return sqlSession.delete("board.deleteBoard", no);
	}
	public List<BoardVo> searchBoard(String text,String option){
		if(option.equals("username")) {
			return sqlSession.selectList("board.searchUsernameBoard", text);
		}
		else {
		    return sqlSession.selectList("board.searchTitleBoard", text);
		}
	}
	


}
