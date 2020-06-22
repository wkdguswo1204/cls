package com.increpas.cls.controller.board;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.*;

import com.increpas.cls.dao.*;
import com.increpas.cls.service.*;
import com.increpas.cls.util.*;
import com.increpas.cls.vo.*;

/**
 * 이 클래스는 게시판에 관련된 요청이 올 경우 처리할 클래스
 * @author	전은석
 * @since	2020/06/19
 * @version	V.1.0
 *
 */
@Controller
@RequestMapping("/board")
public class Board {
	@Autowired
	BoardDAO bDAO;
	@Autowired
	BoardService bSrvc;
	
	// 게시판 뷰 보기 요청
	@RequestMapping("/boardList.cls")
	public ModelAndView boardList(HttpServletRequest req, ModelAndView mv, PageUtil page) {
		/*
		// 할일
		// 먼저 로그인 체크부터 한다.
		String sid = (String)session.getAttribute("SID");
		if(sid == null || sid.length() == 0) {
			RedirectView rv = new RedirectView("/cls/member/login.cls");
			mv.setView(rv);
		} else {
			if(page.getNowPage() == 0) {
				page.setNowPage(1);
			}
			
			int totalCount = bDAO.getCnt();
			page.setPage(totalCount);
			ArrayList<BoardVO> list = (ArrayList<BoardVO>)bDAO.getList(page);
			
			mv.addObject("LIST", list);
			mv.addObject("PAGE", page);
			mv.setViewName("board/boardList");
		}
		*/
		bSrvc.getBrdList(req, mv, page);
		return mv;
	}
	
	@RequestMapping("/boardWrite.cls")
	public ModelAndView writeForm(HttpServletRequest req, ModelAndView mv, PageUtil page) {
		bSrvc.writeSrvc(req, mv, page);
		return mv;
	}
}
