<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="red">
			<h3>Board1 테이블 상세보기</h3>
		<hr width="50%" color="red">	
		
		
		<!-- <form name="frm" action="board_list.do" method="post"> -->
		<%-- <c:set var="dto" value="${dto}" /> --%>
		<input type="hidden" name="board_no" value="${dto.board_no }">
			<table>
				<c:if test="${!empty dto }" >
				<tr>
					<th>글번호*</th>
					<td>${dto.board_no }</td>
				</tr>
				<tr>
					<th>작성자*</th>
					<td>${dto.board_writer }</td>
				</tr>
				<tr>
					<th>글제목*</th>
					<td>${dto.board_title }</td>
				</tr>
				<tr>
					<th>글내용*</th>
					<td>
					<textarea rows="7" cols="30" name="content" readonly="readonly">
					${dto.board_cont }
					</textarea>
					</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${dto.board_hit}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${dto.board_regdate }</td>
				</tr>
				</c:if>
				<c:if test="${empty dto}">
				<tr>
					<td colspan="4" align="center">
						<h3>검색된 레코드가 없습니다.</h3>
					</td>
				</tr>
			</c:if>		
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정" /> &nbsp; &nbsp;
						<input type="button" value="삭제" 
							onclick="location.href=
							'<%=request.getContextPath() %>/board_delete.do?no=${dto.getBoard_
							no() }'"/>
							 &nbsp; &nbsp;	 
						<input type="button" value="목록" 
						onclick="location.href='<%=request.getContextPath() %>/board_list.do'" />
					</td>
				</tr>	
			</table>
	<!-- 	</form> -->
	</div>
</body>
</html>