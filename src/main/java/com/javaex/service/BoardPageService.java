package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardPageDao;
import com.javaex.vo.BoardPageVo;
import com.javaex.vo.BoardVo;

@Service
public class BoardPageService {
	
	@Autowired
	private BoardPageDao boardPageDao;
	
	public int countBoard() {
		return boardPageDao.countBoard();
				
	}
	public List<BoardVo> selectBoard(BoardPageVo vo) {
		return boardPageDao.selectBoard(vo);
	}
	public List<BoardVo> searchBoard(BoardPageVo vo,String option){
		return boardPageDao.searchBoard(vo,option);
	}

}
