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

public class BoardWriteProcess implements WebProcess {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글을 씁니다.");
		
		ServletContext application = request.getServletContext();
		
		// 받은 파라미터로 DB에 글 추가하기
		JspBoard to_write = new JspBoard().setWrite(request);
		
		String sql = "INSERT INTO jspboard(board_id, board_title, board_writer, board_password, board_writer_ip_addr, board_content) VALUES(board_id_seq.nextval, ?, ?, ?, ?, ?)";
		
		// 서버 초기화시 만들어 놓은 연결을 꺼내온다.
		try (
			Connection conn = ((HikariConnector)application.getAttribute("hikari")).getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setString(1, to_write.getBoard_title());
			pstmt.setString(2, to_write.getBoard_writer());
			pstmt.setString(3, to_write.getBoard_password());
			pstmt.setString(4, to_write.getBoard_writer_ip_addr());
			pstmt.setString(5, to_write.getBoard_content());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "redirect:/board/";
	}

}
