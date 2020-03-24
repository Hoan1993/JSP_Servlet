<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="tomato">
			<h3>BOARD1 테이블 검색 목록</h3>
		<hr width="50%" color="tomato">
		<br /><br />
		<table border="1" cellspacing="0" width="400">
			<tr>
				<th>글번호</th><th>글제목</th>
				<th>조회수</th><th>작성일자</th>	
			</tr>
			<c:if test="${!empty list}">
			<c:forEach var="board" items="${list }">
					<tr>
						<td>${board.getBoard_no() }</td>
						<td>
							<a href="board_cont.do?no=${board.getBoard_no()}">
							${board.getBoard_title()} </a>
						</td>
						
						<td>${board.getBoard_hit()}</td>
						<td>${board.getBoard_regdate()}</td>	
					</tr>
			</c:forEach>		
			</c:if>
				<c:if test="${empty list}">
				<tr>
					<td colspan="4" align="center">
						<h3>검색된 레코드가 없습니다.</h3>
					</td>
				</tr>
			</c:if>	
			
			</table>
			<input type="button" value="전체목록"
			onclick="location.href='board_list.do'" />
			</div>
</body>
</html>