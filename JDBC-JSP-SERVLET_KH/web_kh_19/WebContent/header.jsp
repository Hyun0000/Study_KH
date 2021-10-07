<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.*"%>
<%
	Member m = (Member) session.getAttribute("member");
%>
<nav> <!-- 메뉴 선택을 위한 nav 태그 작성 -->
	<ul>
		<li><a href="#">메뉴1</a></li>
		<li><a href="#">메뉴2</a></li>
		<li><a href="#">메뉴3</a></li>
		<li><a href="#">메뉴4</a></li>
	</ul>
</nav>
<% if (m == null) { %><!-- 로그인이 안 된 상태 -->
<div class="box"> <!-- 로그인 From -->
	<p>&nbsp; &nbsp;아이디 : <input type="text" name="userid" id="userid"></p>
	<p>비밀번호 : <input type="password" name="userpwd" id="userpwd"></p>
	<button type="button" id="btnLogin">로그인하기</button>&nbsp;&nbsp;
	<button type="button" id="btnEnroll" onclick="location.href='views/member/enroll.html';">회원가입</button>
</div>
<% } else { %>
<div class="box">
	<h3> 환영합니다, <%=m.getName()%>님!!! 	</h3>
	<p>오늘도 좋은 하루 되세요~!!</p>
	<button onclick="location.href='views/member/myInfo.jsp'">회원정보보기</button>
	<button onclick="location.href='logout.lo'">로그아웃</button>
</div>
<% } %>

