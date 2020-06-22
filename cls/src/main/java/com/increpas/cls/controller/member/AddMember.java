package com.increpas.cls.controller.member;

import java.io.UnsupportedEncodingException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls.controller.member.*;
import com.increpas.cls.dao.*;
import com.increpas.cls.vo.*;

@Controller
public class AddMember {
	@Autowired
	MemberDAO mDAO;
	
	public int setAvt(){
		int cnt = 0 ;
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(int i = 1; i < 7; i++ ) {
			map.put("ano", 10+i);
			map.put("oriname", "img_avatar1" + i + ".png");
			map.put("savename", "img_avatar1" + i + ".png");
			if(i < 4) {
				map.put("gen", "M");
			} else {
				map.put("gen", "F");
			}
			cnt = mDAO.addAvt(map);
		}
		
		return cnt;
	}
	
	public void setData(){
		ArrayList<String> nameList = new ArrayList<String>();
		nameList.add("전은석");
		nameList.add("최두용");
		nameList.add("유태희");
		nameList.add("박광호");
		nameList.add("박기윤");
		nameList.add("김수진");
		ArrayList<String> idList = new ArrayList<String>();
		idList.add("euns");
		idList.add("dDragon");
		idList.add("yth");
		idList.add("pgh");
		idList.add("pky");
		idList.add("chairman");
		ArrayList<String> mailList = new ArrayList<String>();
		mailList.add("euns@increpas.com");
		mailList.add("dDragon@increpas.com");
		mailList.add("yth@increpas.com");
		mailList.add("pgh@increpas.com");
		mailList.add("pky@increpas.com");
		mailList.add("chairman@increpas.com");
		ArrayList<String> telList = new ArrayList<String>();
		telList.add("010-3175-9042");
		telList.add("010-9292-4184");
		telList.add("010-2663-0235");
		telList.add("010-3372-0057");
		telList.add("010-9009-6763");
		telList.add("010-1111-1111");
		String spw = "12345";
		String mGen = "M";
		String fGen = "F";
		int mAno = 12;
		int fAno = 15;
		
		for(int i = 0 ; i < idList.size(); i++) {
			System.out.println((i + 1) + " 번째 회원 입력");
			MemberVO mVO = new MemberVO();
			mVO.setName(nameList.get(i));
			mVO.setId(idList.get(i));
			mVO.setMail(mailList.get(i));
			mVO.setTel(telList.get(i));
			mVO.setPw(spw);
			mVO.setAno(mAno);
			mVO.setGen(mGen);
			if(i==0) {
				mVO.setAno(11);
			} else if(i == idList.size() - 1) {
				mVO.setAno(14);
				mVO.setGen(fGen);
			}
			
			mDAO.addMember(mVO);
		}
		
	}
	

	@RequestMapping("/dbInit.cls")
	public ModelAndView dbInit(ModelAndView mv) {
//		setAvt();
//		System.out.println("db init");
//		setData();
//		ArrayList<ProfileVO> list = (ArrayList<ProfileVO>) mDAO.getProf();
//		System.out.println("### list size : " + list.size());
//		mDAO.insertProfile(list);
		RedirectView rv = new RedirectView("/cls/main.cls");
		mv.setView(rv);
		return mv;
	}
}
