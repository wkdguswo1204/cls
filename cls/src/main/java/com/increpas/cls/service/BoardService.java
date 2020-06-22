package com.increpas.cls.service;

import java.util.ArrayList;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls.dao.*;
import com.increpas.cls.util.*;
import com.increpas.cls.vo.BoardVO;

@Service
public class BoardService {
	@Autowired
	BoardDAO bDAO;
	
	// 게시판 리스트 요청처리 서비스 함수
	public void getBrdList(HttpServletRequest req, ModelAndView mv, PageUtil page) {
		if((Boolean) mv.getModel().get("isLogin")) {
			if(page.getNowPage() == 0) {
				page.setNowPage(1);
			}
			
			int totalCount = bDAO.getCnt();
			page.setPage(totalCount);
			ArrayList<BoardVO> list = (ArrayList<BoardVO>)bDAO.getList(page);
			
			mv.addObject("LIST", list);
			mv.addObject("PAGE", page);
			mv.setViewName("board/boardList");
		} else {
			mv.setView(new RedirectView("/cls/member/login.cls"));
		}
	}
	
	// 게시판 글 작성 폼보기 요청 처리 서비스 함수
	public void writeSrvc(HttpServletRequest req, ModelAndView mv, PageUtil page) {
		if((Boolean) mv.getModel().get("isLogin")) {
			if(page.getNowPage() == 0) {
				page.setNowPage(1);
			}
			
			mv.addObject("PAGE", page);
			mv.setViewName("board/boardWrite");
		} else {
			mv.setView(new RedirectView("/cls/member/login.cls"));
		}
		
	}
}
