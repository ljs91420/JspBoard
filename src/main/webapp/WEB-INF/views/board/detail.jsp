<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="detail_js" value="/resoures/js/detail.js" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 제목: ${board.board_title}</title>
</head>
<body>
	<div>${board.board_title}</div>
	<div>${board.board_writer}</div>
	<div>${board.board_write_date}</div>
	<div>${board.board_content}</div>
	<div>조회수 : ${board.board_view_count}</div>
	<div>좋아요 : ${board.board_good_count}</div>
	<div>싫어요 : ${board.board_bad_count}</div>
	<div><input type="password" /><button>글 수정</button></div>
	<div>
		<button id="good-btn">추천</button>
		<button id="bad-btn">비추천</button>
	</div>
	
	<script>
		const board_id = ${board.board_id};
	</script>
	<script src="${detail_js}"></script>
</body>
</html>