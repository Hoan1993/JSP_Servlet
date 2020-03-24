<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%-- <%

	if(session.getAttribute("joinResult") != null) {
		out.println("<script>alert('게시물성공')</script>");
	}
	session.invalidate();

%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table {
		border: 1px solid #000;
		border-collapse: collapse;
		width: 600px;
	}
	
	th, td {
		border: 1px solid #000;
		border-collapse: collapse;
		text-align: center;
	}

	th {
	background: yellowgreen;
	}
</style>
<link rel="stylesheet" href="css/boostrap-3.3.2.css">
</head>
<body>


	<div align="center">
		<hr width="50%" color="red">
			<h3>JSP_BBS 테이블 게시판 전체 리스트</h3>
		<hr width="50%" color="red">

		<table>
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>조회수</th>
				<th>작성일자</th>
				<th>GROUP</th>
				<th>STEP</th>
				<th>INDENT</th>
			<c:set var="list" value="${list }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
			<%-- 	<tr class='clickable-row' data-href='board_cont.do?no=${dto.getBoard_no()}'> --%>
					<tr>
						<td>${dto.getBoard_no() } </td>
						
						<td>
						<c:forEach begin="1" end="${dto.getBoard_indent()}">
							<img src="images/AnswerLine.gif">
						</c:forEach>
						<a href="<%=request.getContextPath() %>/board_cont.do?no=${dto.getBoard_no()}">
						${dto.getBoard_title() } 
						</a>
						</td>
						<td>${dto.getBoard_hit() }</td>
						<td>${dto.getBoard_date().substring(0,10) }</td>
						<td>${dto.getBoard_group()}</td>
						<td>${dto.getBoard_step() }</td>
						<td>${dto.getBoard_indent() }</td>
					</tr>
				
				</c:forEach>
			
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="4">
						<h3>검색된 레코드가 없습니다.</h3>
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td colspan="7" align="right">
				<input type="button" value="글쓰기"
				onclick="location.href='<%=request.getContextPath() %>/board_write.do'" />
			</tr>

	</table>
</div>



</body>
</html>