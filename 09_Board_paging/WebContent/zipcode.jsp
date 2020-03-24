<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	ArrayList zipCodeList = (ArrayList) request.getAttribute("zip");
	String dong = (String) request.getAttribute("dong");
	String zipcode, addr, addr2;
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function check() {
		if(postform.dong.value == "") {
			alert("동을 입력해 주세요!!!");
			postform.dong.focus();
			return false;
		}				
	}
	
	function selectnow() {
		var zip = document.postform.post_list.value;
		var zip1 = zip.substring(0,3);	// 우편번호 앞 3자리
		var zip2 = zip.substring(4,7);	// 우편번호 뒤 3자리
		var addr2 = zip.substring(7, (zip.length)); // 나머지 주소
		// opener : 현재 페이지를 open한 주체 : 회원가입 폼 페이지
		opener.document.f.member_zip1.value = zip1;
		opener.document.f.member_zip2.value = zip2;
		opener.document.f.member_addr1.value = addr2;
		
		parent.window.close();	// 그 후에 윈도우 창을 닫아준다.
		
	}

</script>
</head>
<body bgcolor="#fff" topmargin="0" leftmargin="0"
	onload="postform.dong.focus()" >
	
	<form name="postform" method="post" 
		action="<%=request.getContextPath() %>/zipcode_ok.do"
		onsubmit="return check()">
		
		<table width="410" height="100" cellspacing="0" align="center">
			<tr>
				<td align="center" bgcolor="#999">
					<input type="image" src="images/ZipCode_img01.gif"
						width="413" height="58" />	
			</tr>
			<tr>
				<td bgcolor="#F5FFEA" align="center">
					<strong><font color="#466D1B">
						<span class="style1">[거주지의 면, 동을 입력하고 '찾기' 버튼을 누르세요!!!]</span>
					</font></strong>
				</td>
			
			</tr>
			<tr height="30">
				<td bgcolor="#F5FFEA" align="center">
				<input type="text" name="dong" size="10"> &nbsp;
				<input type="image" alt="이미지2" src="images/m-i02.gif" width="69" height="19" />
				</td> 
			</tr>
			
			<%-- 실제 우편번호가 출력될 위치 --%>
			<%
				if(dong != null) {
					if(zipCodeList.size() != 0) { %>
					<tr>
						<td bgcolor="#F5FFEA" height="30" align="center">
							<select name="post_list" onchange="selectnow()">
								<option value="">:::주소를 선택하세요:::</option>
								
								<%-- <c:forTokens items="${zipCodeList}" delims="," var="item">
 								   ${item}
 								   <br/>
								</c:forTokens> --%>

								
								<%
									for(int i=0; i<zipCodeList.size(); i++) {
										String data = (String)zipCodeList.get(i);
										StringTokenizer st = new StringTokenizer(data,",");
										zipcode = st.nextToken();
										addr = st.nextToken();
										addr2 = st.nextToken();
										String totalAddr = zipcode + addr; %>
										<option value="<%=totalAddr %>">[<%=zipcode %>]&nbsp; <%=addr %></option>	
										
								<% 	} %>
							</select>
					 	</td>
					</tr>
			<% 	} else { %>
					<tr>
						<td bgcolor="#F5FFEA" height="30" align="center">
							<font color="#466D1B"><span class="style1">검색결과가 없습니다.</span></font>
						</td>
					</tr>
			<% 	}
			} %>
			
			<tr>
				<td bgcolor="#508C0F" colspan="3" height="3"></td>
			</tr>
			
			
		</table>
	</form>


</body>
</html>