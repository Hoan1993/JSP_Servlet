<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="css/board.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.css">
</head>
<body>
	<table>
		<tr>
			<td colspan="5" style="border: white; text-align: right"><a
				href="BoardServlet?command=board_logout">로그아웃</a></td>
		</tr>
	</table>
	<div id="wrap" align="center">
		<h1>게시글 리스트</h1>
		<table class="list">
			<tr>
				<td colspan="5" style="border: white; text-align: right"><a
					href="BoardServlet?command=board_write_form">게시글 등록</a></td>
			</tr>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회</th>
			</tr>
			<c:forEach var="board" items="${boardList }">
				<tr class="record">
					<td>${board.num }</td>
					<td><a href="BoardServlet?command=board_view&num=${board.num }">
						${board.title } </a></td>
					<td>${board.name }</td>
					<td><fmt:formatDate value="${board.writedate }" /></td>
					<td>${board.readcount}</td>
				</tr>
			</c:forEach>
		</table>

	</div>
	<div id="wrap" align="center">
		<ul class="pagination">
			<c:if test="${page > block }">
				<li class="paginate_button previous"><a
					href="BoardServlet?command=board_list&page=1">◀◀</a></li>
				<li><a href="BoardServlet?command=board_list&page=${startBlock - 1}">◀</a></li>
			</c:if>
			<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
				<li><a href="BoardServlet?command=board_list&page=${i }">${i }</a></li>
			</c:forEach>
			<c:if test="${endBlock < allPage }">
				<li><a href="BoardServlet?command=board_list&page=${endBlock + 1}">▶</a></li>
				<li class="paginate_button next"><a
					href="BoardServlet?command=board_list&page=${allPage }">▶▶</a></li>

			</c:if>


		</ul>

	</div>



</body>
</html>