<!-- 로그인 스크립트 -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	$("#btnLogin").on('click', function() {
		console.log("ajax시작")
		// $.ajax(); --> 기본모양
		// $.ajax(object(객체) type);
		// $.ajax({key1 : value1, key2 : value2, key3 : value3, ......})
		// object에서 key는 String, value는 함수, 숫자, 문자, 배열, 오브젝트 등 jS의 모든 타입을 넣을 수 있다.
		
		// ajax를 통해 데이터를 묶어서 로그인 정보를 전달한다.
		// 이로인해 HTML 영역에서 별도의 <form> 태그가 필요 없는 것이다.
		$.ajax({ // JQuery 를 통한 ajax 호출 방식
		// type : "GET", // servlet에 없는 method를 작성
		type : "POST", // <form>의 method 속성과 동일
		url : "login.lo", // <form>의 action 속성과 동일
		// url : "apple.loa", // 없는 url을 작성
		data : {
			// 원하는 값을 object(객체) 모양에 맞춰 각각 대입한 다음에 data(key)에 실겠다는 의미
			id : $("#userid").val(),
			// 사용자가 입력한 <input type="text" name="userid" id="userid">의 값을 읽어 id(key)에 저장
			// 이 id(key)를 들고 (url : "login.lo")로 가겠다는 의미
			passwd : $("#userpwd").val()
		},
		// 위에서 설정한 값들을 가지고 (url : "login.lo")로 이동한다.
		dataType : "json", // servlet으로 부터 전달받을 객체(데이터)의 모양이 JSON이다.
		// (아래의 success, error에서 이용할 데이터 타입이 json이라는 얘기)
		// 주의!!! ajax에서 servlet으로 보내는 데이터들은 (dataType : "json")과 무관하게 js의 objcet type 이어야한다.
		
		// 이 값을 전달받은 servlet 파일은 DB까지 갔다온 후 해당 데이터를 가지고 어디로 이동할지를 정해야한다.  
		// (request.getRequestDispatcher(path).forword()를 이용)
		// 그러나 ajax에서는 위와 같은 부분을 작성하지 않는다. 정확히 말하면 이동하고 싶은 위치를 '작성하지 않아야 한다.'
		// 데이터를 가지고 (url : "login.lo")로 갔다가 DB를 갔다온 후 모든 것이 끝나는 시점(doPost method가 끝나면)에는
		// 아래의 success(key) or error(key)로 돌아와 해당 key에 있는 function을 실행한다.
		// 즉, ajax를 통해 데이터를 전달받으면 해당 데이터를 통해 얻은 결과값을 부메랑처럼 다시 데이터를 전송한 곳으로 전달한다.
		// ajax는 '다시 나에게 돌아오게 하는 것이 목적'이다. 다른 페이지로 갈 수 없다.(반드시 시작한 곳으로 다시 돌아와야한다.)
		
		// 정리 : ajax를 통해서 데이터를 준 후 다시 그에 대한 결과 데이터를 받으면 이번에는 아래의 success or error로 이동한다.
		// 다른 페이지로는 이동할 수 없다.
		
		// 화면에 뿌려진 데이터를 function(data)에 넣어준다.(이렇게 약속이 되어있다.)
		success : function(data) { // 여기서의 data의 타입이 json 형태여야한다.
					if(data.result == "ok"){
						console.log("로그인 성공");
						// js에서 object의 값을 읽는 방법과 동일하다.
						// cf) 반드시 parameter 이름을 data로 할 필요는 없다. 아무거나 해도 된다.
						var text = "<h3>환영합니다, "+data["name"]+"님!!!</h3>"+"<p>오늘도 좋은 하루 되세요~!!</p><button onclick='location.href=\"views/member/myInfo.jsp\"'>회원정보보기</button><button onclick=\"location.href='logout.lo'\">로그아웃</button>";
						// data["name"] 이렇게 쓰면 오류 발생 가능성이 높다. why?
						// var text가 현재 String이기 때문에 쉼표가 ㅈㄹ 많아 오류가 발생할 수 있다.
						$(".box").html(text);
						
						// 방법 1&2만 이 부분에서 확인이 가능하다.(나머지 방법들은 애초에 로그인이 불가한 형태)
						// 방법 1
						console.log(data);
						// success의 function에 매개인자로 아래의 값이 들어오는 것이다.(parameter인 data에 아래의 값이 담기는 것이다.)
						// memberInfo: {id: 'user33', passwd: 'pass33', name: '사용자3', email: 'user22@test.or.kr'}
						// name: "사용자3"
						// result: "ok"
						console.log(data.memberInfo) // memberInfo: {id: 'user33', passwd: 'pass33', name: '사용자3', email: 'user22@test.or.kr'}
						console.log(data.memberInfo.id) // user33
						
						// 방법 2
						console.log(data)
						// memberInfo: Array(2)
						// 0: {id: 'user33', passwd: 'pass33', name: '사용자3', email: 'user22@test.or.kr'}
						// 1: {id: 'user33', passwd: 'pass33', name: '사용자3', email: 'user22@test.or.kr'}
						// name: "사용자3"
						// result: "ok"
						console.log(data.memberInfo)
						// 0: {id: 'user33', passwd: 'pass33', name: '사용자3', email: 'user22@test.or.kr'}
						// 1: {id: 'user33', passwd: 'pass33', name: '사용자3', email: 'user22@test.or.kr'}
						console.log(data.memberInfo[0].id)
						// user33
				} else {
						alert("로그인 실패!\nID와 비밀번호를 다시 확인하세요.");
						console.log("로그인 실패");
						// 방법 3
						console.log(data) // 배열 형태가 됐다.
						// 0:
						// memberInfo: {id: 'user33', passwd: 'pass33', name: '사용자3', email: 'user22@test.or.kr'}
						// name: "사용자3"
						// result: "ok"
						// [[Prototype]]: Object
						// 1:
						// memberInfo: {id: 'user33', passwd: 'pass33', name: '사용자3', email: 'user22@test.or.kr'}
						// name: "사용자3"
						// result: "ok"
						// [[Prototype]]: Object
						// length: 2
						console.log(data.id) // undefined
						console.log(data[0].result); // if(data[0].result == "ok") --> 이렇게 작성하면 로그인이 가능하다.
						
						// 방법 0 & 4(오타 아니다.)
						console.log(data)
						// 0: {id: 'user33', passwd: 'pass33', name: '사용자3', email: 'user22@test.or.kr'}
						// length: 1
						console.log(data.id) // undefined
						console.log(data[0].id);// user33
						
						// 방법 5
						console.log(data)
						// {id: 'user33', passwd: 'pass33', name: '사용자3', email: 'user22@test.or.kr'}
						// [[Prototype]]: Object
						console.log(data.id)
						// user33
				}
					console.log(data)
					console.log(data.id)
			},
			// 일부러 error를 만들어 보자
			// 1. 없는 url을 작성 --> url : "apple.loa"
			// 2. 지원하지 않는 type을 작성 --> type : "get" (현재 LoginServlet에는 doGet method 자체가 없는 상황이다.)
			// 2번의 경우 405 오류가 난다.
		error : function(request, status, error) {
			console.log("error 발생")
			alert("code:"+request.status+"\n"+"message:"+request.responseText+
			"\n"+"error:"+error);
				}
		});
	});
});
</script>
<hr style="clear: both;"> <!--  이전 float 속성을 지우고 구분선을 표시한다.  -->