<%@page import="kh.my.board.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		Board board = (Board)request.getAttribute("DetailBoard");
	%>
	<h1>글 상세 목록</h1>
	
	    <table border="1">
        <tr>
            <td>번호</td>
            <td>제목</td>
            <td>내용</td>
            <td>게시일</td>
            <td>작성자</td>
        </tr>
        <tr>
            <td><%=board.getBno() %></td>
            <td><%=board.getTitle() %></td>
            <td><%=board.getContent() %></td>
            <td><%=board.getCreateDate() %></td>
            <td><%=board.getWriter() %></td>
        </tr>
    </table>	
</body>
</html>