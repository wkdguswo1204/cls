package com.increpas.cls.dao;

import java.util.*;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.increpas.cls.vo.*;

public class MemberDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	@Autowired
	DataSourceTransactionManager trManager;
	/*
	@Resource(name="sqlSession")
	SqlSessionTemplate sqlSession;
	@Resource(name="sqlSession1")
	SqlSessionTemplate sqlSession1;
	*/
	public void testDAO() {
		System.out.println("######### testDAO() 실행 ############# ");
	}
	
	public int idCheck(String id) {
		return sqlSession.selectOne("mSQL.idCount", id);
	}
	
	// 로그인 질의명령 전담 처리함수
	public int login(MemberVO mVO) {
		return sqlSession.selectOne("mSQL.Login", mVO);
	}
	
	// 회원 버튼 리스트 가져오기 전담 처리 함수
	public List getList() {
		return sqlSession.selectList("mSQL.nameList");
	}
	
	// 회원 상세 정보 데이터베이스 조회 전담 처리 함수
	public MemberVO getDetail(String id) {
		return sqlSession.selectOne("mSQL.mDetail", id);
	}
	public MemberVO getDetail(int mno) {
		return sqlSession.selectOne("mSQL.mInfo", mno);
	}
	
	// 프로필 사진 조회 전담 처리함수
	public List getProfile(ProfileVO fVO) {
		return sqlSession.selectList("mSQL.getPic", fVO);
	}
	
	// 회원 가입 데이터베이스 전담 처리 함수
	public int addMember(MemberVO mVO) {
		return sqlSession.insert("mSQL.addMember", mVO);
	}
	
	// 아바타 추가 데이터베이스 작업 전담 처리함수
	public int addAvt(Map map) {
		return sqlSession.insert("mSQL.addAvt", map);
	}
	
	public int insertProfile(ProfileVO fVO) {
		return sqlSession.insert("mSQL.addProfile", fVO);
	}
	
	// 회원정보 데이터베이스 수정 전담 처리함수
	public int editMember(MemberVO mVO) {
		return sqlSession.update("mSQL.editMember", mVO);
	}
	
	/*
	public int insertProfile(ArrayList<ProfileVO> list) {
		int cnt = 0 ;
		for(int i = 0 ; i < list.size(); i++ ) {
			System.out.println("data count : " + (i + 1));
			ProfileVO fVO = list.get(i);
			cnt += sqlSession1.insert("mSQL.addProfile", fVO);
		}
		
		return cnt;
	}
	
	public List getProf() {
		return sqlSession.selectList("mSQL.getProf");
	}
	*/
}
