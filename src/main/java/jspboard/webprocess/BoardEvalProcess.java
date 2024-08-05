package jspboard.webprocess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspboard.dao.HikariConnector;
import jspboard.web.WebProcess;

public class BoardEvalProcess implements WebProcess {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		
		Integer board_id = Integer.parseInt(request.getParameter("board_id"));
		
		// 지금 웹 브라우저로 접속한 클라이언트가 추천을 한 적이 있으면 추천을 안 해야 한다.(쿠키 또는 세션을 사용)
		
		// 리스트에서 현재 추천을 누른 글 번호가 존재하면 더 이상 진행하지 않고 리다이렉트
		if (session.getAttribute("picked") != null) {
			List<Integer> pickedList = (List) session.getAttribute("picked");
			
			if (pickedList.contains(board_id)) {
				return "redirect:/board/detail?board_id=" + board_id;
			}
		}
		
		String pick = request.getParameter("pick").equals("g") ? "board_good_count" : "board_bad_count";
		
		// 추천수를 먼저 반영한 후 글 내용을 불러서 포워드(대신 글 보기 페이지로 리다이렉트)
		String sql1 = String.format("UPDATE board SET %s=%s+1 WHERE board_id=?", pick, pick);
		// String sql2 = "SELECT * FROM board WHERE board_id=?";
		
		try (
			Connection conn = ((HikariConnector)application.getAttribute("hikari")).getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql1);
			// PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		) {
			pstmt.setInt(1, board_id);
			pstmt.executeUpdate();
			
//			pstmt2.setInt(1, board_id);
//			
//			try (ResultSet rs = pstmt2.executeQuery()) {
//				rs.next();
//				request.setAttribute("board", new JspBoard(rs));
//			}
			
			// 이 클라이언트가 추천 또는 비추천 했던 글 번호를 모두 목록에 저장
			List<Integer> pickedList = new ArrayList<>();
			pickedList.add(board_id);
			session.setAttribute("picked", pickedList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "redirect:/board/detail?board_id=" + board_id;
	}
	
}
