<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판 리스트 페이지</title>
<link rel="stylesheet" href="/cls/css/w3.css">
<script type="text/javascript" src="/cls/js/jquery-3.5.0.min.js"></script>
<style>
    .mxw{
    	max-width: 1200px;
    }
    .bnone{
    	display: none;
    }
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('#write').click(function(){
			$(location).attr('href', '/cls/board/boardWrite.cls');
		});
		
		$('.pbtn').click(function(){
			// 할일
			// 1. 어떤 버튼이 클릭이 됬는지 알아낸다.
			var str = $(this).text();
			$('#nowPage').val(str);
			$('#frm').attr('action', '/cls/board/board.cls');
			$('#frm').submit();
		});
		
		$('.w3-button').click(function(){
			var str = $(this).html();
			if(str == 'PRE'){
				$('#nowPage').val('${PAGE.startPage - 1}');
			} else if(str == 'NEXT'){
				$('#nowPage').val('${PAGE.endPage + 1}');
			} else{
				return;
			}
			$('#frm').attr('action', '/cls/board/board.cls');
			$('#frm').submit();
		});
		
		// 게시글 상세보기 이벤트 처리
		$('.content').click(function(){
			// 할일
			// 글 번호 알아낸다.
			var sno = $(this).attr('id');
			$('#bno').val(sno);
			$('#nowPage').val('${PAGE.nowPage}');
			$('#frm').attr('action', '/cls/board/boardDetailProc.cls');
			$('#frm').submit();
		});
	});
</script>
</head>
<body>
	<form method="post" id="frm">
		<input type="hidden" name="nowPage" id="nowPage">
		<input type="hidden" name="bno" id="bno">
	</form>
	
    <div class="w3-content mxw">
        <div class="w3-center w3-col ">
            <h2>인크레파스 게시판</h2>
        </div>
        <div class="w3-rwo w3-margin-top">
            <div class="w3-col w3-padding w3-right-align w3-margin">
                <div class="w3-button w3-red" id="write">글쓰기</div>
            </div>
            <table class="w3-col w3-table-all w3-hoverable">
              <thead>
                <tr class="w3-light-grey">
                  <th>글번호</th>
                  <th>제목</th>
                  <th>작성자</th>
                  <th>작성일</th>
                  <th>조회수</th>
                </tr>
              </thead>
              <!-- 여기에 반복문을 작성하세요-->
              <c:forEach var="data" items="${LIST}">
	              <tr class="w3-text-gray content" id="${data.bno}">
	                <td>${data.bno}</td>
	                <td>${data.title}</td>
	                <td>${data.id}</td>
	                <td>${data.sdate}</td>
	                <td>${data.click}</td>
	              </tr>
              </c:forEach>
              <!--여기까지-->
            </table>
        </div>
        <div class="w3-center">
			<div class="w3-bar w3-border">
				<c:if test="${PAGE.startPage lt (PAGE.pageGroup + 1)}">
					<span class="w3-bar-item w3-light-gray">PRE</span>
				</c:if>
				<c:if test="${PAGE.startPage ge (PAGE.pageGroup + 1)}">
					<span class="w3-bar-item w3-button w3-hover-blue">PRE</span>
				</c:if>
				<c:forEach var="pageNo" begin="${PAGE.startPage}" end="${PAGE.endPage}">
					<span class="w3-bar-item w3-border-left w3-button w3-hover-blue pbtn">${pageNo}</span>
				</c:forEach>
				<c:if test="${PAGE.endPage ne PAGE.totalPage}">
					<span class="w3-bar-item w3-border-left w3-button w3-hover-blue">NEXT</span>
				</c:if>
				<c:if test="${PAGE.endPage eq PAGE.totalPage}">
					<span class="w3-bar-item w3-border-left w3-light-gray">NEXT</span>
				</c:if>
				
			</div>
		</div>
      </div>
</body>
</html>