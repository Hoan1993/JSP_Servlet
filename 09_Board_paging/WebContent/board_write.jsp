<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/board.js"></script>
<style type="text/css">
	table {
		border: 1px solid #000;
		border-collapse: collapse;
		width: 400px;
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

</head>
<body>
	<div align="center">
		<hr width="50%" color="red">
			<h3>MVC-2 모델 게시판 글쓰기 폼</h3>
		<hr width="50%" color="red">	
		<form name="frm" action="<%=request.getContextPath() %>/board_write_ok.do" method="post">
			<table>
				<tr>
					<th>작성자*</th>
					<td><input type="text" name="writer" /></td>
				</tr>
				<tr>
					<th>글제목*</th>
					<td><input type="text" name="title" /></td>
				</tr>
				<tr>
					<th>글내용*</th>
					<td>
					<textarea rows="7" cols="30" name="content"></textarea>
					</td>
				</tr>
				<tr>
					<th>비밀번호*</th>
					<td><input type="password" name="pwd" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글쓰기" onclick="return joinCheck()"/> &nbsp; &nbsp;
						<input type="reset" value="다시작성" /> &nbsp; &nbsp;
						<input type="button" value="목록" 
						onclick="location.href='<%=request.getContextPath() %>/board_list.do'" />
					</td>
				</tr>
			</table>
		</form>
	</div>


</body>
</html>