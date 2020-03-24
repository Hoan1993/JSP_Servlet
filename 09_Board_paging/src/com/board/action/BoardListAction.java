package com.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB의 게시글 전체 레코드를 조회하는 클래스.
		// BoardDAO dao = BoardDAO.getInstance();
		// List<BoardDTO> list = dao.getBoardList();
		// request.setAttriute("list", list);
		
		// 페이징 처리
		// rowsize는 한 페이지에 보여줄 게시물 수
		int rowsize  = 3;
		int block = 3; // 아래에 보여질 페이지의 최대수 - 예) < [1][2][3] > < [4][5][6] >
		int totalRecord = 0;	// DB 상의 레코드 전체 수(게시물의 수) --> DB에 몇 개가 있는지 DB에서 받아와야 한다.
		int allPage = 0;		// 전체 페이지 수	
								//if(totalRecord%rowsize != 0) { allPage = totalRecord/rowsize+1; }
								// else { allPage = totalRecord/rowsize; }
		int page = 0;
		if(request.getParameter("page")!= null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			page = 1;	// 처 음으로 게시물 목록 태그를 클릭한 경우
		}
		
		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize -1);
		// 해당 페이지에 끝 번호
		int endNo = (page * rowsize);
		
		// 해당 페이지의 시작 블럭
		int startBlock = (((page - 1) / block)*block)+1;
		// 해당 페이지의 마지막 블럭
		int endBlock = (((page -1)/block)*block)+block;
		
		BoardDAO dao = BoardDAO.getInstance();
		totalRecord  = dao.getListCount();
		
		// 전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나누어 주어야 한다.
		// 이 과정을 거치면 전체 페이지 수가 나온다.
		// 전체 페이지가 나올 때 나머지가 있으면 무조건 올려주어야 한다.
		
	/*	if(totalRecord%rowsize != 0) { 
			allPage = totalRecord/rowsize+1; 
		} else { 
			allPage = totalRecord/rowsize; 
		}*/
		
		
		allPage = (int)Math.ceil(totalRecord/(double)rowsize);
		
		if(endBlock > allPage) {
			endBlock = allPage;
		}
		
		
		List<BoardDTO> list = dao.getBoardList(page, rowsize);
		
		// 페이징 처리 시 사용했던 모든 값들을 키로 저장하자. 그 값들을 뷰 페이지에서 사용할 수 있기 때문에. startBlock과 endBlock, page
		// startNo과 endNo
		
		request.setAttribute("page", page);		// O
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);	// O
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);		// O
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock); // O
		request.setAttribute("endBlock", endBlock);		// O
		request.setAttribute("list", list);				// O
		
		
	}

}
