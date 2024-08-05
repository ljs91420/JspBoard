package jspboard.webprocess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.dao.HikariConnector;
import jspboard.dto.JspBoard;
import jspboard.web.WebProcess;

public class BoardIndexProcess implements WebProcess {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// DB에서 모든 게시글 목록을 가져온다.
		ServletContext application = request.getServletContext();
		
		String sql = "SELECT * FROM board";
		
		try (
			Connection conn = ((HikariConnector)application.getAttribute("hikari")).getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {
			List<JspBoard> boards = new ArrayList<>();
			request.setAttribute("boards", boards);
			
			while (rs.next()) {
				boards.add(new JspBoard(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return "/board/list";
	}
	
}
