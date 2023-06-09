package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.JsonResult;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping(value="/list",method= {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		List<GalleryVo> GalleryList = galleryService.getList();
		model.addAttribute("GalleryList", GalleryList);
		System.out.println("GalleryController.list()");
		return "gallery/list";
	}
	@RequestMapping(value="/upload",method= {RequestMethod.GET,RequestMethod.POST})
	public String upload(@RequestParam("file")MultipartFile file,@RequestParam("text") String text,@RequestParam("user_no")int user_no) {
		GalleryVo vo = galleryService.restore(file,text,user_no);
		int count = galleryService.insertGallery(vo);
		if(count>0) {
			System.out.println("성공~");
		}
		return "redirect:/gallery/list";
	}
	@ResponseBody
	@RequestMapping(value="/remove",method= {RequestMethod.GET,RequestMethod.POST})
	public JsonResult remove(@RequestParam("no")int no) {
		GalleryVo galleryVo = new GalleryVo(no);
		int count = galleryService.remove(galleryVo);
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(count);
		return jsonResult;
	}

}
