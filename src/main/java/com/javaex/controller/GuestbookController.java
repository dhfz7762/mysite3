package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	//방명록삭제
	@RequestMapping(value="/guestbook/delete", method= {RequestMethod.GET,RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.delete()");
		guestbookService.deleteGuest(guestbookVo);
		return "redirect:/guestbook/addList";
	}
	
	//방명록삭제폼
	@RequestMapping(value="/guestbook/deleteForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteForm() {
		System.out.println("GuestbookController.deleteForm()");
		return "/guestbook/deleteForm";
	}
	
	
	//방명록리스트
	@RequestMapping(value="/guestbook/addList",method = {RequestMethod.GET,RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("GuestbookController.addList()");
		List<GuestbookVo> guestList = guestbookService.getGuestList();
		model.addAttribute("guestList",guestList);
		
		return "/guestbook/addList";
	}
    
	//방명록등록
	@RequestMapping(value="/guestbook/add", method = {RequestMethod.GET,RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		
		System.out.println("GuestbookController.add()");
		guestbookService.addGuest(guestbookVo);
		
		return "redirect:/guestbook/addList";
	}
	//ajax 삭제
	
	

	
}