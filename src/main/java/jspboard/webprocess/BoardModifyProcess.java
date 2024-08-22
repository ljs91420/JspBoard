package jspboard.webprocess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspboard.dao.HikariConnector;
import jspboard.dto.JspBoard;
import jspboard.web.WebProcess;

public class BoardModifyProcess implements WebProcess {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		ServletContext application = request.getServletContext();
		
		JspBoard to_modify = new JspBoard().setWrite(request);
		
		String sql = "UPDATE board SET board_title=?, board_writer=?, board_password=?, board_writer_ip_addr=?, board_content=? WHERE board_id=board_id_seq.currval";
		
		try (
			Connection conn = ((HikariConnector)application.getAttribute("hikari")).getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setString(1, to_modify.getBoard_title());
			pstmt.setString(2, to_modify.getBoard_writer());
			pstmt.setString(3, to_modify.getBoard_password());
			pstmt.setString(4, to_modify.getBoard_writer_ip_addr());
			pstmt.setString(5, to_modify.getBoard_content());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/board/";
	}

	
}
