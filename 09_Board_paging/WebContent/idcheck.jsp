<%@page import="com.board.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = request.getParameter("userId");
	MemberDAO dao = MemberDAO.getInstance();
	int res= dao.checkMemberId(userId);
	out.println(res);
	

%>