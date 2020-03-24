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
		<hr width="50%" color="red">
			<h3>JSP_BBS 테이블 게시물 답변 폼</h3>
		<hr width="50%" color="red">	
		<form name="frm" action="<%=request.getContextPath() %>/board_reply_ok.do" method="post">
			<c:set var="dto" value="${reply}" />
			
			<input type="hidden" name="board_no" value="${dto.getBoard_no() }" />
			<input type="hidden" name="board_group" value="${dto.getBoard_group() }" />
			<input type="hidden" name="board_step" value="${dto.getBoard_step() }" />
			<input type="hidden" name="board_indent" value="${dto.getBoard_indent()}" />
			
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>작성자*</th>
					<td><input type="text" name="writer" value="${dto.getBoard_writer() }"/></td>
				</tr>
				<tr>
					<th>글제목*</th>
					<td><input type="text" name="title" value="${dto.getBoard_title() }"/></td>
				</tr>
				<tr>
					<th>글내용*</th>
					<td>
					<textarea rows="7" cols="30" name="cont" >${dto.getBoard_cont() }</textarea>
					</td>
				</tr>
				<tr>
					<th>비밀번호*</th>
					<td><input type="password" name="pwd" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="답변" /> &nbsp; &nbsp;
						<input type="reset" value="취소" /> &nbsp; &nbsp;
						<input type="button" value="목록" 
						onclick="location.href='<%=request.getContextPath() %>/board_list.do'" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>