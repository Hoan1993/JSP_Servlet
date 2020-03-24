<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <script type="text/javascript">
jQuery(document).ready(function($) {
    $(".clickable-row").click(function() {
        window.location = $(this).data("href");
    });
});

</script> -->
<!-- <style type="text/css">
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

	th {
	background: yellowgreen;
	}

</style> -->

<title>Insert title here</title>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.css"> -->
<link rel="stylesheet" href="css/boostrap-3.3.2.css">
</head>
<body>
	<div id="wrap" align="center">
		<%@ include file="include/header.jsp" %>
		<div class="clear">
		<hr width="50%" color="red">
			<h3>MVC2 모델 BOARD1 테이블 검색 리스트</h3>
		<hr width="50%" color="red">
		<input type="hidden" name="page" value="${page }" />
		<table border="1" cellspacing="0" width="400">
		
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>조회수</th>
				<th>등록일</th>
			<c:set var="list" value="${list }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
			<%-- 	<tr class='clickable-row' data-href='board_cont.do?no=${dto.getBoard_no()}'> --%>
					<tr>
						<td>${dto.getBoard_no() } </td>
						
						<td><a href="board_searchCont.do?no=${dto.getBoard_no()}&page=${page}&find_field=${find_field}&find_name=${find_name}">
						${dto.getBoard_title() } 
						</a>
						</td>
						<td>${dto.getBoard_hit() }</td>
						<td>${dto.getBoard_regdate().substring(0,10) }</td>
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
			
		</table>
		
		<div>
			<ul class="pagination">
				<c:if test="${page > block }">
					<li class="paginate_button previous">
						<a href="board_search.do?page=1&find_field=${find_field}&find_name=${find_name}">◀◀</a>
					</li>
					<li><a href="board_search.do?page=${startBlock - 1}&find_field=${find_field}&find_name=${find_name}">◀</a></li>
				</c:if>
				<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
					<c:if test="${i == page }">
						<li class="active"> <a href="<%=request.getContextPath() %>/board_search.do?page=${i }&find_field=${find_field}&find_name=${find_name}">${i }</a>
					</c:if>
					<c:if test="${i != page }">
						<li> <a href="<%=request.getContextPath() %>/board_search.do?page=${i }&find_field=${find_field}&find_name=${find_name}">${i }</a>
					</c:if>
					<%-- <li> <a href="board_list.do?page=${i }">${i }</a>
					</li> --%>
				</c:forEach>
				<c:if test="${endBlock < allPage }">
					<li><a href="board_search.do?page=${endBlock + 1}&&find_field=${find_field}&find_name=${find_name}">▶</a></li>
					<li class="paginate_button next">
						<a href="board_search.do?page=${allPage }&&find_field=${find_field}&find_name=${find_name}">▶▶</a>
					</li>
				</c:if>
			</ul>
		
		</div>
		
		<div align="center">
			<input type="button" value="전체목록" onclick="location.href=location.href='<%=request.getContextPath() %>/board_list.do?no=${dto.getBoard_no()}'">
		
		
		</div>
		
		
		</div>
		
						
	</div>
</body>
</html>