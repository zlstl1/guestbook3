package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVO;

@Controller
public class GuestbookController {

	@Autowired
	GuestbookDao dao;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		
		List<GuestbookVO> list = dao.getList();
		model.addAttribute("list",list);
		return "/WEB-INF/views/list.jsp";
	}
	
	@RequestMapping(value="/deleteform",method=RequestMethod.GET)
	public String deleteform(@RequestParam("no") int no,Model model) {	
		model.addAttribute("no",no);
		return "/WEB-INF/views/deleteform.jsp";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(@RequestParam("no") int no , @RequestParam("password") String password) {	
	
		dao.delete(no,password);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@ModelAttribute GuestbookVO guestbookVo) {		
		dao.add(guestbookVo);
		return "redirect:/list";
	}
	
}
