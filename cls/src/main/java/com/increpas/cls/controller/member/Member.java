package com.increpas.cls.controller.member;

import java.text.DateFormat;
import java.util.*;

import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.*;

import com.increpas.cls.dao.*;
import com.increpas.cls.home.HomeController;
import com.increpas.cls.service.*;
import com.increpas.cls.vo.*;

@Controller

@RequestMapping("/member")
public class Member {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	MemberDAO mDAO;
	@Autowired
	ProfileService profileSrvc;
	@Autowired
	MemberService mSrvc;
	
	@RequestMapping("/logout.cls")
	public ModelAndView logout(HttpSession session, ModelAndView mv) {
		/*
		 * 이 함수는 로그아웃처리를 할 함수
		 * 로그아웃처리가 정상적으로 되면 로그인페이지로 강제 이동하고
		 * 실패하면 메인페이지로 다시 이동시키자.
		 */
		
		String view = "/cls/member/login.cls";
		
		RedirectView rv = null;
		session.removeAttribute("SID");
		if(session.getAttribute("SID") != null) {
			view = "/cls/main";
		}
		
		rv = new RedirectView(view);
		mv.setView(rv);
		return mv;
	}
	
	@RequestMapping("/login.cls")
	public ModelAndView loginForm(HttpServletRequest req, ModelAndView mv) {
		System.out.println("############# controller");
		/*
		String sid = (String) session.getAttribute("SID");
		String view = "member/login";
		if(sid != null) {
			System.out.println("Session SID : " + session.getAttribute("SID"));
			RedirectView rv = new RedirectView("/cls/main");
			mv.setView(rv);
		} else {
			mv.setViewName(view);
		}
		*/
		mSrvc.loginCk(req, mv);
		return mv;
	}
	
	@RequestMapping("/join.cls")
	public ModelAndView joinForm(HttpServletRequest req, ModelAndView mv) {
		/*
		String sid = (String) session.getAttribute("SID");
		String view = "member/join";
		if(sid != null) {
			RedirectView rv = new RedirectView("main");
			mv.setView(rv);
		} else {
			mv.setViewName(view);
		}
		*/
		mSrvc.loginCk(req, mv);
		
		return mv;
	}
	
	@RequestMapping("/joinProc.cls")
	public ModelAndView joinProc(MemberVO mVO, ProfileVO fVO, ModelAndView mv, HttpSession session) {
		// 할일
		// 전처리기로 mVO의 데이터가 유효하다는 전제하에 코딩해보자.
		// 1. mDAO 함수 호출하고
		int cnt = mDAO.addMember(mVO);
		System.out.println(mVO.getMno());
		fVO.setMno(mVO.getMno());
		RedirectView rv = null;
		if(cnt == 1) {
			// 프로파일의 정보를 데이터베이스에 저장한다.
			int count = profileSrvc.addProfile(fVO, session);
			if(count != 1) {
				// 이 경우는 파일 정보 입력에 실패한 파일이 있는 이경우이므로..
				// 이 후 적적히 처리해 주기로 하자.
			}
			rv = new RedirectView("/cls/member/login.cls");
		} else {
			rv = new RedirectView("/cls/member/join.cls");
		}
		
		mv.setView(rv);
		return mv;
	}
	
	@RequestMapping("/idCk.cls")
	@ResponseBody
	public Map idCheck(String id) {
		HashMap map = new HashMap();
		map.put("cnt", mDAO.idCheck(id)); // {"cnt": 0}
		return map;
	}
	
	@RequestMapping(path={"/Login.cls", "/Join.cls"})
	public ModelAndView doolcuri(HttpServletRequest req, ModelAndView mv) {
		String view = "main";
		mv.setViewName(view);
		return mv;
	}
	
	@RequestMapping(value="/loginProc.cls", method=RequestMethod.POST, params= {"!id", "!pw"})
	public ModelAndView loginProcRedirect(String id, String pw, ModelAndView mv) {
		System.out.println("### redirect ###");
		System.out.println("### id : " + id);
		System.out.println("### pw : " + pw);
		// 임시로 당분간은 로그인페이지로 리다이렉트시키기로 하자.
		RedirectView rv = new RedirectView("/cls/member/login.cls");
		
		mv.setView(rv);
		
		return mv;
	}
	
	@RequestMapping(value="/loginProc.cls", method=RequestMethod.POST, params= {"id", "pw"})
	public ModelAndView loginProc(MemberVO mVO, ModelAndView mv, HttpSession session, Locale locale) {
//	public ModelAndView loginProc(String id, String pw, ModelAndView mv, HttpSession session) {
		/*
		System.out.println("### id : " + id);
		System.out.println("### pw : " + pw);
		*/
		
		// 데이터 받고 전달할 데이터 만들고
		/*
		MemberVO mVO = new MemberVO();
		mVO.setId(id);
		mVO.setPw(pw);
		 */
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		int cnt = mDAO.login(mVO);
		
		RedirectView rv = null;
		if(	cnt == 1 ) {
			session.setAttribute("SID", mVO.getId());
			logger.info("Login ID : {} - {}", mVO.getId(), formattedDate);
			rv = new RedirectView("/cls/main");
		} else {
			// 아이디와 비밀번호에 맞는 회원이 없는 경우이므로 다시 로그인페이지로 이동시킨다.
			rv = new RedirectView("/cls/member/login.cls");
		}
		
		mv.setView(rv);
		
		return mv;
	}
	
