package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.BoardPageDao;
import com.javaex.service.BoardPageService;
import com.javaex.service.BoardService;
import com.javaex.vo.BoardPageVo;
import com.javaex.vo.BoardVo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardPageService boardPageService;

	
	@RequestMapping(value="/board/list",method= {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model,BoardPageVo vo,@RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		//List<BoardVo> boardList = boardService.getBoardList();
		//model.addAttribute("boardList", boardList);
		int total = boardPageService.countBoard();
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "5";
		}
		//boardPageService.selectBoard(vo)
		vo = new BoardPageVo(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
		model.addAttribute("paging", vo);
		model.addAttribute("boardList", boardPageService.selectBoard(vo));
		
		return "board/list";
	}
	@RequestMapping(value="/board/writeForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String writeForm() {
		return "board/writeForm";
	}
	@RequestMapping(value="/board/write",method= {RequestMethod.GET,RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo) {
		int count = boardService.writeBoard(boardVo);
		if(count>0) {
			return "redirect:/board/list";
		}
		else {
			return "board/writeForm";
		}
	}
	@RequestMapping(value="/board/read",method= {RequestMethod.GET,RequestMethod.POST})
	public String read(@RequestParam("no")int no,Model model) {
		boardService.hitBoard(no);
		BoardVo readBoard = boardService.readBoard(no);
		model.addAttribute("readBoard", readBoard);
		return "board/read";	
	}
	@RequestMapping(value="/board/modifyForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(@RequestParam("no")int no,Model model) {
		BoardVo readBoard = boardService.readBoard(no);
	    model.addAttribute("readBoard", readBoard);	
		return "board/modifyForm";
	}
	@RequestMapping(value="/board/modify",method= {RequestMethod.GET,RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		boardService.modifyBoard(boardVo);
		return "redirect:/board/list";
	}
	@RequestMapping(value="/board/delete",method= {RequestMethod.GET,RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		boardService.deleteBoard(no);
		return "redirect:/board/list";	
	}
	@RequestMapping(value="/board/search",method= {RequestMethod.GET,RequestMethod.POST})
	public String search(@RequestParam("searchText")String text,@RequestParam("selectOption") String option,Model model,
			BoardPageVo vo,@RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		int total = boardPageService.countBoard();
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "5";
		}
		vo = new BoardPageVo(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage),text);
		
		model.addAttribute("paging", vo);
		model.addAttribute("boardList", boardPageService.searchBoard(vo,option));
        return "board/list";
	}
}
