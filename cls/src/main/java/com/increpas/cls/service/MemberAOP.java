package com.increpas.cls.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Component
@Aspect
public class MemberAOP {
	
	/*
		와일드카드
		
			"execution(접근지정자  반환값타입	경로.함수)"
			==> 경로.함수 중 선언된 접근지정자와 일치하고 반환값타입이 같은 함수가 실행될때 를 의미한다.
				.. 	0개 이상
				*	1개 이상
	 */
	/*
			@Pointcut 은 언제 실행할지를 정하는 선언적 의미의 함수
	 */
	
	/*
	@Pointcut("execution(* com.increpas.cls.controller.member.Member.*Form(..))")
	public void loginCk() {
		System.out.println("#### poincut exec");
	}
	*/
	
//	@Before("loginCk()")
//	@Before("execution(* com.increpas.cls.controller.member.Member.*Form(..))")
	@Before("execution(* com.increpas.cls.service.MemberService.loginCk(..)) || " +
			"execution(* com.increpas.cls.service.BoardService.getBrdList(..))"
			)
	// @Before 는 매개변수에 선언된 내용이 실행되기 이전 실행한다는 의미 반대의 개념은 @After 가있고 
	// 둘을 모두 포함해서 처리하는 @Arround 도 있다.
	public void membAutho(JoinPoint join) {
		System.out.println("*********** membAutho()");
		/*
			JoinPoint	- 
				포인트컷에 선언된 함수의 매개변수를 관리할 클래스
				JoinPoint 객체에 실행함수의 매개변수를 꺼내는 방법
				
				Object[] obj = 변수.getArgs();
				
				따라서 꺼낼때는 인덱스로 꺼내야 하고
				강제형변환도 해줘야 한다.
		 */
		// 매개변수 꺼내기
		Object[] obj = join.getArgs();
		HttpServletRequest req = (HttpServletRequest) obj[0];
		String tmp = req.getRequestURI();
		tmp = tmp.substring(tmp.lastIndexOf("/") + 1, tmp.lastIndexOf("."));
		// /cls/member/login.cls ==> login
		String view = (tmp.equals("join"))? "member/join" : "member/login";
		
		HttpSession session = req.getSession();
		String sid = (String)session.getAttribute("SID");
		if(sid == null) {
			// 이 경우는 로그인이 안된 상태
			((ModelAndView) obj[1]).setViewName(view);
			((ModelAndView) obj[1]).addObject("isLogin", false);
		} else {
			// 이미 로그인 된 상태
			((ModelAndView) obj[1]).setView(new RedirectView("/cls/main"));
			((ModelAndView) obj[1]).addObject("isLogin", true);
		}
	}
	
	@Before("execution(* com.increpas.cls.service.BoardService.writeSrvc(..))")
	public void loginCk1(JoinPoint join) {
		Object[] obj = join.getArgs();
		HttpServletRequest req = (HttpServletRequest) obj[0];
		// /cls/member/login.cls ==> login
		String view = "board/boardWrite";
		
		HttpSession session = req.getSession();
		String sid = (String)session.getAttribute("SID");
		if(sid == null) {
			((ModelAndView) obj[1]).addObject("isLogin", false);
		} else {
			((ModelAndView) obj[1]).addObject("isLogin", true);
		}
		
	}
}
