<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test</title>
<link rel="stylesheet" href="/cls/css/w3.css" />
<link rel="stylesheet" href="/cls/css/join.css" />
<script type="text/javascript" src="/cls/js/jquery-3.5.0.min.js"></script>
<script type="text/javascript" src="/cls/js/join.js"></script>
<style>

</style>
<script type="text/javascript">

</script>
</head>
<body>
	<div class="w3-col l3 m3">
		<p></p>
	</div>
	<div class="w3-col l6 m6 s12">
		<form class="w3-col" method="post" action="/cls/member/joinProc.cls" name="frm" id="frm" encType="multipart/form-data">
			<div class="w3-col w3-center w3-blue w3-margin-top">
				<h2>Increpas Join</h2>
			</div>
			<div class="w3-col w3-padding-32 w3-margin-top">
				<div class="w3-col">
					<label class="w3-col l3 m3 w3-right-align w3-padding" for="name">회 원 이 름 : </label>
					<div class="w3-col l9 m12 w3-padding">
						<input class="w3-col" type="text" id="name" name="name">
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="id">회원 아이디 : </label>
					<div class="w3-col m9 w3-padding">
						<div class="w3-col m9 pright">
							<input class="w3-col" type="text" id="id" name="id">
						</div>
						<div class="w3-col m3 w3-red w3-hover-orange w3-button w3-small w3-card btn1" id="idck">ID Check</div>
						<p class="w3-col w3-center" id="idmsg" style="display: none;"></p>
						<div class="w3-col w3-center" id="idCont" style="display: none;">
							<h4 class="w3-col m6">I D : </h4><h4 class="w3-col m6" id="getId"></h4>
							<h4 class="w3-col m6">NAME : </h4><h4 class="w3-col m6" id="getName"></h4>
							<h4 class="w3-col m6">TEL : </h4><h4 class="w3-col m6" id="getTel"></h4>
							<h4 class="w3-col m6">MAIL : </h4><h4 class="w3-col m6" id="getMail"></h4>
							<!-- 
									문제 ]
										아이티체크 버튼을 클릭했을 경우
										입력한 아이디가 존해하면 해당 아이디의 정보를 조회해서
										idCont 태그에 추가하고 보여주세요.
								
							 -->
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
						<input class="w3-col" type="password" id="repw" name="reipw">
						<div class="w3-col dnone" id="pwck"></div> 
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="file">프로필 사진 : </label>
					<div class="w3-col m9 w3-padding w3-center" id="imgfr">
						<input class="w3-col" type="file" id="file" name="file">
						<img class="imgwin w3-border w3-margin-top w3-margin-bottom dnone" id="img1" src="../img/noimage.jpg" alt="Image">
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="pw">회원 이메일 : </label>
					<div class="w3-col m9 w3-padding">
						<input class="w3-col" type="text" id="mail" name="mail">
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="tel">전 화 번 호 : </label>
					<div class="w3-col m9 w3-padding">
						<input class="w3-col" type="text" id="tel" name="tel">
					</div>
				</div>
				<div class="w3-col">
					<label class="w3-col m3 w3-right-align w3-padding" for="gen">성 별 : </label>
					<div class="w3-col m9 w3-padding w3-center">
						<div class="w3-half">
							<input type="radio" id="mgen" name="gen" value="M">
							<label for="mgen"> 남 자</label>
						</div>
						<div class="w3-half">
							<input type="radio" id="wgen" name="gen" value="F">
							<label for="wgen"> 여 자</label>
						</div>
					</div>
				</div>
				<!-- ----------------------------------------------------------------------- -->
				<div class="w3-col dnone" id="avtbox">
					<label class="w3-col m3 w3-right-align w3-padding" for="avt">아바타 선택 : </label>
					<div class="w3-col m9">
						<div class="w3-row w3-padding w3-center dnone" id="mavt">
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
						<input class="w3-col" type="date" id="birth" name="birth">
					</div>
				</div>
			</div>
			<div class="w3-col w3-margin-top">
				<div class="w3-half w3-button w3-red w3-hover-amber" id="hbtn">홈으로...</div>
				<div class="w3-half w3-button w3-blue w3-hover-aqua" id="jbtn">회원가입</div>
			</div>
		</form>
	</div>
</body>
</html>