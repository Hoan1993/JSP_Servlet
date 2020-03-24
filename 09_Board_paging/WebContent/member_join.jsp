<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- 회원가입 스타일 적용 파일 링크 --%>
<link rel="stylesheet" href="css/member.css">
<%-- JQUERY 라이브러리 링크 --%>
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<%-- 회원가입에 있어서 데이터를 검증하는 외부 자바스크립트 파일을 링크 --%>
<script type="text/javascript" src="js/member.js"></script>
</head>
<body>

	<div align="center">
	<hr width="50%" color="red">
		<h3>회원가입</h3>
	<hr width="50%" color="red">
	<div id="join_wrap">
		<form method="post" action="<%=request.getContextPath() %>/member_join_ok.do" 
			name="f" onsubmit="return mem_check()">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="member_id" id="member_id" size="14" />
					<input type="button" value="아이디중복체크" id="idcheck_btn" />
					<br />
					<%-- 경고문 출력되는 위치 --%>
					<span id="idcheck"></span>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="member_pass" id="member_pass" size="14"></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="member_pass2" id="member_pass2" size="14"></td>
			</tr>
			<tr>
				<th>회원이름</th>
				<td><input type="text" name="member_name" id="member_name" size="14"></td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td><input type="text" name="member_nickname" id="member_nickname" size="14"></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="member_zip1" id="member_zip1" size="3" readonly onclick="post_search()"></td>
				<td><input type="text" name="member_zip2" id="member_zip2" size="3" readonly onclick="post_search()"></td>
				<td><input type="button" value="우편번호찾기" onclick="post_check()" /></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="member_addr1" id="member_addr1" size="50" readonly onclick="post_search()"></td>
			</tr>
			<tr>
				<th>나머지주소</th>
				<td><input type="text" name="member_addr2" id="member_addr2" size="50" /></td>
			</tr>
		</table>
		
		<div id="join_menu">
			<input type="submit" value="가입하기" /> &nbsp; &nbsp;
			<input type="reset" value="다시작성" /> &nbsp; &nbsp;
		</div>	<%-- id="join_menu" end --%>
		</form>
	</div>	<%-- id="join_wrap" end --%>
	</div>
</body>
</html>