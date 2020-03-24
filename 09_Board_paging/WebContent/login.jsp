<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/board.js"></script>
<style type="text/css">
	table {
		border: 1px solid #000;
		border-collapse: collapse;
		width: 350px;
	}
	
	th, td {
		border: 1px solid #000;
		border-collapse: collapse;
		text-align: center;
	}
</style>
</head>
<body>
	<div align="center">
		<hr width="50%" color="skyblue">
			<h3>회원 로그인 화면</h3>
		<hr width="50%" color="skyblue">
		
		<form method="post" action="<%=request.getContextPath() %>/loginOk.do"
					onsubmit="return login_check()">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id" /></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="로그인" /> &nbsp;
						<input type="reset" value="취소" /> &nbsp;
						<input type="button" value="회원가입" onclick="location.href='join.do'" /> &nbsp;
						<input type="button" value="비번찾기" onclick="location.href='findPwd.do'" />
						<!--비번찾기  -->
					</td>
 				</tr>
			</table>

		</form>
		
	
	
	</div>
</body>
</html>