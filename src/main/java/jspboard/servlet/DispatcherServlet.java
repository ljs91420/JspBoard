package jspboard.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.web.WebProcess;
import jspboard.webprocess.BoardDetailProcess;
import jspboard.webprocess.BoardEvalProcess;
import jspboard.webprocess.BoardIndexProcess;
import jspboard.webprocess.BoardWriteFormProcess;
import jspboard.webprocess.BoardWriteProcess;
import jspboard.webprocess.NotFoundPageProcess;

public class DispatcherServlet extends HttpServlet {
	final private static HashMap<String, WebProcess> URI_MAPPING = new HashMap<>();
	final private static String PREFIX = "/WEB-INF/views";
	final private static String SUFFIX = ".jsp";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		URI_MAPPING.put("GET:/board/", new BoardIndexProcess());
		URI_MAPPING.put("GET:/board/write", new BoardWriteFormProcess());
		URI_MAPPING.put("POST:/board/write", new BoardWriteProcess());
		URI_MAPPING.put("GET:/board/detail", new BoardDetailProcess());
		URI_MAPPING.put("GET:/board/eval", new BoardEvalProcess());
		URI_MAPPING.put("GET:/notfound", new NotFoundPageProcess());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath = req.getContextPath();
		
		String method = req.getMethod();
		String entireUri = req.getRequestURI();
		String uri = entireUri.substring(contextPath.length());
		
		System.out.println("요청 방식: " + method);
		System.out.println("전체 URI: " + entireUri);
		System.out.println("ContextPath: " + contextPath);
		System.out.println("ContextPath가 짤린 요청URI: " + uri);
		
		WebProcess wp = URI_MAPPING.get(method + ":" + uri);
		
		String nextView = null;
		
		if (wp != null) {
			nextView = wp.process(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/notfound");
			return;
		}
		
		if (nextView.startsWith("redirect:")) {
			resp.sendRedirect(nextView.substring("redirect:".length()));
		} else {
			req.getRequestDispatcher(PREFIX + nextView + SUFFIX).forward(req, resp);
		}
	}
}
