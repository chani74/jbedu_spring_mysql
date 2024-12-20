package com.jbedu.mysql.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jbedu.mysql.command.BCommand;
import com.jbedu.mysql.command.BDeleteCommand;
import com.jbedu.mysql.command.BListCommand;
import com.jbedu.mysql.command.BWriteCommand;
import com.jbedu.mysql.dao.BoardDao;
import com.jbedu.mysql.dto.BoardDto;

@Controller
public class BoardController {
	
	BCommand command = null;
	
	@RequestMapping(value="/")
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/write_form")
	public String write_form() {
		return "write_form";
	}
	
	@RequestMapping(value="/writeOk")
	public String writeOk(HttpServletRequest request, Model model) {
		
		model.addAttribute("request",request);
		
		command = new BWriteCommand();
		command.execute(model);
				
		return "redirect:boardList";
	}
	
	@RequestMapping(value="/boardList")
	public String boardList(HttpServletRequest request, Model model) {
		command = new BListCommand();
		command.execute(model);
		
		return "boardList";
	}
	

	@RequestMapping(value="/delete_form")
	public String delete_form() {
		return "delete_form";
	}
	
	@RequestMapping(value="/deleteOk")
	public String deleteOk(HttpServletRequest request, Model model) {
		
		
		model.addAttribute("request",request);
		command = new BDeleteCommand();
		int result = command.execute(model);
		
		if (result== 1) {
			return "redirect:boardList";
		} else {
			model.addAttribute("msg", "이미 삭제된 글입니다. 다시 확인 바랍니다.");
			model.addAttribute("returnPage", "boardList");
			return "alert";
		}
	}

	@RequestMapping(value="/boardContent")
	public String boardContent(HttpServletRequest request, Model model) {
		
		String bnum = request.getParameter("bnum");
		
		BoardDao boardDao = new BoardDao();
		BoardDto boardDto =  boardDao.boardContent(bnum);
		
		model.addAttribute("bDto", boardDto);
		
		return "boardContent";
	}

	@RequestMapping(value="/alert")
	public String alert() {
		return "alert";
	}
}
