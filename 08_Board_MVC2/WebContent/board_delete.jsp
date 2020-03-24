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
		<hr width="50%" color="orange">
			<h3>BOARD1 테이블 게시글 삭제 폼</h3>
		<hr width="50%" color="orange">
		
		<form method="post" action="<%=request.getContextPath() %>/board_deleteOk.do?no=${board_no}">
			<input type="hidden" name="no" value="${board_no}">
			<table border="1" cellspacing="0" width="350">
				<tr>
					<th>삭제할 비밀번호</th>
					<td>
						<input type="password" name="pwd" />
					</td>
				</tr>	
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글삭제" /> &nbsp; &nbsp;
						<input type="reset" value="취소" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>