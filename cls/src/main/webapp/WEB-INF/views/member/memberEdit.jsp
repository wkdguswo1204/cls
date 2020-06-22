<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Edit</title>
<link rel="stylesheet" href="/cls/css/w3.css" />
<link rel="stylesheet" href="/cls/css/join.css" />
<link rel="stylesheet" href="/cls/css/user.css" />
<script type="text/javascript" src="/cls/js/jquery-3.5.0.min.js"></script>
<script type="text/javascript" src="/cls/js/memberEdit.js"></script>
<style>

</style>
<script type="text/javascript">
	$(function(){
		var sgen = '${DATA.gen}';
		var sano = '${DATA.ano}';
		var tt = 'input[value="'+ sano +'"]';
		$(tt).prop('checked', 'checked');
		if(sgen == 'M'){
			$('#favt').css('display', 'none');
			$('#mavt').css('display', '');
		} else {
			$('#mavt').css('display', 'none');
			$('#favt').css('display', '');
		}
		
	});
</script>
</head>
<body>
	<div class="w3-col l3 m3">
		<p></p>
	</div>
	<div class="w3-col l6 m6 s12">
		<form class="w3-col" method="post" action="/cls/member/memberEditProc.cls" name="frm" id="frm" encType="multipart/form-data">
			<input type="hidden" name="mno" value="${DATA.mno}">
			<input type="hidden" name="id" value="${DATA.id}">
			<div class="dnone" id="oldAno">${DATA.ano}</div>
			<div class="w3-col w3-center w3-blue w3-margin-top">
				<h2>${DATA.name} 님 정보수정</h2>
			</div>
			<div class="w3-col w3-padding-32 w3-margin-top">
				<div class="w3-col">
					<label class="w3-col l3 m3 w3-right-align w3-padding" for="name">회 원 이 름 : </label>
					<div class="w3-col l9 m12 w3-padding">
						<h4 class="w3-col" id="name" style="margin-top: 0px;">${DATA.name}</h4>
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="mno">회 원 번 호 : </label>
					<div class="w3-col m9 w3-padding">
						<div class="w3-col pright">
							<h4 class="w3-col" id="mno">${DATA.mno}</h4>
						</div>
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="id">회원 아이디 : </label>
					<div class="w3-col m9 w3-padding">
						<div class="w3-col pright">
							<h4 class="w3-col" id="id">${DATA.id}</h4>
						</div>
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="pw">비 밀 번 호 : </label>
					<div class="w3-col m9 w3-padding">
						<input class="w3-col" type="password" id="pw" name="pw">
					</div>
				</div>
				<div class="w3-col" id="rePwBox">
					<label class="w3-col m3 w3-right-align w3-padding" for="repw">비밀번호확인 : </label>
					<div class="w3-col m9 w3-padding">
						<input class="w3-col" type="password" id="repw" name="repw">
						<div class="w3-col dnone" id="pwck"></div> 
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="file">프로필 사진 : </label>
					<div class="w3-col m9 w3-padding w3-center" id="imgfr">
						<div id="filebox">
							<input class="w3-col" type="file" id="file" name="file">
						</div>
						<div class="w3-col" id="imgBox">
							<c:forEach var="pic" items="${DATA.pic}">
								<img class="w3-card imgwin w3-border w3-margin-top w3-margin-bottom pd-10 imgbox1" id="img1" src="/cls/profile/${pic.savename}" alt="Image">
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="pw">회원 이메일 : </label>
					<div class="w3-col m9 w3-padding">
						<h4 class="w3-col" id="mail">${DATA.mail}</h4>
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="tel">전 화 번 호 : </label>
					<div class="w3-col m9 w3-padding">
						<h4 class="w3-col" id="tel">${DATA.tel}</h4>
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="gen">성 별 : </label>
					<div class="w3-col m9 w3-padding">
						<div class="w3-half">
						<c:if test="${DATA.gen eq 'M'}">
							<h4 id="gen">남 자</h4>
						</c:if>
						<c:if test="${DATA.gen eq 'F'}">
							<h4 id="gen">여 자</h4>
						</c:if>
						</div>
					</div>
				</div>
				<!-- ----------------------------------------------------------------------- -->
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="avt">아바타 선택 : </label>
					<div class="w3-col m9">
						<div class="w3-row w3-padding w3-center" id="mavt">
							<div class="avtfr">
								<input type="radio" name="ano" value="11">
								<div class="w3-col">
									<div class="w3-col w3-center pd5 imgbox w3-border">
										<img src="../img/img_avatar1.png">
									</div>
								</div>
							</div>
							<div class="avtfr">
								<input type="radio" name="ano" value="12">
								<div class="w3-col">
									<div class="w3-col w3-center pd5 imgbox w3-border">
										<img src="../img/img_avatar2.png">
									</div>
								</div>
							</div>
							<div class="avtfr">
								<input type="radio" name="ano" value="13">
								<div class="w3-col">
									<div class="w3-col w3-center pd5 imgbox w3-border">
										<img src="../img/img_avatar3.png">
									</div>
								</div>
							</div>
						</div>
						<div class="w3-row w3-padding w3-center dnone" id="favt">
							<div class="avtfr">
								<input type="radio" name="ano" value="14">
								<div class="w3-col">
									<div class="w3-col w3-center pd5 imgbox w3-border">
										<img src="../img/img_avatar4.png">
									</div>
								</div>
							</div>
							<div class="avtfr">
								<input type="radio" name="ano" value="15">
								<div class="w3-col">
									<div class="w3-col w3-center pd5 imgbox w3-border">
										<img src="../img/img_avatar5.png">
									</div>
								</div>
							</div>
							<div class="avtfr">
								<input type="radio" name="ano" value="16">
								<div class="w3-col">
									<div class="w3-col w3-center pd5 imgbox w3-border">
										<img src="../img/img_avatar6.png">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- ----------------------------------------------------------------------- -->
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="birth">생
						년 월 일 : </label>
					<div class="w3-col m9 w3-padding">
						<h4 class="w3-col" id="birth">${DATA.birth}</h4>
					</div>
				</div>
			</div>
			<div class="w3-col w3-margin-top">
				<div class="w3-half w3-button w3-red w3-hover-amber" id="hbtn">홈으로...</div>
				<div class="w3-half w3-button w3-blue w3-hover-aqua" id="ebtn">수정하기</div>
			</div>
		</form>
	</div>
</body>
</html>