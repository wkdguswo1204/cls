package com.increpas.cls.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.increpas.cls.vo.*;
import com.increpas.cls.util.*;

/**
 * 이 클래스는 게시판 관련 데이터베이스 작업을 처리할 전담 클래스
 * @author	전은석
 * @since	2020/06/19
 * @version	V.1.0
 *
 */
public class BoardDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 게시판 게시물 갯수 조회 전담 처리함수
	public int getCnt() {
		return sqlSession.selectOne("bSQL.selCnt");
	}
	
	public List getList(PageUtil page){
		return sqlSession.selectList("bSQL.getList", page);
	}
	
	public int addList(BoardVO bVO) {
		return sqlSession.insert("bSQL.addList", bVO);
	}
	
	public int addImg(ProfileVO fVO) {
		return sqlSession.insert("bSQL.addImg", fVO);
	}
	
	public List getDetail(BoardVO bVO) {
		return sqlSession.selectList("bSQL.selDetail", bVO);
	}
}
