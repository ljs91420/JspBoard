<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정하기</title>
</head>
<body>
	<h3>글 수정하기 페이지</h3>
	
	<form action="write" method="POST">
		글 제목 : <input type="text" name="board_title" /> <br>
		글 비밀번호 : <input type="password" name="board_password" /> <br>
		글쓴이 : <input type="text" name="board_writer" /> <br>
		글 내용 : <textarea name="board_content" cols="30" rows="10"></textarea> <br>
		<input type="submit" value="수정하기" />
	</form>
</body>
</html>