package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//게시판 전체글 개수
	public int totalCount(String keyword) {
		return sqlSession.selectOne("board.totalCount",keyword);
	}
	
	//게시판 리스트 페이징 포함
	public List<BoardVo> selectList3(int startRnum,int endRnum,String keyword){
		System.out.println(startRnum+" : "+endRnum);
		
		Map<String, Object> bmap = new HashMap<String, Object>();
		bmap.put("startRnum", startRnum);
		bmap.put("endRnum", endRnum);
		bmap.put("keyword", keyword);
		List<BoardVo> bo = sqlSession.selectList("board.selectList3", bmap);
		 return bo;
	}
	
	//게시판 글수정
	public int updateBoard(BoardVo boardVo) {
		System.out.println("BoardDao.updateBoard()");
		
		int count = sqlSession.update("board.updateBoard", boardVo);
		return count;
	}
	
	
	//게시판 글수정폼(하나의 게시판글 가져오기)
	public BoardVo selectBoard(int no) {
		System.out.println("BoardDao.selectBoard()");
		
		BoardVo boardVo = sqlSession.selectOne("board.selectOne", no);
		return boardVo;
	}
	
	
	//게시판 글삭제
	public int deleteBoard(BoardVo boardVo){
		System.out.println("BoardDao.delete()");
		
		int count = sqlSession.delete("board.delete",boardVo);
		return count;
	}
	
	
	//게시판 글쓰기
	public int insertBoard(BoardVo boardVo) {
		System.out.println("BoardDao.insertBoard()");
		
		int count = sqlSession.insert("board.insertBoard",boardVo);
        return count;
	}
	
	
	//게시판 전체리스트와 검색
	public List<BoardVo> selectList(String keyword){
		System.out.println("BoardDao.selectList()");
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList",keyword);
		return boardList;
	}

}