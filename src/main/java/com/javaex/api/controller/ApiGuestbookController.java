package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;
import com.javaex.vo.JsonResult;

@Controller
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	
	//방명록 첫페이지(ajax로 리스트 출력)
	@RequestMapping(value = "/api/guestbook/addList2", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList2() {
		System.out.println("ApiGuestbookController.addList2()");
		return "/guestbook/ajaxList2";
	}
	
	//ajax 전체리스트 가져오기
	@ResponseBody
	@RequestMapping(value = "/api/guestbook/list", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult list() {
		List<GuestbookVo> guestList = guestbookService.getGuestList();
		System.out.println(guestList);
		
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(guestList);
		
		return jsonResult;
	}
	
	//json으로 데이터 전송 후 등록
	@ResponseBody
	@RequestMapping(value ="/api/guestbook/add2",method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResult addList2(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.add()");
		System.out.println(guestbookVo);
		GuestbookVo guestVo = guestbookService.addGuestList(guestbookVo);
		
		//jsonResult에 담기
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(guestVo);
 
		return jsonResult;
	}

	//ajax 전체리스트
	@RequestMapping(value = "/api/guestbook/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model) {
		System.out.println("ApiGuestbookController.addList()");
		
		List<GuestbookVo> guestList = guestbookService.getGuestList();
		System.out.println(guestList);
		
        model.addAttribute("guestList", guestList);
        
		return "/guestbook/ajaxList";
	}
	
	
	//ajax 방명록 등록(등록을 하고나서 no번호를 받아 글정보를 가져옴-서비스에서 두가지 일)
	@ResponseBody
	@RequestMapping(value ="/api/guestbook/add",method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResult addList(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.add()");
		GuestbookVo guestVo = guestbookService.addGuestList(guestbookVo);
		
		//jsonResult에 담기
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(guestVo);
 
		return jsonResult;
	}
	@ResponseBody
	@RequestMapping(value="/api/guestbook/remove", method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResult remove(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("삭제~!");
		int count = guestbookService.deleteGuest(guestbookVo);
		
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(count);
		return jsonResult;
	}
}
