package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	//회원가입 폼
	@RequestMapping(value="/user/joinForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String joinForm() {
		return "user/joinForm";
	}
	//회원가입
	@RequestMapping(value="/user/join",method= {RequestMethod.GET,RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		int count = userService.insertUser(userVo);
		if(count>0) {
			return "user/joinOk";
		}
		else {
			return "redirect:/user/joinForm";
		}
	}
	//로그인폼
	@RequestMapping(value="/user/loginForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		return "user/loginForm";
	}
	//로그인
	@RequestMapping(value="/user/login",method= {RequestMethod.GET,RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo,HttpSession session) {
		UserVo authUser = userService.loginUser(userVo);
		if(authUser != null) {//로그인 성공
			//세션에 저장
			System.out.println("로그인성공");
			session.setAttribute("authUser", authUser);
			//메인으로 리다이렉트
			return "redirect:/main";
			
		}
		else { //로그인 실패
			System.out.println("실패");
			
			return "redirect:/user/loginForm?result=fail";
		}

		
	}
	//로그아웃
	@RequestMapping(value="/user/logout",method = {RequestMethod.GET,RequestMethod.POST})
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/main";
	}
	//수정폼
	@RequestMapping(value="/user/modifyForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm() {
		return "user/modifyForm";
	}
	//수정
	@RequestMapping(value="/user/modify",method= {RequestMethod.GET,RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo,HttpSession session) {
		int count = userService.modifyUser(userVo);
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		authUser.setName(userVo.getName());
		if(count>0) {
			return "redirect:/main";
		}
		else {
			return "redirect:/user/modifyForm";
		}
		
	}
	@ResponseBody
	@RequestMapping(value="/user/idcheck",method= {RequestMethod.GET,RequestMethod.POST})
	public UserVo idcheck(@RequestParam("id")String id) {
		UserVo userVo = userService.idcheck(id);
		
		return userVo;
	}
	
}
