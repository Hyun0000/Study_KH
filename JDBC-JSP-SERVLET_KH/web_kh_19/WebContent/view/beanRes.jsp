<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	// request에 실려 들어오는 데이터의 한글깨짐 방지
	// (jsp-servlet)간의 데이터 전송에는 servlet 부분에 위와 같은 코드가 있다.
	// 지금은 jsp 파일간의 데이터 전송이므로 해당 코드가 필요하다.
	
	// String message = request.getParameter("message");
	// jsp 파일간의 데이터 전송은 이러한 방식을 쓰지 않는다. 
%>
<jsp:useBean id="vo" class="first.beanTest.model.vo.SimpleBeanData" />
<!-- SimpleBeanData의 instance를 만든 것이다. 그 instance의 이름이 바로 'vo'인 것이다. -->
<%-- SimpleBeanData vo = new SimpleBeanData; --%>

<!-- 
	<jsp:setProperty name="vo" property="message" />
	
	(쓰기기능)
	<input type="text" name="message"/>를 통해 실려온 값이
	<jsp:setProperty name="vo" property="message" />에 실린다.
	
	request.getParameter("message"); 작업이 자동으로 돼서
	(property="message")에 값이 써지는 것이다.
	당연히 getParameter(괄호)의 값과 property의 값이 동일해야 값이 써진다.
	
	따라서 별도로 request.getParameter("message"); 같은 코드를 안 적어도 된다.
	이미 vo에 값이 담겨져 있다.
	
	그니까 request.getParameter("message"); ==> 이게 필요없는 코드가 아니라
	굳이 코드로 작성하지 않아도 알아서 request.getParameter("message"); 작업이 진행되고
	그 값이 <jsp:setProperty name="vo" property="message" />의 
	vo에 자동으로 실리는 것이다.
	
	그리고 <jsp:getProperty name="vo" property="message" />를 통해
	vo에 들어있는 message의 값을 꺼내는 것이다.
 -->

<!-- 여러개의 데이터를 받는 방법 1 (데이터를 각각 받는다.) -->

<%--
	<jsp:setProperty name="vo" property="message" />
	<jsp:setProperty name="vo" property="a1" />
	<jsp:setProperty name="vo" property="a2" />
	
	settet() method를 이용해 값을 저장하는 것과 같은 방식이다.
	ex)
	vo.setMessage ("~~~");
	vo.setA1("~~~");
	vo.setA2("~~~");
--%>

<!-- 여러개의 데이터를 받는 방법 2 (한 번에 데이터를 받는다.)-->
<jsp:setProperty name="vo" property="*" />
<%--
	request.getParameter(); 작업을 쭉 수행할 건데 이때 SimpleBeanData class안에 있는 멤버 변수명(필드명)으로
	request.getParameter("message"); / request.getParameter("a1"); / request.getParameter("a2");
	작업을 하나씩 하나씩 진행해서 해당하는 값이 있는지 확인할 것이다. 그리고 거기에서 나온 값 각각을 vo에 넣을 것이다.
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bean Response Check</title>
</head>
<body>
	<h1>간단한 빈즈 프로그램 결과</h1>
	<hr color="red"></hr>
	<br>
	<font size="5">
		메세지 : 
		<jsp:getProperty name="vo" property="message" />
		<jsp:getProperty name="vo" property="a1" />
		<jsp:getProperty name="vo" property="a2" />
<!-- 	getter() method를 이용해 값을 꺼내는 것과 같은 방식이다.
		ex)
		vo.getMessage ("~~~");
		vo.getA1("~~~");
		vo.getA2("~~~");
-->
		
		<%-- <jsp:getProperty name="msg" property="message" /> --%>
		<!-- 멤버변수 이름이 message인 것에서 값을 꺼내줘 -->
	</font>
	<br>
	<br>
	<a href="../index.html">뒤로가기</a>
</body>
</html>