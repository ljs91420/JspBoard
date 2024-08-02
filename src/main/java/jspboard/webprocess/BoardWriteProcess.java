package jspboard.webprocess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.web.WebProcess;

public class BoardWriteProcess implements WebProcess {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// 받은 파라미터로 DB에 글 추가하기
		
		return "redirect:/board/";
	}

}
