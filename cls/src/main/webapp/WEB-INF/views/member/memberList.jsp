<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
<link rel="stylesheet" href="/cls/css/w3.css">
<link rel="stylesheet" href="/cls/css/user.css">
<script type="text/javascript" src="/cls/js/jquery-3.5.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		/*
		$('.btn').click(function(){
			var sno = $(this).attr('id');
			
			$('#mno').val(sno);
			$('#frm').submit();
		});
		*/
		// 회원정보 가져오기 비동기 통신
		
		$('.btn').hover(function(){
			var sno = $(this).attr('id');
			$.ajax({
				url: '/cls/member/mDetail.cls',
				type: 'post',
				dataType: 'json',
				data: {
					'mno': sno
				},
				success: function(obj){
					if(obj.status == 'No'){
						alert('로그인 먼저...!');
						return;
					}
					
					$('#wmno').text(obj.mno);
					$('#avatar').attr('src', '/cls/img/'+ obj.avatar);
					$('#id').text(obj.id);
					$('#name').text(obj.name);
					$('#tel').text(obj.tel);
					$('#gen').text(obj.gen);
					$('#mail').text(obj.mail);
					$('#jdate').text(obj.sdate);
					
					$('#detail').css('display', 'block');
				},
				error: function(){
					alert('######### 통신 에러 ##########');
				}
			});
		});
	});
</script>
</head>
<body>
	<form method="post" action="/cls/member/memberDetail.cls" id="frm">
		<input type="hidden" name="mno" id="mno">
	</form>
	<div class="w3-content mxw">
		<h2 class="w3-deep-purple w3-center w3-padding">회원 리스트</h2>
		<div class="w3-margin-top w3-center">
			<c:forEach var="data" items="${LIST}" varStatus="st">
				<div class="w3-card-4 w3-button w3-margin-bottom ${COLOR.get(st.index)} w-150 btn" id="${data.mno}">${data.name}</div>
			</c:forEach>
		</div>
	</div>
	
	<div class="w3-content mxw" id="detail" style="display: none;">
		<h2 class="w3-light-green w3-center w3-padding w3-card-4"><span id="ttl"></span> 회원 정보</h2>
		<div class="w3-col w3-padding w3-card-4">
			<div class="w3-margin-bottom w3-center">
				<img class="w3-border w3-circle w3-card pd-10 w-200" src="/cls/img/" id="avatar">
			</div>
			<div class="w3-border-bottom">
				<div class="w3-col w-200 w3-right-align f16">회원번호 : </div>
				<div class="w3-rest f16 pd-left-20" id="wmno"></div>
			</div>
			<div class="w3-border-bottom">
				<div class="w3-col w-200 w3-right-align f16">회원이름 : </div>
				<div class="w3-rest f16 pd-left-20" id="name"></div>
			</div>
			<div class="w3-border-bottom">
				<div class="w3-col w-200 w3-right-align f16">회원계정 : </div>
				<div class="w3-rest f16 pd-left-20" id="id"></div>
			</div>
			<div class="w3-border-bottom">
				<div class="w3-col w-200 w3-right-align f16">회원메일 : </div>
				<div class="w3-rest f16 pd-left-20" id="mail"></div>
			</div>
			<div class="w3-border-bottom">
				<div class="w3-col w-200 w3-right-align f16">전화번호 : </div>
				<div class="w3-rest f16 pd-left-20" id="tel"></div>
			</div>
			<div class="w3-border-bottom">
				<div class="w3-col w-200 w3-right-align f16">회원성별 : </div>
				<div class="w3-rest f16 pd-left-20" id="gen"></div>
			</div>
			<div class="w3-border-bottom w3-margin-bottom">
				<div class="w3-col w-200 w3-right-align f16">가입일자 : </div>
				<div class="w3-rest f16 pd-left-20" id="jdate"></div>
			</div>
		</div>
	</div>
</body>
</html>