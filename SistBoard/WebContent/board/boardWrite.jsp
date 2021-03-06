<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
<script type="text/javascript" src="script/board.js"></script>
</head>
<body>
<div id="wrap" align="center">
	<h2>게시글 등록</h2>

		<form name="frm" method="post" action="BoardServlet">
		<input type="hidden" name="command" value="board_write">
		<table>
			<tr>
				<th>작성자 </th>
				<td><input type="text" name="name">*필수</td>	
			</tr>
			<tr>
				<th>패스워드</th>
				<td><input type="password" name="pass"></td>	
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="16" cols="70" name="content" ></textarea></td>
			</tr>
		</table>	
	<input type="submit" value="등록" onClick="return boardCheck()">
	<input type="reset" value="다시작성">
	<input type="button" value="목록" onClick="location.href ='BoardServlet?command=board_list'">
	
	</form>
</div>
</body>
</html>