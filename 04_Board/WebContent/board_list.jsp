<%@page import="com.sist.model.BoardDTO"%>
<%@page import="java.util.List"%>
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
			<h3>BOARD1 테이블 전체 게시물 목록</h3>
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
		<br />
			<hr width="50%" color="tomato">
		<br />
		<input type="button" value="글쓰기"
					onclick="location.href='board_write.jsp'" />
		<br />
		<form method="post" action="<%=request.getContextPath() %>/search.do">
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