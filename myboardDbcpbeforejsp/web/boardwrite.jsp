<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form action="BoardWrite" method="POST">
        <input type="text" name="title"><br>
        <input type="text" name="content"><br>
        <input type="submit" value="등록">
        <input type="reset" value="취소">
    </form>
    
    <a href="BoardList">글 목록 가기</a> 
</body>
</html>