	// 회원 버튼 리스트 페이지 보이기 요청 처리 함수
	@RequestMapping("/memberList.cls")
	public ModelAndView getList(ModelAndView mv) {
		String view = "member/memberList";
		// 할일
		// 1. 받을 데이터는 없으므로 보낼 데이터만 만들면 된다.
		ArrayList<String> color = getColorList();
		
		// 1-2. 회원 버튼에 필요한 리스트 가져오기
		ArrayList<MemberVO> list = (ArrayList<MemberVO>) mDAO.getList();
		
		// 2. 데이터가 준비 되어었으면 데이터를 넘겨주고
		mv.addObject("COLOR", color); // jsp 프로젝트의 req.setAttribute() 와 같은 기능
		mv.addObject("LIST", list);
		// 3. 뷰도 넘겨주고
		mv.setViewName(view);
		return mv;
	}
	
	// 회원 상세정보 요청 처리 함수
	@RequestMapping("/memberDetail.cls")
	public ModelAndView getDetail(HttpSession session, ModelAndView mv) {
		// 먼저 로그인 되어있는지 확인하고
		if(session.getAttribute("SID") == null) {
			RedirectView rd = new RedirectView("/cls/member/login.cls");
			mv.setView(rd);
			return mv;
		}
		
		// 할일
		// 데이터는 이미 매개변수에서 받아왔으므로
		// 할일은 데이터베이스 처리만 해주고
		// 데이터 받아서 뷰에 넘겨기만 하면 되겠다.
//		MemberVO mVO = mDAO.getDetail(mno);
		
		// 데이터 심고
//		mv.addObject("DATA", mVO);
		mv.addObject("DATA", mDAO.getDetail((String) session.getAttribute("SID")));
		// 뷰 심고
		mv.setViewName("member/memberDetail");
		return mv;
	}
	
	// 회원 상세정보 요청 처리 함수
	@RequestMapping("/memberEdit.cls")
	public ModelAndView getEdit(HttpSession session, ModelAndView mv, ProfileVO fVO) {
		// 먼저 로그인 되어있는지 확인하고
		if(session.getAttribute("SID") == null) {
			RedirectView rd = new RedirectView("/cls/member/login.cls");
			mv.setView(rd);
			return mv;
		}
		
		// 할일
		// 데이터는 이미 매개변수에서 받아왔으므로
		// 할일은 데이터베이스 처리만 해주고
		// 데이터 받아서 뷰에 넘겨기만 하면 되겠다.
		MemberVO mVO = mDAO.getDetail((String)session.getAttribute("SID"));
		fVO.setMno(mVO.getMno());
		ArrayList<ProfileVO> list = (ArrayList<ProfileVO>)mDAO.getProfile(fVO);
		mVO.setPic(list);
		// 데이터 심고
//		mv.addObject("DATA", mVO);
		mv.addObject("DATA", mVO);
		// 뷰 심고
		mv.setViewName("member/memberEdit");
		return mv;
	}
	
	@RequestMapping("/memberEditProc.cls")
	public ModelAndView editProc(ModelAndView mv, HttpSession session, MemberVO mVO, ProfileVO fVO) {
		// 할일
		// 데이터 체크하고
//		int cnt = 0 ;
		/*
		System.out.println("*****************************");
		System.out.println(mVO);
		if(mVO.getPw() != null || mVO.getAno() != 0) {
			mSrvc.editMember(mVO);
		}
		*/
		fVO.setFile(Arrays.copyOf(fVO.getFile(), fVO.getFile().length - 1));
		//profileSrvc.addProfile(fVO, session);
		
		mSrvc.editInfo(mVO, fVO);
		RedirectView rv = new RedirectView("/cls/member/memberDetail.cls");
		mv.setView(rv);
		
		return mv;
	}
	
	// 회원 상세정보 요청 처리 함수
	@RequestMapping("/mDetail.cls")
	@ResponseBody
	public MemberVO mDetail(HttpSession session, MemberVO mVO) {
		// 먼저 로그인 되어있는지 확인하고
		if(session.getAttribute("SID") == null) {
			mVO.setStatus("No");
		}
		mVO = mDAO.getDetail(mVO.getMno());
		mVO.setSdate();
		return mVO;
	}
	
	
	// w3.css 칼라 클래스 리스트 반환함수
	public ArrayList<String> getColorList(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("w3-red");
		list.add("w3-pink");
		list.add("w3-purple");
		list.add("w3-deep-purple");
		list.add("w3-indigo");
		list.add("w3-blue");
		list.add("w3-cyan");
		list.add("w3-aqua");
		list.add("w3-teal");
		list.add("w3-green");
		list.add("w3-light-green");
		list.add("w3-lime");
		list.add("w3-khaki");
		list.add("w3-yellow");
		list.add("w3-amber");
		list.add("w3-orange");
		list.add("w3-deep-orange");
		
		return list;
	}
	
}
