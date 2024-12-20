package com.jbedu.mysql.command;

import java.util.List;

import org.springframework.ui.Model;

import com.jbedu.mysql.dao.BoardDao;
import com.jbedu.mysql.dto.BoardDto;

public class BListCommand implements BCommand {

	@Override
	public int execute(Model model) {
		BoardDao boardDao = new BoardDao();
		List<BoardDto> bDtos = boardDao.boardList();	 // 모든 글 목록
		
		model.addAttribute("bDtos", bDtos);
		
		return 0;
	}

}
