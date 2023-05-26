package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	public int writeBoard(BoardVo boardVo) {
		return boardDao.writeBoard(boardVo);
	}
	public List<BoardVo> getBoardList(){
		return boardDao.getBoardList();
	}
	public BoardVo readBoard(int no) {
		return boardDao.readBoard(no);
	}
	public int modifyBoard(BoardVo boardVo) {
		return boardDao.modifyBoard(boardVo);
	}
	public int deleteBoard(int no) {
		return boardDao.deleteBoard(no);
	}
	public int hitBoard(int no) {
		return boardDao.hitBoard(no);
	}
	

}
