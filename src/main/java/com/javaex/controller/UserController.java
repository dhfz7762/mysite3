package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.JsonResult;
import com.javaex.vo.UserVo;

@Controller
public class UserController {
    
	@Autowired
	private UserService userService;
	
	//회원가입폼
	@RequestMapping(value="/user/joinForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String joinForm() {
		
		System.out.println("UserController.joinForm()");
		return "/user/joinForm";
	}
	
	
	//회원가입
	@RequestMapping(value="/user/join",method= {RequestMethod.GET,RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		
		System.out.println("UserController.join()");
		
		int count = userService.join(userVo);
		if(count>0) {
			return "/user/joinOk"; //회원가입 성공시 리턴
		}else {
			return "redirect:/user/joinForm"; //실패시 리턴
		}
	}
    
	
	//로그인폼
	@RequestMapping(value="/user/loginForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController.loginForm()");
		return "/user/loginForm";
	}
	
	
	//로그인
	@RequestMapping(value="/user/login", method = {RequestMethod.GET,RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController.login");
		UserVo authUser = userService.login(userVo);
		System.out.println(authUser);
		
		if(authUser != null) {//로그인 성공
			System.out.println("로그인 성공");
			//세션에 저장
			session.setAttribute("authUser", authUser); //세션 인증
			//메인으로 리다이렉트
			return "redirect:/main";
		}else {//로그인 실패
			System.out.println("로그인 실패");
			//로그인폼으로
			
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	

	
	//회원정보수정
	@RequestMapping(value="/user/modify", method= {RequestMethod.GET,RequestMethod.POST})
	public String modify(HttpSession session,@ModelAttribute UserVo userVo) {
		System.out.println("UserController.modify()");
		
        userService.modify(userVo);
        //userVo no가 없음 <--세션에서 가져올 예정
        UserVo authUser =  (UserVo)session.getAttribute("authUser");
        authUser.setName(userVo.getName());
        int no = authUser.getNo();
       
		return "redirect:/main";
	}
	
	
	//회원정보폼
	@RequestMapping(value="/user/modifyForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("UserController.modifyForm()");
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int no = authUser.getNo();
		
		UserVo userVo = userService.modifyForm(no);
		model.addAttribute(userVo);
		
		return "/user/modifyForm";
	}
	
	//로그아웃
	@RequestMapping(value="/user/logout",method= {RequestMethod.GET,RequestMethod.POST})
	public String logout(HttpSession session) {
		
		System.out.println("UserController.logout()");
		
		session.removeAttribute("authUser"); //session 메모리 삭제
		session.invalidate(); 
		
		return "redirect:/main";
	}
	
	//아이디 중복체크
	@ResponseBody
	@RequestMapping(value="/user/idcheck",method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResult idcheck(@RequestParam("id") String id) {
		System.out.println("UserController.idcheck()");
		
		boolean data = userService.idcheck(id);
		
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(data);
		
		/* 강제적으로 성공 데이타 입력
		jsonResult.setResult("success");
		jsonResult.setData(data);
		
		강제적으로 실패 데이타 입력
		jsonResult.setResult("fail");
		jsonResult.setFailMsg("통신오류");
		*/
		
		System.out.println(jsonResult);
		
		return jsonResult;
	}
}