package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	//게시판 리스트 페이징 포함
	public Map<String,Object> getList3(int crtPage,String keyword){
		System.out.println("getList3()");
		System.out.println(crtPage);
		
		crtPage = (crtPage >=1) ? crtPage : (crtPage = 1);
		
		//페이지당 글 개수
		int listCnt = 10;
		
		//시작글 번호
		int startRnum = (crtPage-1) * listCnt +1;
		
		//끝글 번호
		int endRnum = (startRnum+listCnt)-1;
		List<BoardVo> boardList = boardDao.selectList3(startRnum,endRnum,keyword);
		//페이징 계산
		//총 글 개수
		int totalCount = boardDao.totalCount(keyword);
		//페이지당 버튼 개수
		int pageBtnCount = 5;
		//마지막 버튼 번호
		int endPageBtnNo = (int)(Math.ceil(crtPage / (double)pageBtnCount)) * pageBtnCount;
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - pageBtnCount + 1;
		//다음 화살표 true false
		boolean next = false;
		
		if(endPageBtnNo * listCnt < totalCount ) {
			next = true;
		}
		else {
			next = false;
			//끝 버튼 번호 endPageBtnNo 다시 계싼
			endPageBtnNo =(int)Math.ceil(totalCount/(double)listCnt);
		}
		
		//이전 화살표
		boolean prev = false ;
		if(startPageBtnNo !=1) {
			prev = true;
		}
		else {
			prev = false;
		}
		
		//맵으로 만들기
		Map<String,Object> pMap =new HashMap<String,Object>();
		pMap.put("prev",prev);
		pMap.put("next",next);
		pMap.put("startPageBtnNo",startPageBtnNo);
		pMap.put("endPageBtnNo",endPageBtnNo);
		pMap.put("boardList",boardList);
		pMap.put("keyword", keyword);
		return pMap;
	}
	//게시판 수정
	public void modify(BoardVo boardVo) {
		System.out.println("BoardService.modify()");
		
		boardDao.updateBoard(boardVo);
	}
	
	//게시판 수정폼
	public BoardVo modifyForm(int no) {
		System.out.println("BoardService.modifyForm()");
		
		BoardVo boardVo = boardDao.selectBoard(no);
		return boardVo;
	}
	
	
	//게시판 글삭제
	public int delete(BoardVo boardVo){
		System.out.println("BoardService.delete()");
		
		int count = boardDao.deleteBoard(boardVo);
		return count;
	}
	
	
	//게시판 글쓰기
	public int write(BoardVo boardVo){
		System.out.println("BoardService.write()");
		
		int count = boardDao.insertBoard(boardVo);
		return count;
	}

	
	//게시판 전체리스트와 검색
	public List<BoardVo> getList(String keyword){
		System.out.println("BoardService.getBoardList()");
		
		List<BoardVo> boardList = boardDao.selectList(keyword);
		return boardList;
	}
}
