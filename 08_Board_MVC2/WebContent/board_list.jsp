<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		<hr width="50%" color="blue">
			<h3>Board1 테이블 전체 리스트</h3>
		<hr width="50%" color="blue">
		<br />
		<table>
			<tr>
				<th>글번호</th><th>글제목</th>
				<th>조회수</th><th>작성일</th>
			</tr>
			<c:set var="list" value="${list }" />
			<c:if test="${!empty list}">
				<c:forEach var="dto" items="${list }" >
					<tr>
						<td>${dto.getBoard_no()} </td>
						<td>
						<a href="board_cont.do?no=${dto.getBoard_no()}">${dto.getBoard_title()} 
						</a>
						</td>
						<td>${dto.getBoard_hit()} </td>
						<td>${dto.getBoard_regdate().substring(0,10)} </td>
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
		<hr width="50%" color="blue">
		<br />
		
		<input type="button" value="글쓰기" 
			onclick="location.href='<%=request.getContextPath() %>/board_write.do'">
		<hr /><br /><br />	
		<form method="post" action="<%=request.getContextPath() %>/board_search.do">
			<select name="find_field">
				<option value="title">제목</option>
				<option value="cont">내용</option>
				<option value="title_cont">제목+내용</option>
			</select>
			<input type="text" name="find_name" size="15" />
			<input type="submit" value="검색" />
			
		
		</form>	
			
	</div>
</body>
</html>