<%@page import="java.util.List"%>
<%@page import="com.sist.model.BoardDAO"%>
<%@page import="com.sist.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BoardDTO> list = (List<BoardDTO>) request.getAttribute("list");
 
%>    
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
		
		<%
			if(list.size() != 0) { 
				for(int i=0; i< list.size(); i++) {
					BoardDTO dto = new BoardDTO();
					dto = list.get(i); 
					%>
					
					<tr>
						<td><%=dto.getBoard_no() %></td>
						<td>
							<a href="cont.do?no=<%=dto.getBoard_no() %>">
							<%=dto.getBoard_title() %> </a>
						</td>
						
						<td><%=dto.getBoard_hit() %></td>
						<td><%=dto.getBoard_regdate() %></td>	
					</tr>
		
				<% }
			} else {
				%>
				<tr>
					<td colspan="4" align="center">
						<h3>검색된 레코드가 없습니다.</h3>
					</td>
				</tr>
				
				
			<% } %>
			
			</table>
			<input type="button" value="전체목록"
			onclick="location.href='select.do'" />
			</div>
			
</body>
</html>