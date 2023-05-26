package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	
	//등록+리스트 페이지
	@RequestMapping(value="/guestbook/addList",method= {RequestMethod.GET,RequestMethod.POST})
	public String addList(Model model) {
		List<GuestbookVo> guestbookList = guestbookService.getAddList();
		model.addAttribute("guestbookList",guestbookList);
		return "guestbook/addList";
	}
	@RequestMapping(value="/guestbook/add",method= {RequestMethod.GET,RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		int count = guestbookService.addGuestBook(guestbookVo);
		
		return "redirect:/guestbook/addList";
	}
	@RequestMapping(value="/guestbook/deleteForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String deleteForm(@ModelAttribute GuestbookVo guestbookVo,Model model) {
		model.addAttribute("guestbookVo",guestbookVo);
		return "guestbook/deleteForm";
	}
	@RequestMapping(value="/guestbook/delete",method= {RequestMethod.GET,RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		guestbookService.deleteGuestbook(guestbookVo);
		return "redirect:/guestbook/addList";
	}
}