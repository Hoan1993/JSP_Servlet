package com.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardSearchAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 검색창에서 넘어온 데이터를 가지고 검색어에 해당하는 리스트를
		// DB에서 조회하여 뷰 페이지로 이동시키는 클래스.
		String find_field = request.getParameter("find_field");
		String find_name = request.getParameter("find_name").trim();
		
		int rowsize = 3;
		int block = 3;
		int totalRecord = 0; // db 상의 레코드 전체 수(게시글 수)
		int allPage = 0;	// 전체 페이지 수
		
		int page = 0;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			page = 1;
		}
		
		// 해당 페이지의 시작번호
		int startNo = (page * rowsize) - (rowsize -1);
		// 해당 페이지의 끝번호
		int endNo = (page * rowsize);
		
		// 해당 페이지의 시작 블럭
		int startBlock = (((page-1) / block)*block) +1;
		// 해당 페이지의 끝 블럭
		int endBlock = (((page-1) / block)*block)+block;
		
		BoardDAO dao = BoardDAO.getInstance();
		
		
		totalRecord = dao.searchListCount(find_field, find_name);
		// 검색된 전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나누어 준다.
		allPage = (int)Math.ceil(totalRecord / (double)rowsize);
		
		if(endBlock > allPage) {
			endBlock = allPage;
		}
		
		List<BoardDTO> list = dao.searchBoardList(find_field, find_name, page, rowsize);
		
		// 페이징 처리 작업 시 사용했던 모든 값들을 키로 저장해 주자.
		request.setAttribute("page", page);		// O
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);	// O
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);		// O
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock); // O
		request.setAttribute("endBlock", endBlock);		// O
		request.setAttribute("find_field", find_field);		// O
		request.setAttribute("find_name", find_name);		// O	
		request.setAttribute("list", list);				
		
		
	}

}
