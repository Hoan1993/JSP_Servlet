<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- <form action="board_login" method="post" name="frm"> -->
<div align="center">	
<form name="frm" method="post" action="BoardServlet">
	<input type="hidden" name="command" value="board_login">

		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="submit" value="로그인" onclick="return loginCheck()">
					<input type="reset" value="다시작성">
					<input type="button" value="회원가입" onclick="location.href='BoardServlet?command=joinForm'">
				</td>
			</tr>
			<%-- <br /> <br />
			<tr>
				<td align="right">${message}</td>
			</tr> --%>
		</table>
	</form>
</div>
</body>
</html>